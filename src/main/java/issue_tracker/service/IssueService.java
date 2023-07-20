package issue_tracker.service;

import issue_tracker.domain.Issue;
import issue_tracker.dto.aggregation.*;
import issue_tracker.dto.issue.AssignIssueDto;
import issue_tracker.dto.issue.CreateIssueDto;
import issue_tracker.dto.issue.UpdateIssueDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface IssueService {
    List<Issue> findAll();

    Issue findById(Long id);

    List<Issue> findByParentIssueId(Long parentIssueId);

    Issue create(CreateIssueDto issueDto);

    Issue create(Long parentIssueId, CreateIssueDto issueDto);

    Issue update(UpdateIssueDto issueDto);

    Issue assign(AssignIssueDto issueDto);

    Issue resolve(Long id);

    Issue delete(Long id);

    List<CreatedDateIssueAggregation> aggregateByCreatedDate();

    List<ReviewdDateIssueAggregation> aggregateByReviewedDate();

    List<ResolvedDateIssueAggregation> aggregateByResolvedDate();

//    List<StatusCountAggregation> aggregateByStatus();

    List<TagCountAggregation> aggregateByTopTag(Integer limit);

    Map<LocalDateTime, CreatedResolvedReviewedAggregate> createdResolvedReviewedDateAggregate();

    List<Issue> getRecentIssues(Integer limit);

    List<Issue> filter(String status, Long tagId, Long typeId, String text);

    Issue close(Long id);
}
