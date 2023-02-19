package fr.m2i.spring.Service;

import fr.m2i.spring.model.Client;
import fr.m2i.spring.model.Prestation;
import java.util.List;


public interface IClientService {

    public List<Client> findAll();
    
    public Client findById(long id);
    
    public Client create(Client client);

    public void delete(long id);
    
   // public void update(long id, Todo todo);
    
}
