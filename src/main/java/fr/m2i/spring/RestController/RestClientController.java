package fr.m2i.spring.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.m2i.spring.Service.ClientService;
import fr.m2i.spring.model.Client;
import java.util.Optional;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/clients")
public class RestClientController {

    private final ClientService clientService;

    @Autowired
    public RestClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Returns the list of all clients", nickname = "Get all clients", response = Client.class)
    public ResponseEntity getAllClients() {

        try {
            List<Client> clients = this.clientService.findAll();
            return ResponseEntity.ok(new ObjectMapper().writeValueAsString(clients));
        } catch (JsonProcessingException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
    }
    
    @ApiOperation(value = "Returns the client with given id", nickname = "Get a client", response = Client.class)
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity getById(@PathVariable("id") Long id) {
        Client client;
        try {
            client = this.clientService.findById(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
        if (client== null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found.");
        }
        try {
            return ResponseEntity.ok(new ObjectMapper().writeValueAsString(client));
        } catch (JsonProcessingException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Creates a new client", nickname = "Create a client")
    public ResponseEntity create(@RequestBody Client client) {
        try {
            this.clientService.create(client);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Client successfully created.");
    }
    
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletes the client with given id", nickname = "Delete client")
    public ResponseEntity delete(@PathVariable("id") Long id, Authentication authentification) {
        Client client;
        try {
            client = this.clientService.findById(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
        if (client == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found.");
        }
        this.clientService.delete(id);
        return ResponseEntity.ok("Client successfully deleted.");
    }
    

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Updates the client with given id", nickname = "Update client")
    public ResponseEntity update(@RequestBody Client client, @PathVariable("id") Long id, Authentication authentification) {
        try {
            this.clientService.create(client);
        } catch (NotFoundException nfe) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
        return ResponseEntity.ok("Client successfully modified.");
    }

}
