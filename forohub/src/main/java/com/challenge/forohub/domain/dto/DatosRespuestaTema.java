package com.challenge.forohub.domain.dto;

import com.challenge.forohub.domain.model.tema.Estado;
import com.challenge.forohub.domain.model.tema.Tema;

import java.time.LocalDateTime;

public record DatosRespuestaTema(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fecha,
        Estado status,
        String curso,
        String autor
) {
    public DatosRespuestaTema(Tema tema) {
        this(tema.getId(), tema.getTitulo(), tema.getMensaje(), tema.getFecha(), tema.getStatus(), tema.getCursoId().getNombre(), tema.getAutorId().getNombre());
    }
}
