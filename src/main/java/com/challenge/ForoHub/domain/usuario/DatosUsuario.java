package com.challenge.ForoHub.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DatosUsuario(
        @NotBlank
        String mail,
        @NotBlank
        String password
) {
}
