package fr.m2i.spring.Service;

import fr.m2i.spring.model.Client;
import fr.m2i.spring.model.Log;
import java.util.List;
import org.springframework.stereotype.Service;
import fr.m2i.spring.repository.ClientRepository;
import fr.m2i.spring.repository.LogRepository;

@Service
public class ClientService implements IClientService {

    private ClientRepository clientRepository;
    private LogService logService;

    public ClientService(ClientRepository clientRepository, LogService logService) {
        this.clientRepository = clientRepository;
        this.logService = logService;
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client findById(long id) {
        Client result = clientRepository.findById(id).orElse(null);
        return result;
    }

    @Override
    public Client create(Client client) {
        logService.create(new Log("client", "client created", client.getNom()));
        return clientRepository.save(client);
    }

    @Override
    public void delete(long id) {
        logService.create(new Log("client", "client deleted", this.findById(id).getNom()));
        clientRepository.deleteById(id);
    }

    public boolean isNomUnique(String nom) {
        Client client = clientRepository.findByNom(nom);
        return client == null;
    }
}
