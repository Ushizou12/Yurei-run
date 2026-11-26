package n.m.com.br.Yurei_run.service;

import n.m.com.br.Yurei_run.dto.ResultadoPartida;
import n.m.com.br.Yurei_run.model.Jogador;
import n.m.com.br.Yurei_run.model.Partida;
import n.m.com.br.Yurei_run.repository.JogadorRepository;
import n.m.com.br.Yurei_run.repository.PartidaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PartidaService {

    private final JogadorRepository jogadorRepository;
    private final PartidaRepository partidaRepository;
    private final Integer razaoMoedas;

    public PartidaService(JogadorRepository jogadorRepository,PartidaRepository partidaRepository,
            @Value("${app.moedas.razao}") Integer razaoMoedas) {
        this.jogadorRepository = jogadorRepository;
        this.partidaRepository = partidaRepository;
        this.razaoMoedas = razaoMoedas;
    }

    @Transactional
    public Optional<ResultadoPartida> registrar(Long jogadorId, Integer distancia) {
        if (distancia == null || distancia < 0) {
            throw new IllegalArgumentException("A distância não pode ser nula ou negativa.");
        }

        Optional<Jogador> jogadorOpt = jogadorRepository.findById(jogadorId);
        if (jogadorOpt.isEmpty()) {
            return Optional.empty();
        }

        Jogador jogador = jogadorOpt.get();

        Partida partida = new Partida();
        partida.setDistancia(distancia);
        partida.setJogador(jogador);
        partida.setData(LocalDateTime.now());
        partidaRepository.save(partida);

        Integer melhorScore = jogador.getMelhorScore();
        if (melhorScore == null || melhorScore < distancia) {
            jogador.setMelhorScore(distancia);
        }

        int moedasGanhas = distancia / razaoMoedas;
        int saldoAtual = jogador.getMoedas() != null ? jogador.getMoedas() : 0;
        jogador.setMoedas(saldoAtual + moedasGanhas);

        boolean novoRecorde = false;
        Integer melhorScoreAtual =+ jogador.getMelhorScore();

        if(melhorScoreAtual > melhorScore) {
            novoRecorde = true;
        }

        return Optional.of(new ResultadoPartida(
                distancia,
                moedasGanhas,
                jogador.getMoedas(),
                jogador.getMelhorScore(),
                novoRecorde
        ));
    }
}
