package issue_tracker.controller;



import issue_tracker.domain.dto.RefreshTokenRequest;
import issue_tracker.domain.dto.UserRequest;
import issue_tracker.domain.dto.UserResponse;
import issue_tracker.service.impl.AuthServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/uaa")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UaaController {
    private final AuthServiceImpl authService;
    public UaaController(AuthServiceImpl authService) {
        this.authService = authService;
    }
    @PostMapping
    public ResponseEntity<?> login(@RequestBody UserRequest loginRequest) {
        var loginResponse = authService.login(loginRequest);
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }

    @PostMapping("/refreshToken")
    public UserResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return authService.refreshToken(refreshTokenRequest);
    }

}

