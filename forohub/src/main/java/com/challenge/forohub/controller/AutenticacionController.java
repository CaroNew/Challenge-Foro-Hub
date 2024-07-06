package com.challenge.forohub.controller;

import com.challenge.forohub.domain.dto.DatosLoginUsuario;
import com.challenge.forohub.domain.dto.DatosRegistroUsuario;
import com.challenge.forohub.domain.dto.DatosRespuestaRegistroUsuario;
import com.challenge.forohub.domain.dto.JWTtoken;
import com.challenge.forohub.infra.security.TokenService;
import com.challenge.forohub.domain.model.roles.Rol;
import com.challenge.forohub.domain.model.usuario.Usuario;
import com.challenge.forohub.domain.repository.RolRepository;
import com.challenge.forohub.domain.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/auth")
public class AutenticacionController {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    @Autowired
    TokenService tokenService;

    @Autowired
    public AutenticacionController(UsuarioRepository usuarioRepository, RolRepository rolRepository, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;

    }


    @PostMapping("registro")
    public ResponseEntity <DatosRespuestaRegistroUsuario> registroUsuario(@RequestBody DatosRegistroUsuario datosUsuario,
                                                                          UriComponentsBuilder uriBuilder) {//pedir y devolver un dto

        //Idea admin
        /*
        * if(nombre == admin && clave == admin )
        *
        * */


        var passwordEncriptada = passwordEncoder.encode(datosUsuario.clave());
        Rol rol = rolRepository.findByNombre("USER");
        Usuario usuario = usuarioRepository.save(new Usuario(datosUsuario, passwordEncriptada, rol));
        URI url = uriBuilder.path("/api/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(url).body(new DatosRespuestaRegistroUsuario(usuario.getId(), usuario.getNombre(),
                usuario.getCorreoElectronico()));
    }

    @PostMapping("login")
    public ResponseEntity<JWTtoken> login(@RequestBody @Valid DatosLoginUsuario datos) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(datos.correoElectronico(),
                datos.clave());
        var usuarioAutenticado = authenticationManager.authenticate(authenticationToken);
        var JWTtoken = tokenService.generateToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new JWTtoken(JWTtoken));
    }
}
