package com.challenge.ForoHub.domain.topico;

import jakarta.validation.constraints.NotNull;

public record ValidPutDatosTopico(
        @NotNull
        Long id,
        String titulo,
        String mensaje
) {

}
