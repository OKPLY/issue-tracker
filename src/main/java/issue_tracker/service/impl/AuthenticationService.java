package issue_tracker.service.impl;

import issue_tracker.domain.Permission;
import issue_tracker.domain.Role;
import issue_tracker.domain.User;
import issue_tracker.dto.auth.AuthenticationRequest;
import issue_tracker.dto.auth.AuthenticationResponse;
import issue_tracker.dto.auth.RegisterRequest;
import issue_tracker.repository.PermissionRepo;
import issue_tracker.repository.RoleRepo;
import issue_tracker.repository.UserRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PermissionRepo permissionRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {

        //TODO:  We need to check which kind of user we create

        // based on the roles we can get the permission from the database
        // Though we need to hard code it in the Security Config.java file for the filter. Though we can do that
        // in a brute force manner once we set everything for each entity and HTTP method we are set

        // I also added some cascading the permssion role and user it needed to be eager


        List<Role> roles = new ArrayList<>();
        Set<Permission> permissions = new HashSet<>();

        for (String roleString: request.getRoles()){
            Optional<Role> role = roleRepo.findByName(roleString);
            if(role.isEmpty()) throw new RuntimeException("Role Doesn't Exists");

            roles.add(role.get());
            permissions.addAll(role.get().getPermissions());
        }


        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(roles)
                .deleted(false)
                .build();

        userRepo.save(user);

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User Not found"));

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }

}
