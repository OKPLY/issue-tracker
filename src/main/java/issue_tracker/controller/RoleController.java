package issue_tracker.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import issue_tracker.aspect.annotation.Log;
import issue_tracker.domain.Role;
import issue_tracker.domain.Tag;
import issue_tracker.dto.role.CreateRoleDto;
import issue_tracker.dto.role.UpdateRoleDto;
import issue_tracker.dto.tag.CreateTagDto;
import issue_tracker.dto.tag.UpdateTagDto;
import issue_tracker.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@CrossOrigin
public class RoleController {
    private final RoleService roleService;

    @GetMapping
    @Log
    public ResponseEntity<List<Role>> findAll() {
        return ResponseEntity.ok(roleService.findAll());
    }

    @GetMapping("{id}")
    @Log
    public ResponseEntity<Role> findById(@PathVariable Long id) {
        return ResponseEntity.ok(roleService.findById(id));
    }

    @PostMapping
    @Log
    public ResponseEntity<Role> create(@RequestBody CreateRoleDto roleDto) {
        return new ResponseEntity<>(roleService.create(roleDto), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    @Log
    public ResponseEntity<Role> update(@PathVariable Long id, @RequestBody UpdateRoleDto roleDto) {
        roleDto.setId(id);
        return ResponseEntity.ok(roleService.update(roleDto));
    }

    @DeleteMapping("{id}")
    @Log
    public ResponseEntity<Role> delete(@PathVariable Long id) {
        return ResponseEntity.ok(roleService.delete(id));
    }
}
