package n.m.com.br.Yurei_run.repository;

import n.m.com.br.Yurei_run.model.Partida;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartidaRepository extends JpaRepository<Partida, Long> {
    List<Partida> findByJogadorId(Long jogadorId);
}
