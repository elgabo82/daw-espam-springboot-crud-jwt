package com.grupofmo.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.grupofmo.dto.LoginRequest;
import com.grupofmo.dto.LoginResponse;
import com.grupofmo.dto.RegistroRequest;
import com.grupofmo.models.Rol;
import com.grupofmo.models.Usuario;
import com.grupofmo.repositories.UsuarioRepository;
import com.grupofmo.security.JwtService;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(UsuarioRepository usuarioRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService,
                       AuthenticationManager authenticationManager) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public String registrar(RegistroRequest request) {
        if (usuarioRepository.existsByNombreUsuario(request.nombreUsuario())) {
            throw new IllegalArgumentException("El usuario ya está registrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNombresCompletos(request.nombresCompletos());
        usuario.setNombreUsuario(request.nombreUsuario());
        usuario.setClave(passwordEncoder.encode(request.clave()));
        usuario.setRol(Rol.USUARIO);

        usuarioRepository.save(usuario);
        return "Usuario registrado correctamente";
    }

    public LoginResponse autenticar(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.nombreUsuario(),
                        request.clave()
                )
        );

        Usuario usuario = (Usuario) authentication.getPrincipal();
        String token = jwtService.generarToken(usuario);

        return new LoginResponse(token, "Bearer");
    }
}
/*public class AuthService {
	private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    
    public AuthService(UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            AuthenticationManager authenticationManager) {
		this.usuarioRepository = usuarioRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
		this.authenticationManager = authenticationManager;
	}
    
    public String registrar(RegistroRequest request) {
        if (usuarioRepository.existsByNombreUsuario(request.nombreUsuario())) {
            throw new IllegalArgumentException("El username ya está registrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNombresCompletos(request.nombresCompletos());
        usuario.setNombreUsuario(request.nombreUsuario());
        usuario.setClave(passwordEncoder.encode(request.clave()));
        usuario.setRol(Rol.USUARIO);

        usuarioRepository.save(usuario);
        return "Usuario registrado correctamente";
    }

    public LoginResponse autenticar(LoginRequest request) {
        //authenticationManager.authenticate(
        //        new UsernamePasswordAuthenticationToken(
        //                request.nombreUsuario(),
        //                request.clave()
        //        )
        );
    	Authentication auth = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.nombreUsuario(),
						request.clave()
				)
		);

        //Usuario usuario = usuarioRepository.findByNombreUsuario(request.nombreUsuario())
        //        .orElseThrow(() -> new IllegalArgumentException("Credenciales inválidas"));
    	
    	Usuario usuario = (Usuario) auth.getPrincipal();
    	
    	
        String token = jwtService.generarToken(usuario);
        return new LoginResponse(token, "Bearer");
    }
}*/
