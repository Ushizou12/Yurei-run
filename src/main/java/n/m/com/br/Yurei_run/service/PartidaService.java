package n.m.com.br.Yurei_run.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class PartidaService {

    @Transactional
    public Optional<ResultadoPartida> registrar(Long jogadorId, Integer distancia){
        if(distancia <= 0){
            return Optional.empty();
        }
    }
}
