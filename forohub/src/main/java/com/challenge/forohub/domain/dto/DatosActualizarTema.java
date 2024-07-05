package com.challenge.forohub.domain.dto;

import com.challenge.forohub.domain.model.tema.Estado;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DatosActualizarTema(
        @NotNull
        Long id,
        String titulo,
        String mensaje,
        //@Pattern(regexp = "^(ABIERTO|CERRADO)$", message = "El estado debe ser ABIERTO o CERRADO")
        Estado status
) {
}
