package com.challenge.forohub.controller;

import com.challenge.forohub.domain.dto.DatosCrearRespuesta;
import com.challenge.forohub.domain.dto.DatosRespuesta;
import com.challenge.forohub.domain.model.respuesta.Respuesta;
import com.challenge.forohub.domain.model.tema.Tema;
import com.challenge.forohub.domain.model.usuario.Usuario;
import com.challenge.forohub.domain.repository.RespuestaRepository;
import com.challenge.forohub.domain.repository.TemaRepository;
import com.challenge.forohub.domain.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;

@Hidden
@RestController
@RequestMapping("api/respuestas")

//@SecurityRequirement(name = "bearer-key")
public class RespuestaController {

    private final
    UsuarioRepository usuarioRepository;

    private final
    TemaRepository temaRepository;

    private final
    RespuestaRepository respuestaRepository;

    @Autowired
    public RespuestaController(TemaRepository temaRepository, RespuestaRepository respuestaRepository,
                               UsuarioRepository usuarioRepository) {
        this.temaRepository = temaRepository;
        this.respuestaRepository = respuestaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    public ResponseEntity<DatosRespuesta> createRespuesta(@RequestBody DatosCrearRespuesta respuesta,
                                                          UriComponentsBuilder uriBuilder) {
        // TODO
        // solo se puede responder si el status del tema es abierto
        // solo se puede responder si existe y esta activo

        Tema tema = temaRepository.findById(respuesta.temaId())
                .orElseThrow(() -> new RuntimeException("Tema no encontrado"));

        Usuario usuario = usuarioRepository.findById(respuesta.autorId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Respuesta resultadoRespuesta = respuestaRepository.save(new Respuesta(null, respuesta.mensaje(),
                usuario,  LocalDateTime.now(), tema, false));
        //tema.setRespuestas(resultadoRespuesta);
        //temaRepository.update(tema);

        URI url = uriBuilder.path("/respuestas/{id}").buildAndExpand(tema.getId()).toUri();
        return ResponseEntity.created(url).body(new DatosRespuesta(resultadoRespuesta.getId(), resultadoRespuesta.getMensaje(),
                resultadoRespuesta.getFecha(), tema.getTitulo(), usuario.getNombre()));
    }

    // Traer todas las respuestas de un tema
    // Proxima implementacion

//    @GetMapping("tema/{id}")
//    public ResponseEntity<DatosListaRespuestaPorTema> getRespuestasByTema(@PathVariable Long id) {
//        List<Respuesta> respuestas = respuestaRepository.findAllByTemaId(id);
//
//        return ResponseEntity.ok().body(new DatosListaRespuestaPorTema(respuestas));
//    }

}
