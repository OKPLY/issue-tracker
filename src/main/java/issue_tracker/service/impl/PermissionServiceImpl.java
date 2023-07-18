package issue_tracker.service.impl;

import issue_tracker.domain.Permission;
import issue_tracker.repository.PermissionRepo;
import issue_tracker.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {
    private final PermissionRepo permissionRepo;
    @Override
    public List<Permission> getAllPermissions() {
        return permissionRepo.findAll();
    }
}
