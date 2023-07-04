package issue_tracker.repository;

import issue_tracker.domain.Role;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends ListCrudRepository<Role, Long> {
}
