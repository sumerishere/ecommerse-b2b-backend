package com.b2b.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.b2b.dtos.AuthRequest;
import com.b2b.dtos.AuthResponse;
import com.b2b.utils.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	   private final AuthenticationManager authenticationManager;
	   private final UserDetailsService userDetailsService;
	   private final JwtUtil jwtUtil;
	   
	   public AuthController(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtUtil jwtUtil){
		   
			this.authenticationManager = authenticationManager;
			this.userDetailsService = userDetailsService;
			this.jwtUtil = jwtUtil;
	   }
	   
	   @PostMapping("/login")
	    public ResponseEntity<?> authenticate(@RequestBody AuthRequest authRequest) {
	        authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
	        );

	        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
	        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

	        // REPLACE: Return JWT token in your preferred format
	        return ResponseEntity.ok(new AuthResponse(jwt));
	    }



}
