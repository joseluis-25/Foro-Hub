package ForoHub.foro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    public ResponseEntity<Topico> registrarTopico(@Valid @RequestBody Topico topico) {
        Topico creado = topicoService.crearTopico(topico);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Topico>> obtenerTodosLosTopicos() {
        return new ResponseEntity<>(topicoService.listarTopicos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topico> obtenerTopicoPorId(@PathVariable Long id) {
        Optional<Topico> topico = topicoService.obtenerTopicoPorId(id);
        return topico.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Topico> actualizarTopico(@PathVariable Long id, @Valid @RequestBody Topico topico) {
        return new ResponseEntity<>(topicoService.actualizarTopico(id, topico), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        topicoService.eliminarTopico(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

