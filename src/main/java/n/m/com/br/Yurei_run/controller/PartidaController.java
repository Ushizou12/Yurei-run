package n.m.com.br.Yurei_run.controller;

import n.m.com.br.Yurei_run.dto.PartidaRequest;
import n.m.com.br.Yurei_run.dto.ResultadoPartida;
import n.m.com.br.Yurei_run.service.PartidaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/partidas")
public class PartidaController {

    private final PartidaService partidaService;

    public PartidaController(PartidaService partidaService) {
        this.partidaService = partidaService;
    }

    @PostMapping
    public ResponseEntity<ResultadoPartida> registrar(@RequestBody PartidaRequest request){
        return partidaService.registrar(request.jogadorId(), request.distancia())
                .map(r -> ResponseEntity.status(HttpStatus.CREATED).body(r))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
