package com.b2b.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.b2b.model.User;
import com.b2b.repository.UserRepository;
import com.b2b.service.UserService;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	 @Autowired
	 private UserRepository userRepository;
	
	 
	@PostMapping("/add-user")
    public ResponseEntity<?> createUser(@RequestBody User user) {
		
		Optional<User> userOptional = userRepository.findByEmail(user.getEmail());


        try {
        	
        	if(!userOptional.isPresent()) {
        		userService.saveUser(user);
    			userService.signUpMail(user.getFirstName(), user.getEmail());
                return ResponseEntity.ok("User information saved successfully.");

        	}

		}
        catch (MessagingException | IOException e) {
			e.printStackTrace();
		}
        return new ResponseEntity<>("user already present", HttpStatus.BAD_REQUEST);
    }

}
