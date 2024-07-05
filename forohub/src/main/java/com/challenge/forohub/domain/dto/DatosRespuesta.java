package com.challenge.forohub.domain.dto;

import com.challenge.forohub.domain.model.tema.Estado;

import java.time.LocalDateTime;

public record DatosRespuesta(
        Long id,
        String mensaje,
        LocalDateTime fecha,
        String temaTitulo,
        String autor
) {
}
