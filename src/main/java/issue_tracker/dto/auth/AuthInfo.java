package issue_tracker.dto.auth;

import issue_tracker.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthInfo {
    private Long Id;

    private String firstname;

    private String lastname;

    private String email;

    private String password;

    private List<Role> roles;
}
