package com.challenge.forohub.domain.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record DatosModificarUsuario(
        String nombre,
        @Email
        @JsonAlias("email")
        String correoElectronico,
        String clave,
        @Pattern(regexp = "^(ADMIN|USER)$", message = "El rol debe ser ADMIN o USER")
        String rol
) {
}
