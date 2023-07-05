package issue_tracker.service.impl;

import issue_tracker.domain.Issue;
import issue_tracker.dto.issue.AssignIssueDto;
import issue_tracker.dto.issue.CreateIssueDto;
import issue_tracker.dto.issue.UpdateIssueDto;
import issue_tracker.repository.IssueRepo;
import issue_tracker.service.IssueService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class IssueServiceImpl implements IssueService {

    private final IssueRepo issueRepo;

    private final ModelMapper modelMapper;

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
        return issueRepo.save(issue);
    }

    @Override
    public Issue create(Long parentIssueId, CreateIssueDto issueDto) {
        Issue parentIssue = findById(parentIssueId);
        Issue issue = modelMapper.map(issueDto, Issue.class);
        issue.setParentIssue(parentIssue);

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
        modelMapper.map(issueDto, issue);

        issue.setAssignedAt(LocalDateTime.now());

        return issueRepo.save(issue);
    }

    @Override
    public Issue resolve(Long id) {
        Issue issue = findById(id);

        issue.setResolvedAt(LocalDateTime.now());

        return issueRepo.save(issue);
    }

    @Override
    public Issue delete(Long id) {
        Issue issue = findById(id);
        issue.setDeleted(true);

        return issueRepo.save(issue);
    }
}
