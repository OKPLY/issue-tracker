package issue_tracker.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import issue_tracker.domain.Comment;
import issue_tracker.domain.Issue;
import issue_tracker.dto.aggregation.CreatedDateIssueAggregation;
import issue_tracker.dto.aggregation.CreatedResolvedReviewedAggregate;
import issue_tracker.dto.aggregation.ResolvedDateIssueAggregation;
import issue_tracker.dto.aggregation.ReviewdDateIssueAggregation;
import issue_tracker.dto.comment.CreateCommentDto;
import issue_tracker.dto.issue.AssignIssueDto;
import issue_tracker.dto.issue.CreateIssueDto;
import issue_tracker.dto.issue.UpdateIssueDto;
import issue_tracker.service.CommentService;
import issue_tracker.service.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/issues")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class IssueController {

    private final IssueService issueService;
    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<List<Issue>> findAll() {
        return ResponseEntity.ok(issueService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Issue> findById(@PathVariable Long id) {
        return ResponseEntity.ok(issueService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Issue> create(@RequestBody CreateIssueDto issueDto) {
        return new ResponseEntity<>(issueService.create(issueDto), HttpStatus.CREATED);
    }

    @GetMapping("{id}/issues")
    public ResponseEntity<List<Issue>> findByParentIssueId(@PathVariable Long id) {
        return ResponseEntity.ok(issueService.findByParentIssueId(id));
    }

    @PostMapping("{id}/issues")
    public ResponseEntity<Issue> createByParent(@PathVariable Long id, @RequestBody CreateIssueDto issueDto) {
        return new ResponseEntity<>(issueService.create(id, issueDto), HttpStatus.CREATED);
    }

    @PostMapping("{id}/assign")
    public ResponseEntity<Issue> assign(@PathVariable Long id, @RequestBody AssignIssueDto issueDto) {
        issueDto.setId(id);
        return ResponseEntity.ok(issueService.assign(issueDto));
    }

    @PostMapping("{id}/resolve")
    public ResponseEntity<Issue> resolve(@PathVariable Long id) {
        return ResponseEntity.ok(issueService.resolve(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<Issue> update(@PathVariable Long id, @RequestBody UpdateIssueDto issueDto) {
        issueDto.setId(id);
        return ResponseEntity.ok(issueService.update(issueDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Issue> delete(@PathVariable Long id) {
        return ResponseEntity.ok(issueService.delete(id));
    }

    //Comments

    @GetMapping("{id}/comments")
    public ResponseEntity<List<Comment>> findAllComments(@PathVariable Long id) {
        return ResponseEntity.ok(commentService.findAllByIssueId(id));
    }

    @PostMapping("{id}/comments")
    public ResponseEntity<Comment> createComment(@PathVariable Long id, @RequestBody CreateCommentDto commentDto) {
        return new ResponseEntity<>(commentService.createByIssueId(id, commentDto), HttpStatus.CREATED);
    }

    @GetMapping("/aggregation/created-date")
    public ResponseEntity<List<CreatedDateIssueAggregation>> getCreatedDateAggregation() {
        return ResponseEntity.ok(issueService.aggregateByCreatedDate());
    }
    @GetMapping("/aggregation/review-date")
    public ResponseEntity<List<ReviewdDateIssueAggregation>> getReviewedDateAggregation() {
        return ResponseEntity.ok(issueService.aggregateByReviewedDate());
    }
    @GetMapping("/aggregation/resolve-date")
    public ResponseEntity<List<ResolvedDateIssueAggregation>> getResolvedDateAggregation() {
        return ResponseEntity.ok(issueService.aggregateByResolvedDate());
    }
    @GetMapping("/aggregation/all-date")
    public ResponseEntity<Map<LocalDateTime, CreatedResolvedReviewedAggregate>> getAllDateAggregation() {
        return ResponseEntity.ok(issueService.createdResolvedReviewedDateAggregate());
    }

}
