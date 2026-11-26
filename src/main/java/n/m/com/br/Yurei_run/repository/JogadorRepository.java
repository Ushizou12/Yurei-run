package n.m.com.br.Yurei_run.repository;

import n.m.com.br.Yurei_run.model.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JogadorRepository extends JpaRepository<Jogador, Long> {
    Optional<Jogador> findByNickname(String nickname);
    List<Jogador> findTop10ByMelhorScoreIsNotNullOrderByMelhorScoreDesc();
}
