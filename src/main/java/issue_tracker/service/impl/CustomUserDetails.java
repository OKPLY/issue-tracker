package issue_tracker.service.impl;


import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails extends org.springframework.security.core.userdetails.User {
    private Long id;

    public CustomUserDetails(UserDetails userDetails) {
        super(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}


