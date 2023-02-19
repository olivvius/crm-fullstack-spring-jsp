package fr.m2i.spring.controller;

import fr.m2i.spring.Service.UserService;
import fr.m2i.spring.exception.duplicateEmailException;
import java.util.List;
import fr.m2i.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import fr.m2i.spring.repository.UserRepository;
import javax.validation.Valid;
import javax.xml.ws.Response;
import org.springframework.validation.BindingResult;

@Controller
public class AdminController {

    //Attribut de la classe
    private UserRepository userRepository;
    private UserService userService;

    //Injection des dépendences
    @Autowired
    public AdminController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    // routage pour l'affichage de la liste des clients
    @GetMapping("/list-users")
    public String getUserList(ModelMap model) {

        return "list-users";
    }

    //recuperation de liste des clients et injection dans un attribut de model
    @ModelAttribute("listUsers")
    public List<User> listUsers() {
        return userService.findAll();
    }

    //creation d'une instance de client en tant qu'attribut de modele
    @ModelAttribute("delete-user")
    public User delete() {
        return new User();
    }

    //routage en POST pour suppression d'un user avec redirection vers la liste des users actualisée
    @PostMapping("/delete-user")
    public String deleteUser(@ModelAttribute("delete") User user) {
        userService.delete(user.getId());
        return "redirect:list-users";
    }

    //creation d'une instance de client en tant qu'attribut de modele
    @ModelAttribute("add-user")
    public User addUser() {
        return new User();
    }

    //routage GET vers la page d'ajout de client et ajout d'un client en tant qu'attribut du model
    @GetMapping("/add-user")
    public String addPrestation(ModelMap model) {
        return "add-user";
    }

    //routage POST pour recuperer l'user créé et injection dans la BDD via le service
    @PostMapping("/add-user")
    public String createUser(@ModelAttribute("add-user") @Valid User user, BindingResult result) {
        if (!userService.isEmailUnique(user.getEmail())) {
            result.rejectValue("email", null, "cet email existe deja!");
        }
        if (result.hasErrors()) {
            return "add-user";
        }
        this.userService.create(user);
        return "redirect:list-users";
    }

}
