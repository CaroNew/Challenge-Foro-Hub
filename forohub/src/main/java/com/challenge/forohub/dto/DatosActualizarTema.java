package com.challenge.forohub.dto;

import com.challenge.forohub.model.tema.Estado;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarTema(
        @NotNull
        Long id,
        String titulo,
        String mensaje,
        Estado status
) {
}
