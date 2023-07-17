package issue_tracker.dto.user;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasicUserDto {
    private String firstname;
    private String lastname;
    private String profilePicture;
}
