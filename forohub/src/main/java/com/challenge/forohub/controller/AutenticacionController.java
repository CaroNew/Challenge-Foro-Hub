package com.challenge.forohub.controller;

import com.challenge.forohub.dto.DatosRegistroUsuario;
import com.challenge.forohub.dto.DatosRespuestaUsuario;
import com.challenge.forohub.model.roles.Rol;
import com.challenge.forohub.model.usuario.Usuario;
import com.challenge.forohub.repository.RolRepository;
import com.challenge.forohub.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/auth")
public class AutenticacionController {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    @Autowired
    public AutenticacionController(UsuarioRepository usuarioRepository, RolRepository rolRepository, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping("registro")
    public ResponseEntity <DatosRespuestaUsuario> registroUsuario(@RequestBody DatosRegistroUsuario datosUsuario,
                                                                  UriComponentsBuilder uriBuilder) {//pedir y devolver un dto
        //encriptar la contraseña
        var passwordEncriptada = passwordEncoder.encode(datosUsuario.clave());
        //Asignar un rol (USER por defecto)
        Rol rol = rolRepository.findByNombre("USER");
        Usuario usuario = new Usuario(null, datosUsuario.nombre(), datosUsuario.CorreoElectronico(),
                passwordEncriptada, Collections.singletonList(rol));
        usuarioRepository.save(usuario);
        URI url = uriBuilder.path("/api/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(url).body(new DatosRespuestaUsuario(usuario.getId(), usuario.getNombre(),
                usuario.getCorreoElectronico()));
    }
}
