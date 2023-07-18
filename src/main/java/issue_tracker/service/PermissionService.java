package issue_tracker.service;

import issue_tracker.domain.Permission;
import issue_tracker.domain.Role;

import java.util.List;

public interface PermissionService {
    public List<Permission> getAllPermissions();

}
