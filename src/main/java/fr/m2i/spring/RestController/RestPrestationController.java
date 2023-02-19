package fr.m2i.spring.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.m2i.spring.Service.PrestationService;
import fr.m2i.spring.model.Prestation;
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
@RequestMapping("/api/prestations")
public class RestPrestationController {

    private final PrestationService prestationService;

    @Autowired
    public RestPrestationController(PrestationService prestationService) {
        this.prestationService = prestationService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Returns the list of all prestations", nickname = "Get all prestations", response = Prestation.class)
    public ResponseEntity getAllPrestations() {

        try {
            List<Prestation> prestations = this.prestationService.findAll();
            return ResponseEntity.ok(new ObjectMapper().writeValueAsString(prestations));
        } catch (JsonProcessingException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
    }
    
    @ApiOperation(value = "Returns the prestation with given id", nickname = "Get a prestation", response = Prestation.class)
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity getById(@PathVariable("id") Long id) {
        Prestation prestation;
        try {
            prestation = this.prestationService.findById(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
        if (prestation== null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Prestation not found.");
        }
        try {
            return ResponseEntity.ok(new ObjectMapper().writeValueAsString(prestation));
        } catch (JsonProcessingException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Creates a new prestation", nickname = "Create a prestation")
    public ResponseEntity create(@RequestBody Prestation prestation) {
        try {
            this.prestationService.create(prestation);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Prestation successfully created.");
    }
    
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletes the prestation with given id", nickname = "Delete prestation")
    public ResponseEntity delete(@PathVariable("id") Long id, Authentication authentification) {
        Prestation prestation;
        try {
            prestation = this.prestationService.findById(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
        if (prestation == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Prestation not found.");
        }
        this.prestationService.delete(id);
        return ResponseEntity.ok("Prestation successfully deleted.");
    }
    

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Updates the prestation with given id", nickname = "Update prestation")
    public ResponseEntity update(@RequestBody Prestation prestation, @PathVariable("id") Long id, Authentication authentification) {
        try {
            this.prestationService.create(prestation);
        } catch (NotFoundException nfe) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Prestation not found.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
        return ResponseEntity.ok("Prestation successfully modified.");
    }

}
