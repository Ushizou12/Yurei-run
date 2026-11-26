package n.m.com.br.Yurei_run.service;

import n.m.com.br.Yurei_run.model.Jogador;
import n.m.com.br.Yurei_run.repository.JogadorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class JogadorService {
    private final JogadorRepository jogadorRepository;

    public JogadorService(JogadorRepository jogadorRepository) {
        this.jogadorRepository = jogadorRepository;
    }

    @Transactional
    public Jogador acharOuCriar(String nickname){

        String nicknameLimpo = nickname.trim();

        return jogadorRepository.findByNickname(nicknameLimpo)
                .orElseGet(() -> {
                    Jogador novojogador = new Jogador();
                    novojogador.setNickname(nickname);
                    novojogador.setMelhorScore(0);
                    novojogador.setMoedas(0);
                    return jogadorRepository.save(novojogador);
                });
    }

    public List<Jogador> ranking(){
        return jogadorRepository.findAll();
    }
}
