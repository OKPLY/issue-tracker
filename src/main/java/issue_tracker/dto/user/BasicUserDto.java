package issue_tracker.dto.user;


import issue_tracker.domain.Role;
import lombok.*;

import java.util.List;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class BasicUserDto {
    private String firstname;
    private String lastname;
    private String profilePicture;
    private List<Role> roles;
}
