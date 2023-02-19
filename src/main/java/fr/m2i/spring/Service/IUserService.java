package fr.m2i.spring.Service;

import fr.m2i.spring.exception.duplicateEmailException;
import fr.m2i.spring.model.Client;
import fr.m2i.spring.model.Prestation;
import fr.m2i.spring.model.User;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface IUserService  extends UserDetailsService {

    public List<User> findAll();
    
    public User findById(long id);
    
    public void create(User user) throws duplicateEmailException;

    public void delete(long id);
    
   // public void update(long id, Todo todo);
    
}
