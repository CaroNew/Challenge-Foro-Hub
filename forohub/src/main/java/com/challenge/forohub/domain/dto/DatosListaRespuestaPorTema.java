package com.challenge.forohub.domain.dto;

import com.challenge.forohub.domain.model.respuesta.Respuesta;

import java.time.LocalDateTime;
import java.util.List;

public record DatosListaRespuestaPorTema(
        List<Respuesta> respuestas
) {
}
