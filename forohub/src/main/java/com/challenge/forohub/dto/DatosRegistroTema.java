package com.challenge.forohub.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;

public record DatosRegistroTema(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @JsonAlias("usuario_id") Long autorId,
        @JsonAlias("nombre_curso") String nombreCurso
) {
}
