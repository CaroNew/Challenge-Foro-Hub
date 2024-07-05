package com.challenge.forohub.domain.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosCrearRespuesta(
        @NotBlank
        String mensaje,
        @NotNull
        @JsonAlias("autor_id") Long autorId,
        @NotNull
        @JsonAlias("tema_id")Long temaId
) {
}
