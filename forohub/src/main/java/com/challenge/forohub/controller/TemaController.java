package com.challenge.forohub.controller;

import com.challenge.forohub.dto.DatosRegistroTema;
import com.challenge.forohub.dto.DatosRespuestaTema;
import com.challenge.forohub.dto.DatosActualizarTema;
import com.challenge.forohub.model.curso.Curso;
import com.challenge.forohub.model.tema.Tema;
import com.challenge.forohub.model.usuario.Usuario;
import com.challenge.forohub.repository.CursoRepository;
import com.challenge.forohub.repository.TemaRepository;
import com.challenge.forohub.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("api/temas")
public class TemaController {

    final
    TemaRepository temaRepository;
    final
    CursoRepository cursoRepository;
    final
    UsuarioRepository usuarioRepository;

    public TemaController(TemaRepository temaRepository, CursoRepository cursoRepository, UsuarioRepository usuarioRepository) {
        this.temaRepository = temaRepository;
        this.cursoRepository = cursoRepository;
        this.usuarioRepository = usuarioRepository;
    }



    @PostMapping
    public ResponseEntity<DatosRespuestaTema> create(@RequestBody @Valid DatosRegistroTema body, UriComponentsBuilder uriBuilder) {
        Curso curso = cursoRepository.findByNombre(body.nombreCurso()).get();
        Usuario usuario = usuarioRepository.findById(body.autorId()).get();
        Tema temaGuardado = temaRepository.save(new Tema(curso, usuario,body));

        URI url = uriBuilder.path("/api/temas/{id}").buildAndExpand(temaGuardado.getId()).toUri();
        DatosRespuestaTema respuestaTema = new DatosRespuestaTema(temaGuardado);

        return ResponseEntity.created(url).body(respuestaTema);
    }

    @GetMapping
    public ResponseEntity<Page<DatosRespuestaTema>> list(@PageableDefault(size = 5) Pageable page) {
        System.out.println("Listing all topics");
        return ResponseEntity.ok(temaRepository.findByActivoTrue(page).map(tema -> new DatosRespuestaTema(tema.getId(), tema.getTitulo(),
                tema.getMensaje(), tema.getFecha(), tema.getStatus(), tema.getCursoId().getNombre(), tema.getAutorId().getNombre())));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTema> get(@PathVariable Long id) {
        //corregir el tema del optional
        Tema tema = temaRepository.findById(id).get();
        DatosRespuestaTema respuestaTema = new DatosRespuestaTema(tema);
        return ResponseEntity.ok(respuestaTema);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaTema> update(@RequestBody @Valid DatosActualizarTema body) {
        //realizar validacion si existe el tema
        Optional<Tema> tema = temaRepository.findById(body.id());
        tema.get().actualizardatos(body);

        return ResponseEntity.ok(new DatosRespuestaTema(tema.get()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Tema tema = temaRepository.findById(id).get();
        tema.desactivarTema();
        return ResponseEntity.noContent().build();
    }
}
