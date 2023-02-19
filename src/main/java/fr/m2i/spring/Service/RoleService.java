package fr.m2i.spring.Service;

import fr.m2i.spring.model.Role;
import fr.m2i.spring.repository.RoleRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import fr.m2i.spring.repository.UserRepository;

@Service
public class RoleService {

    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");
        this.create(roleAdmin);
        this.create(roleUser);
        
    }

    public Role create(Role role) {
        return roleRepository.save(role);
    }

    public void delete(long id) {
        roleRepository.deleteById(id);
    }
     public Role findByName(String name){
         return roleRepository.findByName(name);
     }
    
}
