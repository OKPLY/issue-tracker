package issue_tracker.repository;

import issue_tracker.domain.Tag;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepo extends ListCrudRepository<Tag, Long> {
}
