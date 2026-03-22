package com.grupofmo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record LibroRequest(
        @NotBlank(message = "El título es obligatorio")
        String titulo,

        @NotBlank(message = "El autor es obligatorio")
        String autor,

        @NotBlank(message = "El ISBN es obligatorio")
        String isbn,

        @PositiveOrZero(message = "El año de publicación no puede ser negativo")
        Integer anioPublicacion,

        @NotBlank(message = "La editorial es obligatoria")
        String editorial,

        Boolean disponible
) {
}
