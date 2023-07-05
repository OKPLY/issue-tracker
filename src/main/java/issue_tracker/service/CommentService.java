package issue_tracker.service;

import issue_tracker.domain.Comment;
import issue_tracker.dto.comment.CreateCommentDto;
import issue_tracker.dto.comment.UpdateCommentDto;

import java.util.List;

public interface CommentService {
    List<Comment> findAll();

    Comment findById(Long id);

    List<Comment> findAllByIssueId(Long issueId);

    List<Comment> findAllByParentCommentId(Long parentCommentId);

    Comment createByIssueId(Long issueId, CreateCommentDto commentDto);

    Comment createByCommentId(Long commentId, CreateCommentDto commentDto);

    Comment update(UpdateCommentDto commentDto);

    Comment delete(Long id);
}
