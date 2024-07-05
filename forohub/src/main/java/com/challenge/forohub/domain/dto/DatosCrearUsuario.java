package com.challenge.forohub.domain.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DatosCrearUsuario(
        @NotBlank
        String nombre,
        @NotBlank
        @Email
        @JsonAlias("email")
        String CorreoElectronico,
        @NotBlank
        String clave,
        @Pattern(regexp = "^(ADMIN|USER)$", message = "El rol debe ser ADMIN o USER")
        @JsonAlias("rol")
        String rol

) {
}
