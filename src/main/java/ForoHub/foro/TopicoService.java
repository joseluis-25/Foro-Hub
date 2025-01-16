package ForoHub.foro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    public Topico crearTopico(Topico topico) {
        Optional<Topico> existingTopico = topicoRepository.findByTituloAndMensaje(topico.getTitulo(), topico.getMensaje());
        if (existingTopico.isPresent()) {
            throw new IllegalArgumentException("El tópico ya existe.");
        }
        return topicoRepository.save(topico);
    }

    public List<Topico> listarTopicos() {
        return topicoRepository.findAll();
    }

    public Optional<Topico> obtenerTopicoPorId(Long id) {
        return topicoRepository.findById(id);
    }

    public Topico actualizarTopico(Long id, Topico topicoActualizado) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Tópico no encontrado"));
        topico.setTitulo(topicoActualizado.getTitulo());
        topico.setMensaje(topicoActualizado.getMensaje());
        topico.setEstado(topicoActualizado.getEstado());
        topico.setAutor(topicoActualizado.getAutor());
        topico.setCurso(topicoActualizado.getCurso());
        return topicoRepository.save(topico);
    }

    public void eliminarTopico(Long id) {
        if (!topicoRepository.existsById(id)) {
            throw new NoSuchElementException("Tópico no encontrado");
        }
        topicoRepository.deleteById(id);
    }
}


