package issue_tracker.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import issue_tracker.domain.User;
import issue_tracker.dto.aggregation.CreatedResolvedReviewedAggregate;
import issue_tracker.dto.user.BasicUserDto;
import issue_tracker.service.UserService;
import issue_tracker.utility.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@CrossOrigin
public class UserController {

    private final UserService userService;
    private final Util util;

    @GetMapping("/aggregate")
    public ResponseEntity<CreatedResolvedReviewedAggregate> aggregate() {
        return ResponseEntity.ok(userService.currentUserAggregate());
    }

    @GetMapping("/current")
    public ResponseEntity<BasicUserDto> getUser() {
        return ResponseEntity.ok(userService.getCurrentUser());
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/recentUsers")
    public ResponseEntity<List<User>> getRecentUsers(@RequestParam(required = false) Integer limit) {
        return ResponseEntity.ok(userService.getRecentUsers(limit == null ? 10 : limit));
    }

}
