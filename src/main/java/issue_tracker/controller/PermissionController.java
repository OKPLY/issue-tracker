package issue_tracker.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import issue_tracker.aspect.annotation.Log;
import issue_tracker.domain.Permission;
import issue_tracker.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@CrossOrigin
public class PermissionController {
    private final PermissionService permissionService;

    @GetMapping
    @Log
    public ResponseEntity<List<Permission>> findAll(){
        return ResponseEntity.ok(permissionService.getAllPermissions());
    }
}
