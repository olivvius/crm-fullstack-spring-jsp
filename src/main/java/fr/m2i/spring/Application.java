package fr.m2i.spring;

import fr.m2i.spring.Service.ClientService;
import fr.m2i.spring.Service.PrestationService;
import fr.m2i.spring.Service.RoleService;
import fr.m2i.spring.Service.UserService;
import fr.m2i.spring.exception.duplicateEmailException;
import fr.m2i.spring.model.Client;
import fr.m2i.spring.model.Prestation;
import fr.m2i.spring.model.Role;
import fr.m2i.spring.model.User;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

// Equivalent de
// @SpringBootConfiguration
// @EnableAutoConfiguration
//@ComponentScan(basePackages = {"fr.m2i.spring.lesson", "fr.m2i.data"})
@SpringBootApplication
public class Application {

    public static void main(String[] args) throws duplicateEmailException {

        //demarrage de l'application
        ApplicationContext ctx = SpringApplication.run(Application.class, args);

        //appel des beans
        PrestationService prestationService = ctx.getBean(PrestationService.class);
        ClientService clientService = ctx.getBean(ClientService.class);
        UserService userService = ctx.getBean(UserService.class);
        RoleService roleService = ctx.getBean(RoleService.class);
        
        //creation des clients
        Client m2i = new Client("m2i", "actif");
        Client m3i = new Client("m3i", "inactif");
        //m2i.getPrestation().add(presta);
        clientService.create(m2i);
        clientService.create(m3i);

        //creation des prestations
        Prestation presta = new Prestation("formation JAVA pour les pros", 3, 1200, "OPTION");
        Prestation presta2 = new Prestation("formation JAVA", 6, 2400, "OPTION");
        presta.setClient(m3i);
        presta2.setClient(m2i);
        prestationService.create(presta);
        prestationService.create(presta2);

        //creation des users
        User user1 = new User("Alfred", "Mortimer", "alfred@gmail.com", "admin");
        User user2 = new User("John", "Duff", "john@gmail.com", "admin");
        userService.create(user1);
        userService.create(user2);
        
        /*
        // System.out.println(userService.isEmailUnique("john@gmail.com"));
        //User administrator = new User("Admin", "admin", "admin@gmail.com", "admin");

        //Role roleAdmin = new Role("ROLE_ADMIN");
       // Role roleUser = new Role("ROLE_USER");
        //roleService.create(roleAdmin);
        roleService.create(roleUser);

        List<Role> rolesAdmin = new ArrayList<>();
        //rolesAdmin.add(roleAdmin);
        rolesAdmin.add(roleUser);

        List<Role> rolesUser = new ArrayList<>();
        rolesUser.add(roleUser);

       // administrator.setRoles(rolesAdmin);
        user1.setRoles(rolesUser);
         */

        //userService.create(administrator);
    }
}
