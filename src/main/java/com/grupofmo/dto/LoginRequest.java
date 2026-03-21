package com.grupofmo.dto;

/*
 * DAW-ESPAM - 2026
 * Registro (Record): LoginRequest
 * Gabriel Morejón
 */

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
		@NotBlank(message = "El usuario es obligatorio.")
		String nombreUsuario,
		
		@NotBlank(message = "La contraseña es obligatorio.")
		String clave
		) {

}
