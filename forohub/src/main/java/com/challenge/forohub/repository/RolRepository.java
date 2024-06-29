package com.challenge.forohub.repository;

import com.challenge.forohub.controller.TemaController;
import com.challenge.forohub.model.roles.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, Long> {
    Rol findByNombre(String usuario);
}
