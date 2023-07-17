package issue_tracker.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateUser {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String profilePicture;

}
