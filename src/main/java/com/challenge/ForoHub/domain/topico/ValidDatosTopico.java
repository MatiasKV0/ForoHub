package com.challenge.ForoHub.domain.topico;

import jakarta.validation.constraints.NotBlank;

public record ValidDatosTopico(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje
) {
}
