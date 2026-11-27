package n.m.com.br.Yurei_run.controller;

import n.m.com.br.Yurei_run.dto.JogadorRequest;
import n.m.com.br.Yurei_run.dto.RankingItem;
import n.m.com.br.Yurei_run.model.Jogador;
import n.m.com.br.Yurei_run.service.JogadorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jogadores")
public class JogadorController {

    private final JogadorService jogadorService;

    public JogadorController(JogadorService jogadorService) {
        this.jogadorService = jogadorService;
    }

    @PostMapping
    public ResponseEntity<Jogador> identificar(@RequestBody JogadorRequest request){
        Jogador jogador = jogadorService.acharOuCriar(request.nickname());
        return ResponseEntity.ok(jogador);
    }

    @GetMapping
    public List<RankingItem> ranking(){
        return jogadorService.ranking().stream()
                .map(jogador -> new RankingItem(
                    jogador.getNickname(),
                    jogador.getMelhorScore()
                ))
                .toList();
    }
}
