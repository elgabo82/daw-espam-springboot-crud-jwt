package com.grupofmo.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

/*
 * DAW-ESPAM - 2026
 * Security: JwtService
 * Gabriel Morejón
 */


@Service
public class JwtService {
	
	@Value("${security.jwt.secret-key}")
	private String secretKey;
	
	@Value("${security.jwt.expiration-ms}")
	private String expiration;
	
	private SecretKey getSigningKey() {
		return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
	}
	
	public String generarToken(UserDetails usuarioDetalles) {
		Map<String, Object> claims = new HashMap<>();
		String authority = usuarioDetalles.getAuthorities()
				.stream()
				.findFirst().map(GrantedAuthority::getAuthority)
				.orElse("ROL_USUARIO");
		
		claims.put("rol", authority);
		
		return Jwts.builder()
				.claims(claims)
				.subject(usuarioDetalles.getUsername())
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(getSigningKey())
				.compact();
	}
	
	public String extraerUsuario(String token) {
		return extractAllClaims(token).getSubject();
	}
	
	public boolean tokenValido(String token, UserDetails usuarioDetalles) {
		String usuario = extraerUsuario(token);
		return usuario.equals(usuarioDetalles.getUsername()) && !tokenExpirado(token);
	}
	
	public boolean tokenExpirado(String token) {		
		return extractAllClaims(token).getExpiration().before(new Date());
	}
	
	private Claims extractAllClaims(String token) {
		return Jwts.parser()
				.verifyWith(getSigningKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}
	
	

}
