package com.grupofmo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.grupofmo.models.Rol;
import com.grupofmo.models.Usuario;
import com.grupofmo.repositories.UsuarioRepository;

@Configuration
public class InitialDataConfig {

    @Bean
    CommandLineRunner initAdmin(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (!usuarioRepository.existsByNombreUsuario("admin")) {
                Usuario admin = new Usuario();
                admin.setNombresCompletos("Administrador");
                admin.setNombreUsuario(null);
                admin.setClave(passwordEncoder.encode("Clave.2026"));
                admin.setRol(Rol.ADMIN);
                usuarioRepository.save(admin);
            }
        };
    }
}