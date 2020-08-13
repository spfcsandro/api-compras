package com.compras.config;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.compras.constant.SecurityConstant;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtManager {

	public String createToken(String email, List<String> roles) {
		
		Date dateExp = Date.from(LocalDateTime.now()
				 .atZone(ZoneId.systemDefault()).plusDays(SecurityConstant.JWT_EXP_DAYS).toInstant());
		
		String token = Jwts.builder()
						 .setSubject(email)
						 .setExpiration(dateExp)
						 .claim(SecurityConstant.JWT_ROLE_KEY, roles)
						 .signWith(SignatureAlgorithm.HS512, SecurityConstant.API_KEY)
						 .compact();
		return token;
	}
	
	
}
