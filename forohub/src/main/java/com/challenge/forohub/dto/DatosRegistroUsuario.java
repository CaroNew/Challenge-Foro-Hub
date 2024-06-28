package com.challenge.forohub.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DatosRegistroUsuario(
        @NotBlank
        String nombre,
        @NotBlank
        @Email
        @JsonAlias("email") String CorreoElectronico,
        @NotBlank
        String clave
) {

}
