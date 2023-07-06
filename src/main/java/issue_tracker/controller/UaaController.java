package issue_tracker.controller;



import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import issue_tracker.domain.User;
import issue_tracker.domain.dto.CreateUser;
import issue_tracker.domain.dto.UserRequest;
import issue_tracker.domain.dto.UserResponse;
import issue_tracker.service.UserService;
import issue_tracker.service.impl.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/uaa")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class UaaController {
    private final AuthServiceImpl authService;
    private final UserService userService;



    @PostMapping ("/login")
    public ResponseEntity<?> login(@RequestBody UserRequest loginRequest) {
        var loginResponse = authService.login(loginRequest);
        return new ResponseEntity<UserResponse>(
                loginResponse, HttpStatus.OK);
    }
    @PostMapping("/signup")
    public User signUp(@RequestBody CreateUser user) {
    return  userService.createUser(user);
    }



}

