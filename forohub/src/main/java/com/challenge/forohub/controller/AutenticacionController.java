package com.challenge.forohub.controller;

import com.challenge.forohub.domain.dto.DatosLoginUsuario;
import com.challenge.forohub.domain.dto.DatosRegistroUsuario;
import com.challenge.forohub.domain.dto.DatosRespuestaRegistroUsuario;
import com.challenge.forohub.domain.dto.JWTtoken;
import com.challenge.forohub.domain.service.RegistroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/auth")
public class AutenticacionController {

    private final
    RegistroService registroService;

    @Autowired
    public AutenticacionController(RegistroService registroService) {
        this.registroService = registroService;
    }


    @PostMapping("registro")
    public ResponseEntity <DatosRespuestaRegistroUsuario> registroUsuario(@RequestBody @Valid DatosRegistroUsuario datosUsuario,
                                                                          UriComponentsBuilder uriBuilder) {
        DatosRespuestaRegistroUsuario usuarioRegistrado = registroService.regitrarUsuario(datosUsuario);
        URI url = uriBuilder.path("/api/usuarios/{id}").buildAndExpand(usuarioRegistrado.id()).toUri();
        return ResponseEntity.created(url).body(usuarioRegistrado);
    }

    @PostMapping("login")
    public ResponseEntity<JWTtoken> login(@RequestBody @Valid DatosLoginUsuario datos) {
        JWTtoken token = registroService.loginUsuario(datos);
        return ResponseEntity.ok(token);
    }
}
