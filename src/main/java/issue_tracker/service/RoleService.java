package issue_tracker.service;

import issue_tracker.domain.Permission;
import issue_tracker.domain.Role;
import issue_tracker.dto.role.CreateRoleDto;
import issue_tracker.dto.role.UpdateRoleDto;
import jakarta.transaction.Transactional;

import java.util.List;

public interface RoleService {

    List<Role> findAll();

    Role findById(Long id);

    Role create(CreateRoleDto roleDto);

    Role update(UpdateRoleDto roleDto);

    @Transactional
    List<Permission> getPermissionsFromId(List<Long> permissionIds);

    Role delete(Long id);
}
