package fr.m2i.spring.Service;

import fr.m2i.spring.exception.duplicateEmailException;
import fr.m2i.spring.model.Client;
import fr.m2i.spring.model.Log;
import fr.m2i.spring.model.Role;
import fr.m2i.spring.model.User;
import java.util.List;
import org.springframework.stereotype.Service;
import fr.m2i.spring.repository.UserRepository;
import java.util.Arrays;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserService implements IUserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private RoleService roleService;
    private LogService logService;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, RoleService roleService, LogService logService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.logService = logService;

        User adminUser = new User("admin", "admin", "admin@gmail.com", "admin");
        adminUser.setPassword(passwordEncoder.encode(adminUser.getPassword()));
        adminUser.setRoles(Arrays.asList(roleService.findByName("ROLE_USER"), roleService.findByName("ROLE_ADMIN")));
        userRepository.save(adminUser);

    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(long id) {
        User result = userRepository.findById(id).orElse(null);
        return result;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void create(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList(roleService.findByName("ROLE_USER")));
        logService.create(new Log("user", "user created", user.getEmail()));
        userRepository.save(user);
    }

    @Override
    public void delete(long id) {
        logService.create(new Log("user", "user deleted", this.findById(id).getEmail()));
        userRepository.deleteById(id);
    }

    public boolean isEmailUnique(String email) {
        User user = userRepository.getUserByEmail(email);
        return user == null;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return user;
    }

}
