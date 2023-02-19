package fr.m2i.spring.Service;

import fr.m2i.spring.model.Prestation;
import java.util.List;


public interface IPrestationService {

    public List<Prestation> findAll();
    
    public Prestation findById(long id);
    
    public void create(Prestation todo);

    public void delete(long id);
    
   // public void update(long id, Todo todo);
    
}
