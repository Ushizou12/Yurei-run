package n.m.com.br.Yurei_run.repository;

import n.m.com.br.Yurei_run.model.Jogador;

import java.util.Optional;

public interface JogadorRepository {
    Optional<Jogador> findByNickname(String nickname);
}
