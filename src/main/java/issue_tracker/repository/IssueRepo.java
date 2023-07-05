package issue_tracker.repository;

import issue_tracker.domain.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepo extends JpaRepository<Issue, Long> {

    List<Issue> findAllByParentIssue_Id(Long issueId);
}
