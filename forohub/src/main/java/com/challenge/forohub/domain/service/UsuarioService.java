package com.challenge.forohub.domain.service;

import com.challenge.forohub.domain.dto.DatosCrearUsuario;
import com.challenge.forohub.domain.dto.DatosRespuestaUsuario;
import com.challenge.forohub.domain.model.roles.Rol;
import com.challenge.forohub.domain.model.usuario.Usuario;
import com.challenge.forohub.domain.repository.RolRepository;
import com.challenge.forohub.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, RolRepository rolRepository) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
    }


    public DatosRespuestaUsuario crearUsuario(DatosCrearUsuario datos) {
        var passEncod = passwordEncoder.encode(datos.clave());

        Rol rol = rolRepository.findByNombre(datos.rol());
        Usuario usuario = usuarioRepository.save(new Usuario(null, datos.nombre(), datos.CorreoElectronico(),
                passEncod, List.of(rol), true));

        return new DatosRespuestaUsuario(usuario.getId(), usuario.getNombre(),
                usuario.getCorreoElectronico(), usuario.getRoles());
    }

//    @Transactional
//    public Usuario update(DatosModificarUsuario datos) {
//        Usuario usuario = usuarioRepository.findById(datos.id())
//                .orElseThrow(() -> new UsuarioNotFoundException("Usuario no encontrado"));
//
//        usuario.setNombre(datos.nombre());
//        usuario.setCorreoElectronico(datos.CorreoElectronico());
//        usuario.setClave(datos.clave());
//
//        return usuarioRepository.save(usuario);
//    }
//
//    @Transactional
//    public void delete(Long id) {
//        usuarioRepository.deleteById(id);
//    }
//
//    public Page<Usuario> findAll(Pageable pageable) {
//        return usuarioRepository.findAll(pageable);
//    }
//
//    public Usuario findById(Long id) {
//        return usuarioRepository.findById(id)
//                .orElseThrow(() -> new UsuarioNotFoundException("Usuario no encontrado"));
//    }
}
