package com.grupofmo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegistroRequest(
        @NotBlank(message = "El nombre es obligatorio.")
        String nombresCompletos,

        @JsonProperty("usuario")
        @NotBlank(message = "El nombre de usuario es obligatorio.")
        @Size(min = 4, max = 60, message = "El usuario debe tener mínimo 4 y máximo 60 caracteres")
        String nombreUsuario,

        @NotBlank(message = "La contraseña es obligatoria.")
        @Size(min = 6, message = "La clave debe tener al menos 6 caracteres.")
        String clave
		) {
}
