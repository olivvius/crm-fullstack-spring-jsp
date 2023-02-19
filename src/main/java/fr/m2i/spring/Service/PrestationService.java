package fr.m2i.spring.Service;

import fr.m2i.spring.model.Log;
import fr.m2i.spring.model.Prestation;
import fr.m2i.spring.repository.LogRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import fr.m2i.spring.repository.PrestationRepository;

@Service
public class PrestationService implements IPrestationService {

    private PrestationRepository prestationRepository;
    private LogService logService;

    public PrestationService(PrestationRepository prestationRepository, LogService logService) {
        this.prestationRepository = prestationRepository;
        this.logService = logService;
    }

    @Override
    public List<Prestation> findAll() {
        return prestationRepository.findAll();
    }

    @Override
    public Prestation findById(long id) {
        Prestation result = prestationRepository.findById(id).orElse(null);
        return result;
    }

    @Override
    public void create(Prestation prestation) {
        prestationRepository.save(prestation);
        logService.create(new Log("prestation", "prestation created", prestation.getDescription()));
    }

    @Override
    public void delete(long id) {
        logService.create(new Log("prestation", "prestation deleted", this.findById(id).getDescription()));
        prestationRepository.deleteById(id);
    }

}
