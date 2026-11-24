package n.m.com.br.Yurei_run.service;

import n.m.com.br.Yurei_run.model.Yokai;
import n.m.com.br.Yurei_run.repository.YokaiRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class YokaiService {
    private final YokaiRepository yokaiRepository;

    public YokaiService(YokaiRepository yokaiRepository) {
        this.yokaiRepository = yokaiRepository;
    }

    public List<Yokai> listarTodos() {
        return yokaiRepository.findAll();
    }

    public Optional<Yokai> buscarPorId(Long id) {
        return yokaiRepository.findById(id);
    }

    @Transactional
    public Yokai criar(Yokai yokai) {
        yokai.setId(null);
        return yokaiRepository.save(yokai);
    }

    @Transactional
    public Optional<Yokai> atualizar(Long id, Yokai dadosNovos) {

        return buscarPorId(id).map(existente -> {
            existente.setNome(dadosNovos.getNome());
            existente.setDescricao(dadosNovos.getDescricao());
            existente.setVelocidade(dadosNovos.getVelocidade());
            existente.setSprite(dadosNovos.getSprite());
            existente.setAtivo(dadosNovos.getAtivo());

            return yokaiRepository.save(existente);
        });
    }

    @Transactional
    public boolean excluir(Long id) {
        if (yokaiRepository.existsById(id)) {
            yokaiRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
