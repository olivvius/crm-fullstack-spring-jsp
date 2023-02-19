package fr.m2i.spring.Service;

import fr.m2i.spring.model.Log;
import java.util.List;
import org.springframework.stereotype.Service;
import fr.m2i.spring.repository.LogRepository;

@Service
public class LogService {

    private LogRepository logRepository;

    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public List<Log> findAll() {
        return logRepository.findAll();
    }

    public Log findById(long id) {
        Log result= logRepository.findById(id).orElse(null);
             return result;
    }

      public Log create(Log log) {
        return logRepository.save(log);
    }

}
