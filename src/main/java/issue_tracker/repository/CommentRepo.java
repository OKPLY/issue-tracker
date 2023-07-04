package issue_tracker.repository;

import issue_tracker.domain.Comment;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends ListCrudRepository<Comment, Long> {
}
