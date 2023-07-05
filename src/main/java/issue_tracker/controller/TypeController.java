package issue_tracker.controller;

import issue_tracker.domain.Issue;
import issue_tracker.domain.Type;
import issue_tracker.dto.type.CreateTypeDto;
import issue_tracker.dto.type.UpdateTypeDto;
import issue_tracker.service.TypeService;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/types")
@RequiredArgsConstructor
public class TypeController {

    private final TypeService typeService;

    @GetMapping
    public ResponseEntity<List<Type>> findAll(){
        return ResponseEntity.ok(typeService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Type> findById(@PathVariable Long id){
        return ResponseEntity.ok(typeService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Type> create(@RequestBody CreateTypeDto typeDto){
        return ResponseEntity.ok(typeService.create(typeDto));
    }

    @PutMapping("{id}")
    public ResponseEntity<Type> update(@PathVariable Long id, @RequestBody UpdateTypeDto typeDto){
        typeDto.setId(id);

        return ResponseEntity.ok(typeService.update(typeDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Type> delete(@PathVariable Long id){
        return ResponseEntity.ok(typeService.delete(id));
    }
}