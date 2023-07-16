package issue_tracker.service;


import issue_tracker.dto.auth.UserRequest;
import issue_tracker.dto.auth.UserResponse;

public interface AuthService  {
    UserResponse login(UserRequest loginRequest);
//    UserResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
