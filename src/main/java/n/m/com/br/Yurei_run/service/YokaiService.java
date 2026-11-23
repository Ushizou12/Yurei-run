package n.m.com.br.Yurei_run.service;

import n.m.com.br.Yurei_run.model.Yokai;
import n.m.com.br.Yurei_run.repository.YokaiRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class YokaiService {
    private final YokaiRepository yokaiRepository;

    public YokaiService(YokaiRepository yokaiRepository) {
        this.yokaiRepository = yokaiRepository;
    }

    public List<Yokai> listarTodos(){
        return yokaiRepository.findAll();
    }

    public Optional<Yokai> buscarPorId(long id){
        return yokaiRepository.findById(id);
    }

    public Yokai criar(Yokai yokai){
        return yokaiRepository.save(yokai);
    }

    public void excluir(long id){
        yokaiRepository.deleteById(id);
    }

    public Optional<Yokai> atualizar(Yokai yokai){
        buscarPorId(yokai.getId());
        return Optional.ofNullable(criar(yokai));
    }
}
