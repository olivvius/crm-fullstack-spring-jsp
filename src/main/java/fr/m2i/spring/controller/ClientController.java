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
public class ClientController {

    //Attribut de la classe
    private PrestationRepository prestationRepository;
    private PrestationService prestationService;
    private ClientService clientService;
    private ClientRepository clientRepository;

    //Injection des dépendences
    @Autowired
    public ClientController(PrestationRepository prestationRepository, PrestationService prestationService, ClientRepository clientRepository, ClientService clientService) {
        this.prestationRepository = prestationRepository;
        this.prestationService = prestationService;
        this.clientRepository = clientRepository;
        this.clientService = clientService;
    }

    // routage pour l'affichage de la liste des clients
    @GetMapping("/list-clients")
    public String getClientList(ModelMap model) {
        return "list-clients";
    }

    //recuperation de liste des clients et injection dans un attribut de model
    @ModelAttribute("listClients")
    public List<Client> listClients() {
        return clientService.findAll();
    }

    //creation d'une instance de client en tant qu'attribut de modele
    @ModelAttribute("delete-client")
    public Client delete() {
        return new Client();
    }

    //routage en POST pour suppression d'un client avec redirection vers la liste des clients actualisée
    @PostMapping("/delete-client")
    public String deleteClient(@ModelAttribute("delete") Client client) {
        clientService.delete(client.getId());
        return "redirect:list-clients";
    }

    //creation d'une instance de client en tant qu'attribut de modele
    @ModelAttribute("modify-client")
    public Client modifyClient() {
        return new Client();
    }

    //ajout d'un attribut au modele, le client recupéré par son id
    @GetMapping("/modify-client")
    public String modify(@RequestParam("id") int id, ModelMap model) {
        model.addAttribute("modify-client", clientService.findById(id));
        return "modify-client";
    }

    //routage POST, recup client et inejction en BDD, renvoi vers liste clients reactualisée
    @PostMapping("/modify-client")
    public String modifyClient(@ModelAttribute("modif-client") @Valid Client client, BindingResult result) {
        if (!clientService.isNomUnique(client.getNom())) {
            result.rejectValue("nom", null, "ce client existe deja!");
        }
        if (result.hasErrors()) {
            return "list-clients";
        }
        clientService.create(client);
        return "list-clients";
    }

    //routage GET vers la page d'ajout de client et ajout d'un client en tant qu'attribut du model
    @GetMapping("/add-client")
    public String addPrestation(ModelMap modelclient) {
        modelclient.addAttribute("add-client", new Client());
        return "add-client";
    }

    //routage POST pour recuperer le client créé et injection dans la BDD via le service
    @PostMapping("/add-client")
    public String createClient(@ModelAttribute("add-client") @Valid Client client, BindingResult result) {
        if (!clientService.isNomUnique(client.getNom())) {
            result.rejectValue("nom", null, "ce client existe deja!");
        }
        if (result.hasErrors()) {
            return "add-client";
        }
        this.clientService.create(client);
        return "redirect:list-clients";
    }

}
