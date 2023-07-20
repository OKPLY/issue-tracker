package issue_tracker.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import issue_tracker.domain.Issue;
import issue_tracker.domain.Type;
import issue_tracker.dto.aggregation.TypeCountAggregation;
import issue_tracker.dto.type.CreateTypeDto;
import issue_tracker.dto.type.UpdateTypeDto;
import issue_tracker.service.TypeService;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/types")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@CrossOrigin
public class TypeController {

    private final TypeService typeService;

    @GetMapping
    @Log
    public ResponseEntity<List<Type>> findAll(){
        return ResponseEntity.ok(typeService.findAll());
    }

    @Log
    @GetMapping("{id}")
    public ResponseEntity<Type> findById(@PathVariable Long id){
        return ResponseEntity.ok(typeService.findById(id));
    }

    @GetMapping("/aggregate")
    @Log
    public ResponseEntity<List<TypeCountAggregation>> aggregate() {
        return ResponseEntity.ok(typeService.typeCountAggregation());
    }

    @PostMapping
    @Log
    public ResponseEntity<Type> create(@RequestBody CreateTypeDto typeDto){
        return ResponseEntity.ok(typeService.create(typeDto));
    }

    @PutMapping("{id}")
    @Log
    public ResponseEntity<Type> update(@PathVariable Long id, @RequestBody UpdateTypeDto typeDto){
        typeDto.setId(id);

        return ResponseEntity.ok(typeService.update(typeDto));
    }

    @DeleteMapping("{id}")
    @Log
    public ResponseEntity<Type> delete(@PathVariable Long id){
        return ResponseEntity.ok(typeService.delete(id));
    }
}
