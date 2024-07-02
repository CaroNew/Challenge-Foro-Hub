package com.challenge.forohub.repository;

import com.challenge.forohub.dto.DatosModificarUsuario;
import com.challenge.forohub.model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByNombre(String nombre);

    Optional<UserDetails> findByCorreoElectronico(String email);

}
