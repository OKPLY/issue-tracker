package issue_tracker.dto.auth;

import issue_tracker.dto.user.BasicUserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private String token;
    private String message;
    private BasicUserDto user;
}
