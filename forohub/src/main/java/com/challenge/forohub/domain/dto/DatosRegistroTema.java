package com.challenge.forohub.domain.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTema(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @JsonAlias("usuario_id")
        @NotNull Long autorId,
        @JsonAlias("nombre_curso")
        @NotNull String nombreCurso
) {
}
