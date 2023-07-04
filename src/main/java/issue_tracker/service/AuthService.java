package issue_tracker.service;


import issue_tracker.domain.dto.RefreshTokenRequest;
import issue_tracker.domain.dto.UserRequest;
import issue_tracker.domain.dto.UserResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {
    UserResponse login(UserRequest loginRequest);
    UserResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
