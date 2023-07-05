package issue_tracker.repository;

import issue_tracker.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {

    List<Comment> findAllByParentComment_Id(Long commentId);

    List<Comment> findAllByIssue_Id(Long issueId);
}
