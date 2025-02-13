package com.b2b.utils;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtUtil {

	
	public String extractUsername(String token) {
		
		Claims claims = extractAllClaims(token);
		return claims.getSubject();
	}
	
	public Date extractExpiration(String token) {
		return extractAllClaims(token).getExpiration();
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser()
				.verifyWith(getSigingKey())
				.build()
				.parseSignedClaims(token)
				.getPayLoad();
	}
	
	
}
