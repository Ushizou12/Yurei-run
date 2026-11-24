package n.m.com.br.Yurei_run.service;

import jakarta.transaction.Transactional;
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

    public Optional<Yokai> buscarPorId(Long id){
        return yokaiRepository.findById(id);
    }

    @Transactional
    public Yokai criar(Yokai yokai) {
        yokai.setId(null);
        yokaiRepository.save(yokai);
        return null;
    }

    @Transactional
    public Optional<Yokai> atualizar(Long id, Yokai dadosNovos) {
        // 1. busque o Yokai existente pelo id
        if(buscarPorId(id) != null){
            Yokai dados = dadosNovos;
            return Optional.of(yokaiRepository.save(dados));
        }
        return Optional.empty();
    }

    @Transactional
    public boolean excluir(Long id) {
        // 1. confira se existe (existsById)
        if(yokaiRepository.existsById(id)){
            yokaiRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
