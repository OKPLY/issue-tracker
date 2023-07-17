package issue_tracker.dto.user;


import issue_tracker.domain.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BasicUserDto {
    private String firstname;
    private String lastname;
    private String profilePicture;
    private List<Role> roles;
}
