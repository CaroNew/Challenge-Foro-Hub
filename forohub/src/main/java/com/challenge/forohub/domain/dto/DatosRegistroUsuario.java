package com.challenge.forohub.domain.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DatosRegistroUsuario(
        @NotBlank
        String nombre,
        @NotBlank
        @Email
        @JsonAlias("email") String CorreoElectronico,
        @NotBlank
        @Size(min = 8, max = 255)
        String clave
) {

}
