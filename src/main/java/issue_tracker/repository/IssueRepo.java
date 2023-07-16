package issue_tracker.repository;

import issue_tracker.domain.Issue;
import issue_tracker.domain.User;
import issue_tracker.dto.aggregation.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public interface IssueRepo extends JpaRepository<Issue, Long> {

    List<Issue> findAllByParentIssue_Id(Long issueId);

    @Query("SELECT new issue_tracker.dto.aggregation.CreatedDateIssueAggregation(i.createdAt, COUNT(i)) FROM Issue AS i  where i.createdAt != NULL GROUP BY i.createdAt")
    List<CreatedDateIssueAggregation> aggregateByCreatedDate();

    @Query("SELECT new issue_tracker.dto.aggregation.ReviewdDateIssueAggregation(i.assignedAt, COUNT(i)) FROM Issue AS i WHERE i.assignedAt != NULL GROUP BY i.assignedAt")
    List<ReviewdDateIssueAggregation> aggregateByReviewedDate();

    @Query("SELECT new issue_tracker.dto.aggregation.ResolvedDateIssueAggregation(i.resolvedAt, COUNT(i)) FROM Issue AS i WHERE i.resolvedAt != NULL GROUP BY i.resolvedAt")
    List<ResolvedDateIssueAggregation> aggregateByResolvedDate();


   @Query("SELECT COUNT(i) FROM Issue AS i WHERE i.creator = ?1")
   Long getCreationAggregate(User user);
    @Query("SELECT COUNT(i) FROM Issue AS i WHERE i.reviewer = ?1")
    Long getReviewAggregate(User user);

    @Query("SELECT COUNT(i) FROM Issue AS i WHERE i.resolver = ?1")
    Long getResolveAggregate(User user);

    // TODO: Figure out how to get the nulls
    @Query("SELECT new issue_tracker.dto.aggregation.TypeCountAggregation(i.type.name, COUNT(i)) FROM Issue AS i GROUP BY i.type")
    List<TypeCountAggregation> getTypeCountAggregation();

    //Query to get the top 5 most common tags
    @Query("SELECT new issue_tracker.dto.aggregation.TagCountAggregation(t.name, COUNT(i)) FROM Issue AS i JOIN i.tags AS t GROUP BY t.name ORDER BY COUNT(i) DESC")
    List<TagCountAggregation> getMostCommonIssueTags();

    // No longer needed
//    @Query("SELECT new issue_tracker.dto.aggregation.StatusCountAggregation(i.status, COUNT(i)) FROM Issue AS i GROUP BY i.status")
//    List<StatusCountAggregation> getStatusCountAggregation();

}

