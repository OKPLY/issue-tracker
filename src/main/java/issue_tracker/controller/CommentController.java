package issue_tracker.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import issue_tracker.domain.Comment;
import issue_tracker.dto.comment.CreateCommentDto;
import issue_tracker.dto.comment.UpdateCommentDto;
import issue_tracker.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("comments")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("{id}")
    public ResponseEntity<Comment> findById(@PathVariable Long id) {
        return ResponseEntity.ok(commentService.findById(id));
    }

    @GetMapping("{id}/comments")
    public ResponseEntity<List<Comment>> findByCommentId(@PathVariable Long id) {
        return ResponseEntity.ok(commentService.findAllByParentCommentId(id));
    }

    @PostMapping("{id}/comments")
    public ResponseEntity<Comment> create(@PathVariable Long id, @RequestBody CreateCommentDto commentDto) {
        return new ResponseEntity<>(commentService.createByCommentId(id, commentDto), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Comment> update(@PathVariable Long id, @RequestBody UpdateCommentDto commentDto) {
        commentDto.setId(id);
        return ResponseEntity.ok(commentService.update(commentDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Comment> delete(@PathVariable Long id) {
        return ResponseEntity.ok(commentService.delete(id));
    }

}
