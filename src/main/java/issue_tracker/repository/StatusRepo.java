package issue_tracker.repository;

import issue_tracker.domain.Status;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepo extends ListCrudRepository<Status, Long> {
}
