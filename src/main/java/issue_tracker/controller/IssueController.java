package issue_tracker.controller;

import issue_tracker.domain.Issue;
import issue_tracker.dto.issue.CreateIssueDto;
import issue_tracker.dto.issue.UpdateIssueDto;
import issue_tracker.service.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/issues")
@RequiredArgsConstructor
public class IssueController {

    private final IssueService issueService;

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

    @PutMapping("{id}")
    public ResponseEntity<Issue> update(@PathVariable Long id, @RequestBody UpdateIssueDto issueDto) {
        issueDto.setId(id);
        return ResponseEntity.ok(issueService.update(issueDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Issue> delete(@PathVariable Long id) {
        return ResponseEntity.ok(issueService.delete(id));
    }
}
