package issue_tracker.repository;

import issue_tracker.domain.User;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends ListCrudRepository<User, Long> {

    Optional<User> findByEmail(String userName);
}
