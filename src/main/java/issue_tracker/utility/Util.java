package issue_tracker.utility;

import issue_tracker.domain.User;
import issue_tracker.repository.UserRepo;
import issue_tracker.service.impl.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Util {

    private final UserRepo userRepo;

    public User getUserFromContext(){
        var customUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long id = customUser.getId();

        var user = userRepo.findById(id);
        return user.get();
    }

}
