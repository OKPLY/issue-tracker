package issue_tracker.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import issue_tracker.domain.Status;
import issue_tracker.dto.status.CreateStatusDto;
import issue_tracker.dto.status.UpdateStatusDto;
import issue_tracker.service.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/status")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class StatusController {

    private final StatusService statusService;

    @GetMapping
    public ResponseEntity<List<Status>> findAll(){
        return ResponseEntity.ok(statusService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Status> findById(@PathVariable Long id){
        return ResponseEntity.ok(statusService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Status> create(@RequestBody CreateStatusDto statusDto){
        return new ResponseEntity<>(statusService.create(statusDto), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Status> update(@PathVariable Long id, @RequestBody UpdateStatusDto statusDto){
        statusDto.setId(id);

        return ResponseEntity.ok(statusService.update(statusDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Status> delete(@PathVariable Long id){
        return ResponseEntity.ok(statusService.delete(id));
    }
}
