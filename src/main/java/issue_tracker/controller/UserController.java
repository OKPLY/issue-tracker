package issue_tracker.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import issue_tracker.dto.aggregation.CreatedResolvedReviewedAggregate;
import issue_tracker.dto.user.BasicUserDto;
import issue_tracker.service.UserService;
import issue_tracker.utility.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class UserController {

    private final UserService userService;
    private final Util util;
   @GetMapping("/aggregate")
    public ResponseEntity<CreatedResolvedReviewedAggregate> aggregate() {
       return ResponseEntity.ok(userService.currentUserAggregate());
   }

   //TODO: Make a dto to return only the necessary fields
    @GetMapping
    public ResponseEntity<BasicUserDto> getUser() {
        return ResponseEntity.ok(userService.getCurrentUser());
    }
}
