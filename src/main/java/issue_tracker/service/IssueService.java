package issue_tracker.service;

import issue_tracker.domain.Issue;
import issue_tracker.dto.aggregation.CreatedDateIssueAggregation;
import issue_tracker.dto.aggregation.CreatedResolvedReviewedAggregate;
import issue_tracker.dto.aggregation.ResolvedDateIssueAggregation;
import issue_tracker.dto.aggregation.ReviewdDateIssueAggregation;
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

    Map<LocalDateTime, CreatedResolvedReviewedAggregate> createdResolvedReviewedDateAggregate();
}
