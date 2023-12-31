package issue_tracker.service.impl;

import issue_tracker.domain.Issue;
import issue_tracker.domain.Status;
import issue_tracker.dto.aggregation.*;
import issue_tracker.dto.issue.AssignIssueDto;
import issue_tracker.dto.issue.CreateIssueDto;
import issue_tracker.dto.issue.UpdateIssueDto;
import issue_tracker.repository.IssueRepo;
import issue_tracker.service.IssueService;
import issue_tracker.utility.Util;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class IssueServiceImpl implements IssueService {

    private final IssueRepo issueRepo;

    private final ModelMapper modelMapper;

    private final Util util;

    @Override
    public List<Issue> findAll() {
        return issueRepo.findAll();
    }

    @Override
    public Issue findById(Long id) {
        var res = issueRepo.findById(id);

        if (res.isPresent())
            return res.get();

        throw new NoSuchElementException("Issue Not Found.");
    }

    @Override
    public List<Issue> findByParentIssueId(Long parentIssueId) {
        return issueRepo.findAllByParentIssue_Id(parentIssueId);
    }

    @Override
    public Issue create(CreateIssueDto issueDto) {
        Issue issue = modelMapper.map(issueDto, Issue.class);
        issue.setCreator(util.getUserFromContext());
        issue.setStatus(Status.CREATED);
        return issueRepo.save(issue);
    }

    @Override
    public Issue create(Long parentIssueId, CreateIssueDto issueDto) {
        Issue parentIssue = findById(parentIssueId);
        Issue issue = modelMapper.map(issueDto, Issue.class);
        issue.setParentIssue(parentIssue);
        issue.setCreator(util.getUserFromContext());
        issue.setStatus(Status.CREATED);

        return issueRepo.save(issue);
    }

    @Override
    public Issue update(UpdateIssueDto issueDto) {
        Issue issue = findById(issueDto.getId());
        issue.setTags(null);
        issue.setType(null);

        modelMapper.map(issueDto, issue);

        return issueRepo.save(issue);
    }

    @Override
    public Issue assign(AssignIssueDto issueDto) {
        Issue issue = findById(issueDto.getId());
        issue.setResolver(null);
        issue.setReviewer(util.getUserFromContext());
        modelMapper.map(issueDto, issue);

        issue.setAssignedAt(LocalDateTime.now());
        issue.setStatus(Status.ASSIGNED);

        return issueRepo.save(issue);
    }

    @Override
    public Issue resolve(Long id) {
        Issue issue = findById(id);
        issue.setResolver(util.getUserFromContext());
        issue.setResolvedAt(LocalDateTime.now());
        issue.setStatus(Status.RESOLVED);
        return issueRepo.save(issue);
    }

    @Override
    public Issue close(Long id) {
        Issue issue = findById(id);
        issue.setReviewer(util.getUserFromContext());
        issue.setAssignedAt(LocalDateTime.now());
        issue.setStatus(Status.CLOSED);
        return issueRepo.save(issue);
    }

    @Override
    public Issue delete(Long id) {
        Issue issue = findById(id);
        issue.setDeleted(true);

        return issueRepo.save(issue);
    }

    @Override
    public List<CreatedDateIssueAggregation> aggregateByCreatedDate() {
        return issueRepo.aggregateByCreatedDate();
    }

    @Override
    public List<ReviewdDateIssueAggregation> aggregateByReviewedDate() {
        return issueRepo.aggregateByReviewedDate();
    }

    @Override
    public List<ResolvedDateIssueAggregation> aggregateByResolvedDate() {
        return issueRepo.aggregateByResolvedDate();
    }

    // No longer needed
//    @Override
//    public List<StatusCountAggregation> aggregateByStatus() {
//        return issueRepo.getStatusCountAggregation();
//    }

    @Override
    public List<TagCountAggregation> aggregateByTopTag(Integer limit) {
        var data = issueRepo.getMostCommonIssueTags();
        return data.stream().limit(limit).toList();

    }

    @Override
    public List<Map.Entry<LocalDate, CreatedResolvedReviewedAggregate>> createdResolvedReviewedDateAggregate() {
        var createdAggregate = issueRepo.aggregateByCreatedDate();
        var resolvedAggregate = issueRepo.aggregateByResolvedDate();
        var reviewedAggregate = issueRepo.aggregateByReviewedDate();

        Map<LocalDate, CreatedResolvedReviewedAggregate> data = new HashMap<>();
        createdAggregate.parallelStream().forEach((item) -> {
            var time = item.getCreatedAt().toLocalDate();
            if(ChronoUnit.DAYS.between(time, LocalDate.now()) > 30)
                return;

            if (!data.containsKey(time)){
                var aggregate = new CreatedResolvedReviewedAggregate();
                aggregate.setCreated(item.getAmount());
                data.put(time, aggregate);
                return;
            }
            data.get(time).setCreated(item.getAmount() + data.get(time).getCreated());
        });

        resolvedAggregate.parallelStream().forEach((item) -> {
            var time = item.getResolvedAt().toLocalDate();
            if(ChronoUnit.DAYS.between(time, LocalDate.now()) > 30)
                return;

            if (!data.containsKey(time)) {
                var aggregate = new CreatedResolvedReviewedAggregate();
                aggregate.setResolved(item.getAmount());
                data.put(time, aggregate);
                return;
            }
            data.get(time).setResolved(item.getAmount() + data.get(time).getResolved());
        });

        reviewedAggregate.parallelStream().forEach((item) -> {
            var time = item.getReviewedAt().toLocalDate();
            if(ChronoUnit.DAYS.between(time, LocalDate.now()) > 30)
                return;

            if (!data.containsKey(time)) {
                var aggregate = new CreatedResolvedReviewedAggregate();
                aggregate.setReviewed(item.getAmount());
                data.put(time, aggregate);
                return;
            }
            data.get(time).setReviewed(item.getAmount() + data.get(time).getReviewed());
        });

        List<Map.Entry<LocalDate, CreatedResolvedReviewedAggregate>> entries = new ArrayList<>(data.entrySet());
        entries.sort((o1, o2) -> o2.getKey().compareTo(o1.getKey()));

        return entries;
    }

    @Override
    public List<Issue> getRecentIssues(Integer limit) {
        return issueRepo.getRecentIssues().stream().limit(limit).toList();
    }

    @Override
    public List<Issue> filter(String status, Long tagId, Long typeId, String text, Long creatorId, Long reviewerId, Long resolverId) {
        List<Issue> issues = issueRepo.findAll();
        Stream<Issue> issueStream = issues.stream();

        if (status != null && !status.isBlank())
            issueStream = issueStream.filter((issue) -> issue.getStatus().toString().equals(status));

        if (tagId != null)
            issueStream = issueStream.filter((issue) -> issue.getTags().stream().anyMatch((tag) -> tag.getId().equals(tagId)));

        if (typeId != null)
            issueStream = issueStream.filter((issue) -> issue.getType().getId().equals(typeId));

        if (text != null)
            issueStream = issueStream.filter(issue -> {
                return issue.getTitle().toLowerCase().contains(text.toLowerCase()) ||
                        issue.getDescription().toLowerCase().contains(text.toLowerCase());
            });

        if (creatorId != null)
            issueStream = issueStream.filter((issue) -> issue.getCreator() != null && issue.getCreator().getId().equals(creatorId));

        if (reviewerId != null)
            issueStream = issueStream.filter((issue) -> issue.getReviewer() != null && issue.getReviewer().getId().equals(reviewerId));

        if (resolverId != null)
            issueStream = issueStream.filter((issue) -> issue.getResolver() != null && issue.getResolver().getId().equals(resolverId));

        return issueStream.sorted((x, y) -> Long.compare(y.getId(), x.getId())).toList();

    }


}
