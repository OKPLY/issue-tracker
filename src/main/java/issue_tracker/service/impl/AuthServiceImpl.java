package issue_tracker.service.impl;


import issue_tracker.domain.User;
import issue_tracker.dto.auth.UserRequest;
import issue_tracker.dto.auth.UserResponse;
import issue_tracker.dto.user.BasicUserDto;
import issue_tracker.repository.UserRepo;
import issue_tracker.service.AuthService;
import issue_tracker.utility.Util;
import jakarta.persistence.Basic;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import issue_tracker.utility.JwtUtil;


@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {


    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final UserRepo userRepo;

    @Override
    public UserResponse login(UserRequest loginRequest) {
        Authentication result = null;
        try {
            result = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(e.getMessage());
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(result.getName());

        final String accessToken = jwtUtil.generateToken(userDetails.getUsername());
//        final String refreshToken = jwtUtil.generateRefreshToken(loginRequest.getEmail());

        User user = userRepo.findByEmail(userDetails.getUsername()).get();

        BasicUserDto basicUser = BasicUserDto.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .profilePicture(user.getProfilePicture())
                .roles(user.getRoles())
                .build();

        var userResponse = new UserResponse(accessToken, "refreshToken", basicUser);
        return userResponse;
    }


}