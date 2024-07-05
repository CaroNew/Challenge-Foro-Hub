package com.challenge.forohub.domain.repository;

import com.challenge.forohub.domain.model.usuario.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByNombre(String nombre);

    Optional<UserDetails> findByCorreoElectronico(String email);

    Page<Usuario> findByActivoTrue(Pageable page);

}
