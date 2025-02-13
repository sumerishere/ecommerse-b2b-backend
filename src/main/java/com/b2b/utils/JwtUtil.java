package com.b2b.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private String SECRET_KEY = "TaK+HaV^uvCHEFsEVfypW#7g9^k*Z8$V";
    
    SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    
    private static final long TOKEN_VALIDITY = 24 * 60 * 60 * 1000;  // Token validity in milliseconds (e.g., 24 hours)

    
    public SecretKey getSigningKey() {
    	return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }
    
	
	public String extractUsername(String token) {
		Claims claims = extractAllClaims(token);
		return claims.getSubject();
	}
	
	
	public Date extractExpiration(String token) {
		return extractAllClaims(token).getExpiration();
	}
	

	private Claims extractAllClaims(String token) {
		
		return Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	
	
	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	
	public String generateToken(String username) {
		
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, username);
	}
	
	
	public String createToken(Map<String, Object> claims, String subject) {
		return Jwts.builder()
				.addClaims(claims)
				.setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY ))
				.signWith(getSigningKey())
				.compact();
	}
	
	public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

	
//	public Boolean validateToken(String token) {
//		return !isTokenExpired(token);
//	}
	
	
}
