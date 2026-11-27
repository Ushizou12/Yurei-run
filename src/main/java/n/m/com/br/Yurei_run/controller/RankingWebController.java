package n.m.com.br.Yurei_run.controller;

import n.m.com.br.Yurei_run.service.JogadorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ranking")
public class RankingWebController {
    // construtor recebendo o JogadorService (igual ao que você já faz)

    JogadorService jogadorService;

    public RankingWebController(JogadorService jogadorService) {
        this.jogadorService = jogadorService;
    }

    @GetMapping
    public String top10(Model model) {
        model.addAttribute("ranking", jogadorService.ranking());
        return "ranking";
    }
}
