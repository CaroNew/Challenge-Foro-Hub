package com.challenge.forohub.controller;

import com.challenge.forohub.model.usuario.Usuario;
import com.challenge.forohub.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/login")
public class AutenticacionController {


    @PostMapping
    public ResponseEntity autenticacionUsuario(@RequestBody Usuario usuario) {//pedir y devolver un dto
        return ResponseEntity.ok().build();
    }
}
