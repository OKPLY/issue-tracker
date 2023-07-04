package issue_tracker.repository;

import issue_tracker.domain.Issue;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueRepo extends ListCrudRepository<Issue, Long> {
}
