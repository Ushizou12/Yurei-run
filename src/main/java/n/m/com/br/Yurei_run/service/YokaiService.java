package n.m.com.br.Yurei_run.service;

import n.m.com.br.Yurei_run.repository.YokaiRepository;
import org.springframework.stereotype.Service;

@Service
public class YokaiService {
    private final Object YokaiRepository;

    public YokaiService(YokaiRepository yokaiRepository) {
        this.YokaiRepository = yokaiRepository;
    }
}
