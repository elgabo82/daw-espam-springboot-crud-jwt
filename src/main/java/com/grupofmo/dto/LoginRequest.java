package com.grupofmo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * DAW-ESPAM - 2026
 * Registro (Record): LoginRequest
 * Gabriel Morejón
 */

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
		@JsonProperty("usuario")
		@NotBlank(message = "El usuario es obligatorio.")
		String nombreUsuario,
		
		@JsonProperty("clave")
		@NotBlank(message = "La contraseña es obligatoria.")
		String clave
		) {

}
