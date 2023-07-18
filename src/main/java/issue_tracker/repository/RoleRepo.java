package issue_tracker.repository;

import issue_tracker.domain.Permission;
import issue_tracker.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    Role getByName(String name);

}
