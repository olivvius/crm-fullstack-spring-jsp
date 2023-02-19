package fr.m2i.spring.controller;

import fr.m2i.spring.Service.ClientService;
import fr.m2i.spring.Service.PrestationService;
import fr.m2i.spring.model.Client;
import java.util.List;
import fr.m2i.spring.model.Prestation;
import fr.m2i.spring.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import fr.m2i.spring.repository.PrestationRepository;
import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.validation.BindingResult;

@Controller
public class PrestationController {

    //Attribut de la classe
    private PrestationRepository prestationRepository;
    private PrestationService prestationService;
    private ClientService clientService;
    private ClientRepository clientRepository;

    //Injection des dépendences
    @Autowired
    public PrestationController(PrestationRepository prestationRepository, PrestationService prestationService, ClientRepository clientRepository, ClientService clientService) {
        this.prestationRepository = prestationRepository;
        this.prestationService = prestationService;
        this.clientRepository = clientRepository;
        this.clientService = clientService;
    }

    // routage pour l'affichage de l'index
    @GetMapping("/")
    public String root() {
        return "index";
    }
    
        // routage pour l'affichage de l'index
    @GetMapping("/index")
    public String root2() {
        return "index";
    }

    // routage pour l'affichage de la liste des prestations
    @GetMapping("/list-prestations")
    public String getprestationList(ModelMap model) {
        return "list-prestations";
    }

    //recuperation de liste des prestations et injection dans un attribut de model
    @ModelAttribute("listPrestations")
    public List<Prestation> listpresta() {
        return prestationService.findAll();
    }
    
        //recuperation de liste des clients et injection dans un attribut de model
    @ModelAttribute("listClients")
    public List<Client> listClients() {
        return clientService.findAll();
    }

    //creation d'une instance de prestation en tant qu'attribut de modele
    @ModelAttribute("delete")
    public Prestation delete() {
        return new Prestation();
    }

    //routage en POST pour suppression d'une prsetation avec redirection vers la liste des clients actualisée
    @PostMapping("/delete-prestation")
    public String deleteClient(@ModelAttribute("delete") Prestation presta) {
        prestationService.delete(presta.getId());
        return "redirect:list-prestations";
    }

    //creation d'une instance de prestation en tant qu'attribut de modele
    @ModelAttribute("modify")
    public Prestation modify() {
        return new Prestation();
    }

    //ajout d'un attribut au modele, la prestation recupérée par son id
    @GetMapping("/modify-prestation")
    public String modify(@RequestParam("id") int id, ModelMap model) {
        model.addAttribute("modify", prestationService.findById(id));
        return "modify-prestation";
    }

    //routage POST, recup  prestation et inejction en BDD, renvoi vers liste prestations reactualisée
    @PostMapping("/modify-prestation")
    public String modifyPrestation(@ModelAttribute("modify") @Valid Prestation prestation) {
        /*
        Prestation presta = new Prestation();
        presta.setDescription(prestation.getDescription());
        presta.setEtat(presta.getEtat());
        prestation.setPrix((int) presta.getPrix());
        prestation.setDuree((int) presta.getDuree());
         */
        prestationService.create(prestation);
        return "list-prestations";
    }

    //routage GET vers la page d'ajout de prestation et ajout d'une prstation en tant qu'attribut du model
    @GetMapping("/add-prestation")
    public String addPrestation(ModelMap model) {
        model.addAttribute("add-presta", new Prestation());
        return "add-prestation";
    }

    //routage POST pour recuperer la prstation créée et injection dans la BDD via le service
    @PostMapping("/add-prestation")
    public String createPrestation(@ModelAttribute("add-presta") @Valid Prestation prestation, BindingResult result) {
        if (result.hasErrors()) {
            return "add-prestation";
        }
        this.prestationService.create(prestation);

        return "redirect:list-prestations";
    }
}
