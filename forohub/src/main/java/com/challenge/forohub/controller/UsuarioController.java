package com.challenge.forohub.controller;

import com.challenge.forohub.dto.DatosRegistroUsuario;
import com.challenge.forohub.dto.DatosRespuestaUsuario;
import com.challenge.forohub.model.usuario.Usuario;
import com.challenge.forohub.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/usuarios")
public class UsuarioController {

    private final
    UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    @PostMapping
    public ResponseEntity<DatosRespuestaUsuario> create(@RequestBody @Valid DatosRegistroUsuario datos,
                                                        UriComponentsBuilder uriBuilder) {
        Usuario usuario = usuarioRepository.save(new Usuario(datos));
        DatosRespuestaUsuario respuesta = new DatosRespuestaUsuario(usuario.getId(), usuario.getNombre(),
                usuario.getCorreoElectronico());
        URI url = uriBuilder.path("/api/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(url).body(respuesta);
    }

    @GetMapping
    public ResponseEntity<Page<DatosRespuestaUsuario>> userList(@PageableDefault(size = 5,
            sort = {"nombre"}) Pageable page) {
        Page<Usuario> usuarios = usuarioRepository.findAll(page);

        return ResponseEntity.ok(usuarios.map(usuario -> new DatosRespuestaUsuario(usuario.getId(),
                usuario.getNombre(), usuario.getCorreoElectronico())));
    }
}
