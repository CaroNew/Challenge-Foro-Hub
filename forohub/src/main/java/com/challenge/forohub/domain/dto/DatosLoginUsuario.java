package com.challenge.forohub.domain.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;

public record DatosLoginUsuario(
        @NotBlank
        @JsonAlias("email") String correoElectronico,
        @NotBlank
        String clave
) {
}
