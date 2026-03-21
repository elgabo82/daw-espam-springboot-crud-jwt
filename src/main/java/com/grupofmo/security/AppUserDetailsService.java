package com.grupofmo.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.grupofmo.repositories.UsuarioRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {
	
	private final UsuarioRepository repoUsuario;
	
	public AppUserDetailsService(UsuarioRepository repo) {
		this.repoUsuario = repo;
	}	

	@Override
	public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
		return repoUsuario.findByNombreUsuario(usuario)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + usuario));
	}

}
