package issue_tracker.service.impl;

import issue_tracker.domain.Permission;
import issue_tracker.domain.Role;
import issue_tracker.dto.role.CreateRoleDto;
import issue_tracker.dto.role.UpdateRoleDto;
import issue_tracker.repository.PermissionRepo;
import issue_tracker.repository.RoleRepo;
import issue_tracker.service.RoleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepo roleRepo;
    private final PermissionRepo permissionRepo;

    @Override
    public List<Role> findAll() {
        return roleRepo.findAll();
    }

    @Override
    public Role findById(Long id) {
        var role = roleRepo.findById(id);

        if (role.isPresent())
            return role.get();

        throw new NoSuchElementException("Role Not Found.");
    }

    @Override
    @Transactional
    public Role create(CreateRoleDto roleDto) {
        Role role = new Role();
        role.setName(roleDto.getName());

        final List<Permission> permissions = getPermissionsFromId(roleDto.getPermissionIds());
        role.setPermissions(permissions);

        return roleRepo.save(role);
    }


    @Override
    public Role update(UpdateRoleDto roleDto) {
        var role = findById(roleDto.getId());

        System.out.println(role.getId());

        role.setName(roleDto.getName());
        List<Permission> permissions = getPermissionsFromId(roleDto.getPermissionIds());
        role.setPermissions(permissions);
        return roleRepo.save(role);
    }

    @Override
    public List<Permission> getPermissionsFromId(List<Long> permissionIds) {
        List<Permission> permissions = new ArrayList<>();

        permissionIds
                .parallelStream()
                .forEach(permissionId -> {
                    var permission = permissionRepo.findById(permissionId);
                    if (permission.isPresent())
                        permissions.add(permission.get());
                    else
                        throw new NoSuchElementException("Permission Not Found.");
                });

        return permissions;
    }

    @Override
    public Role delete(Long id) {
        var role = findById(id);
        role.setDeleted(true);
        return roleRepo.save(role);

    }
}
