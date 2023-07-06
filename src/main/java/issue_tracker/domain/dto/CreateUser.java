package issue_tracker.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateUser {
    private String firstname;
    private String lastname;
    private String email;
    private String password;


}
