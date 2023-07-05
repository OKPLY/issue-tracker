package issue_tracker.service.impl;

import issue_tracker.domain.Comment;
import issue_tracker.domain.Issue;
import issue_tracker.dto.comment.CreateCommentDto;
import issue_tracker.dto.comment.UpdateCommentDto;
import issue_tracker.repository.CommentRepo;
import issue_tracker.service.CommentService;
import issue_tracker.service.IssueService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final IssueService issueService;
    private final CommentRepo commentRepo;
    private final ModelMapper modelMapper;

    private Comment create(Comment comment) {
        return commentRepo.save(comment);
    }

    @Override
    public List<Comment> findAll() {
        return commentRepo.findAll();
    }

    @Override
    public Comment findById(Long id) {
        var res = commentRepo.findById(id);

        if (res.isPresent())
            return res.get();

        throw new NoSuchElementException("Comment Not Found.");
    }

    @Override
    public List<Comment> findAllByIssueId(Long issueId) {
        return commentRepo.findAllByIssue_Id(issueId);
    }

    @Override
    public List<Comment> findAllByParentCommentId(Long parentCommentId) {
        return commentRepo.findAllByParentComment_Id(parentCommentId);
    }

    @Override
    public Comment createByIssueId(Long issueId, CreateCommentDto commentDto) {
        Issue issue = issueService.findById(issueId);
        Comment comment = modelMapper.map(commentDto, Comment.class);
        comment.setIssue(issue);

        return create(comment);
    }

    @Override
    public Comment createByCommentId(Long commentId, CreateCommentDto commentDto) {
        Comment parentComment = findById(commentId);
        Comment comment = modelMapper.map(commentDto, Comment.class);
        comment.setParentComment(parentComment);

        return create(comment);
    }

    @Override
    public Comment update(UpdateCommentDto commentDto) {
        Comment comment = findById(commentDto.getId());
        modelMapper.map(commentDto, comment);

        return commentRepo.save(comment);
    }

    @Override
    public Comment delete(Long id) {
        Comment comment = findById(id);
        comment.setDeleted(true);

        return commentRepo.save(comment);
    }

}
