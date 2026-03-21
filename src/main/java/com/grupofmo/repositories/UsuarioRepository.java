package com.grupofmo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupofmo.models.Usuario;

/*
 * DAW-ESPAM - 2026
 * Repositorio: Usuario
 * Gabriel Morejón
 */

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Optional<Usuario> findByNombreUsuario(String nombreUsuario);
	boolean existsByNombreUsuario(String nombreusuario);
}
