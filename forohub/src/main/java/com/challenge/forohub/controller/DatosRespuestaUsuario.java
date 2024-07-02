package com.challenge.forohub.controller;

import com.challenge.forohub.model.roles.Rol;
import java.util.List;

public record DatosRespuestaUsuario(
        Long id,
        String nombre,
        String correoElectronico,
        List<Rol> roles
) {
}
