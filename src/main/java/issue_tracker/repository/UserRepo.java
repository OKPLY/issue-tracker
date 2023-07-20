package issue_tracker.repository;

import issue_tracker.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends ListCrudRepository<User, Long> {

    Optional<User> findByEmail(String userName);

    //Query to get the 10 most recent users
    @Query("SELECT u FROM User AS u ORDER BY u.createdAt DESC")
    List<User> getRecentUsers();

}
