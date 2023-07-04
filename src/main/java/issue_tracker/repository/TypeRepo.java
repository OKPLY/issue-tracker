package issue_tracker.repository;

import issue_tracker.domain.Type;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepo extends ListCrudRepository<Type, Long> {
}
