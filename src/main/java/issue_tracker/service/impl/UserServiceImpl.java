package issue_tracker.service.impl;

import issue_tracker.domain.User;
import issue_tracker.dto.auth.CreateUser;
import issue_tracker.dto.aggregation.CreatedResolvedReviewedAggregate;
import issue_tracker.repository.IssueRepo;
import issue_tracker.repository.RoleRepo;
import issue_tracker.repository.UserRepo;
import issue_tracker.service.UserService;
import issue_tracker.utility.Util;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {


    private final BCryptPasswordEncoder pwdEncoder;
    private final UserRepo userRepository;
    private final IssueRepo issueRepo;
    private final RoleRepo roleRepo;
    private final ModelMapper modelMapper;
    private final Util util;



    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    public User createUser(CreateUser user) {

//        if(user.getRoles().isEmpty()){
//            user.addRole(roleRepo.getByName("Creator"));
//        }
        user.setPassword(pwdEncoder.encode(user.getPassword()));
        User newUser = modelMapper.map(user,User.class);
        newUser.addRole(roleRepo.getByName("Creator"));
        return userRepository.save(newUser);
    }

    @Override
    public User updateUser(Long id, User user) {

        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(pwdEncoder.encode(user.getPassword()));
            return userRepository.save(existingUser);
        }
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }
    public Optional<User> findUserByEmail(String userName){
        return userRepository.findByEmail(userName);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> opt = findUserByEmail(email);
        User user= opt.get();
        if(opt.isEmpty()){
            throw new UsernameNotFoundException(" user doesn't exist");}
        UserDetails userDetails= new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getRoles().stream()
                        .flatMap(role -> role.getPermissions().stream())
                        .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                        .collect(Collectors.toSet()));

        CustomUserDetails customUserDetails = new CustomUserDetails(userDetails);
        customUserDetails.setId(user.getId());

        return customUserDetails;
    }

    @Override
    public CreatedResolvedReviewedAggregate currentUserAggregate(){
        User user = util.getUserFromContext();
        Long created = issueRepo.getCreationAggregate(user);
        Long resolved = issueRepo.getResolveAggregate(user);
        Long reviewed = issueRepo.getReviewAggregate(user);

        return new CreatedResolvedReviewedAggregate(created, resolved, reviewed);
    }
}

