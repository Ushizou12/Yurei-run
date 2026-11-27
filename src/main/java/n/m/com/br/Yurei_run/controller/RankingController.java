package n.m.com.br.Yurei_run.controller;

import n.m.com.br.Yurei_run.dto.RankingItem;
import n.m.com.br.Yurei_run.service.JogadorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rankings")
public class RankingController {

    private final JogadorService jogadorService;

    public RankingController(JogadorService jogadorService) {
        this.jogadorService = jogadorService;
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
