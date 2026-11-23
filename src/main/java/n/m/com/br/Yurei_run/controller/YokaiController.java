package n.m.com.br.Yurei_run.controller;

import n.m.com.br.Yurei_run.model.Yokai;
import n.m.com.br.Yurei_run.service.YokaiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController
@RequestMapping("/api/yokais")
public class YokaiController {

    private final YokaiService service;

    public YokaiController(YokaiService service) {
        this.service = service;
    }

    @GetMapping
    public List<Yokai> listar() {
        return service.listarTodos();
    }
}
