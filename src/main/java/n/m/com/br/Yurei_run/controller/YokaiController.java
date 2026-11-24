package n.m.com.br.Yurei_run.controller;

import n.m.com.br.Yurei_run.model.Yokai;
import n.m.com.br.Yurei_run.service.YokaiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<YokaiService> buscarPorId(@PathVariable Long id){
        return service.buscarPorId(id)
                .map(yokai -> ResponseEntity.ok(service))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Yokai> criar(@RequestBody Yokai yokai) {
        Yokai objetoSalvo = service.criar(yokai);
        return ResponseEntity.status(HttpStatus.CREATED).body(objetoSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Yokai> atualizar(@PathVariable Long id, @RequestBody Yokai yokai) {
        service.atualizar(id, yokai);
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        // chame service.excluir(id)
        Boolean deletado = service.excluir(id);

        if (deletado){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
