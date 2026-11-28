package n.m.com.br.Yurei_run.service;

import n.m.com.br.Yurei_run.model.Skin;
import n.m.com.br.Yurei_run.repository.SkinRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SkinService {

    private final SkinRepository skinRepository ;

    public SkinService(SkinRepository skinRepository) {
        this.skinRepository = skinRepository;
    }

    public Optional<Skin> buscarPorId(Long id) {
        return skinRepository.findById(id);
    }

    public List<Skin> listar(Skin skin) {
        return skinRepository.findAll();
    }

    @Transactional
    public Skin salvar(Skin skin) {

        boolean nomeJaExiste = false;

        if (skin.getId() == null) {
            nomeJaExiste = skinRepository.existsByNome(skin.getNome());
        } else {
            nomeJaExiste = skinRepository.existsByNomeAndIdNot(skin.getNome(), skin.getId());
        }

        if (nomeJaExiste) {
            throw new IllegalArgumentException("Já existe uma skin com esse nome.");
        }

        return skinRepository.save(skin);
    }

        public boolean excluir(Long id) {
            if(skinRepository.existsById(id)) {
                skinRepository.deleteById(id);
                return true;
            }
            return false;
        }
    }

