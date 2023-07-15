package issue_tracker.repository;

import issue_tracker.domain.Issue;
import issue_tracker.dto.aggregation.CreatedDateIssueAggregation;
import issue_tracker.dto.aggregation.ResolvedDateIssueAggregation;
import issue_tracker.dto.aggregation.ReviewdDateIssueAggregation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepo extends JpaRepository<Issue, Long> {

    List<Issue> findAllByParentIssue_Id(Long issueId);

    @Query("SELECT new issue_tracker.dto.aggregation.CreatedDateIssueAggregation(i.createdAt, COUNT(i)) FROM Issue AS i  where i.createdAt != NULL GROUP BY i.createdAt")
    List<CreatedDateIssueAggregation> aggregateByCreatedDate();

    @Query("SELECT new issue_tracker.dto.aggregation.ReviewdDateIssueAggregation(i.assignedAt, COUNT(i)) FROM Issue AS i WHERE i.assignedAt != NULL GROUP BY i.assignedAt")
    List<ReviewdDateIssueAggregation> aggregateByReviewedDate();

    @Query("SELECT new issue_tracker.dto.aggregation.ResolvedDateIssueAggregation(i.resolvedAt, COUNT(i)) FROM Issue AS i WHERE i.resolvedAt != NULL GROUP BY i.resolvedAt")
    List<ResolvedDateIssueAggregation> aggregateByResolvedDate();
}

