package com.compras.service.security;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.compras.constant.SecurityConstant;
import com.compras.dto.UsuarioLoginResponseDTO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtManager {

	public UsuarioLoginResponseDTO createToken(String email, List<String> roles) {
		
		Instant now = Instant.now();
		Date dateExp = Date.from(now.plus(SecurityConstant.JWT_EXP_DAYS, ChronoUnit.DAYS));
		
		String token = Jwts.builder()
						 .setSubject(email)
						 .setExpiration(dateExp)
						 .claim(SecurityConstant.JWT_ROLE_KEY, roles)
						 .signWith(SignatureAlgorithm.HS512, SecurityConstant.API_KEY.getBytes())
						 .compact();
		
		
		return new UsuarioLoginResponseDTO(token, dateExp.getTime(), SecurityConstant.JWT_PROVIDER);
	}
	
	public Claims parseToken(String jwt) throws JwtException {
		Claims claims = Jwts.parser()
							.setSigningKey(SecurityConstant.API_KEY.getBytes())
							.parseClaimsJws(jwt)
							.getBody();
		
		
		return claims;
	}
	
	
}
