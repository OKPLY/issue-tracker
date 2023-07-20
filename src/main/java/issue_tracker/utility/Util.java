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
        var context = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(context.equals("anonymousUser"))
            return null;

        var customUser = (CustomUserDetails) context;
        Long id = customUser.getId();

        var user = userRepo.findById(id);
        return user.get();
    }

}
