package issue_tracker.service;

import issue_tracker.domain.User;
import issue_tracker.domain.dto.CreateUser;
import issue_tracker.dto.aggregation.CreatedResolvedReviewedAggregate;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    User createUser(CreateUser user);
    User updateUser(Long id, User user);
    void deleteUser(Long id);

    CreatedResolvedReviewedAggregate currentUserAggregate();
}
