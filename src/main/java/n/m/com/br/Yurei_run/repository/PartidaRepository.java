package n.m.com.br.Yurei_run.repository;

import n.m.com.br.Yurei_run.model.Partida;

import java.util.List;

public interface PartidaRepository {
    List<Partida> findByJogadorId(Long jogadorId);
}
