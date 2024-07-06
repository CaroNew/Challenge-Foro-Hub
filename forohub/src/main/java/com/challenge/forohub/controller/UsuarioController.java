package com.challenge.forohub.controller;

import com.challenge.forohub.domain.dto.DatosModificarUsuario;
import com.challenge.forohub.domain.dto.DatosCrearUsuario;
import com.challenge.forohub.domain.dto.DatosRespuestaUsuario;
import com.challenge.forohub.domain.model.roles.Rol;
import com.challenge.forohub.domain.model.usuario.Usuario;
import com.challenge.forohub.domain.repository.RolRepository;
import com.challenge.forohub.domain.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/usuarios")
public class UsuarioController {

    private final
    UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioController(UsuarioRepository usuarioRepository, RolRepository rolRepository) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
    }


    @PostMapping
    @RequestMapping
    public ResponseEntity<DatosRespuestaUsuario> create(@RequestBody @Valid DatosCrearUsuario datos,
                                                                UriComponentsBuilder uriBuilder) {

        var passEncod = passwordEncoder.encode(datos.clave());
        Rol rol = rolRepository.findByNombre(datos.rol());

        datos = new DatosCrearUsuario(datos.nombre(), datos.CorreoElectronico(), passEncod, datos.rol());

        Usuario usuario = usuarioRepository.save(new Usuario(null, datos.nombre(), datos.CorreoElectronico(),
                datos.clave(), List.of(rol), true));

        DatosRespuestaUsuario respuesta = new DatosRespuestaUsuario(usuario.getId(), usuario.getNombre(),
                usuario.getCorreoElectronico(), usuario.getRoles());

        URI url = uriBuilder.path("/api/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(url).body(respuesta);
    }

    @GetMapping
    public ResponseEntity<Page<DatosRespuestaUsuario>> userList(@PageableDefault(size = 5,
            sort = {"nombre"}) Pageable page) {
        //mostrar solo los usuarios activos
        Page<Usuario> usuarios = usuarioRepository.findByActivoTrue(page);

        return ResponseEntity.ok(usuarios.map(usuario -> new DatosRespuestaUsuario(usuario.getId(),
                usuario.getNombre(), usuario.getCorreoElectronico(), usuario.getRoles())));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaUsuario> userById(@PathVariable Long id) {
        //validar que el usuario exista y este activo
        return usuarioRepository.findById(id)
                .map(usuario -> ResponseEntity.ok(new DatosRespuestaUsuario(usuario.getId(),
                        usuario.getNombre(), usuario.getCorreoElectronico(), usuario.getRoles())))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaUsuario> updateUser(@PathVariable Long id,
                                                            @RequestBody @Valid DatosModificarUsuario datos) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Usuario no encontrado"));
        //validar que el usuario este activo y existe
        String pass = null;
        Rol rol = null;
        if(datos.clave() != null){
            pass = passwordEncoder.encode(datos.clave());
        }
        //verificacion: que el rol no sea el mismo que ya existe
        if(datos.rol() != null){
            rol = rolRepository.findByNombre(datos.rol());
        }
        usuario.ModificarUsuario(datos, pass, rol);
        return ResponseEntity.ok(new DatosRespuestaUsuario(usuario.getId(), usuario.getNombre(),
                usuario.getCorreoElectronico(), usuario.getRoles()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        //validar que el usuario exista y este activo
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Usuario no encontrado"));
        usuario.desactivarUsuario();
        return ResponseEntity.noContent().build();
    }
}


