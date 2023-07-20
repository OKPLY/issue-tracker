package issue_tracker.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import issue_tracker.aspect.annotation.Log;
import issue_tracker.domain.Tag;
import issue_tracker.dto.tag.CreateTagDto;
import issue_tracker.dto.tag.UpdateTagDto;
import issue_tracker.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@CrossOrigin
public class TagController {

    private final TagService tagService;

    @GetMapping
    @Log
    public ResponseEntity<List<Tag>> findAll() {
        return ResponseEntity.ok(tagService.findAll());
    }

    @GetMapping("{id}")
    @Log
    public ResponseEntity<Tag> findById(@PathVariable Long id) {
        return ResponseEntity.ok(tagService.findById(id));
    }

    @PostMapping
    @Log
    public ResponseEntity<Tag> create(@RequestBody CreateTagDto tagDto) {
        return new ResponseEntity<>(tagService.create(tagDto), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    @Log
    public ResponseEntity<Tag> update(@PathVariable Long id, @RequestBody UpdateTagDto tagDto) {
        tagDto.setId(id);
        return ResponseEntity.ok(tagService.update(tagDto));
    }

    @DeleteMapping("{id}")
    @Log
    public ResponseEntity<Tag> delete(@PathVariable Long id) {
        return ResponseEntity.ok(tagService.delete(id));
    }

}
