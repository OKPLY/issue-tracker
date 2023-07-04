package issue_tracker.repository;

import issue_tracker.domain.Permission;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepo extends ListCrudRepository<Permission, Long> {
}
