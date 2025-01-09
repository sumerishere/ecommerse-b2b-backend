package com.b2b.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.b2b.model.User;
import com.b2b.repository.UserRepository;
import com.b2b.service.UserService;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {
	
	private UserService userService;
	private UserRepository userRepository;
	
	
	public UserController(UserService userService, UserRepository userRepository) {
		this.userService = userService;
		this.userRepository = userRepository;
		
	}
	
	 
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
	
	
	
	@GetMapping("/login")
    public ResponseEntity<?> getUserByEmailAndPassword(@RequestParam String email, @RequestParam String password) {
		
       return userService.getUserByEmailAndPassword(email, password);  
    }
	
	
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
    	
        Optional<User> user = userService.getUserById(id);
        
        return user.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }
    

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {

    	Optional<User> existingUser = userService.getUserById(id);
    	
        if (!existingUser.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        user.setId(id);
        User updatedUser = userService.saveOrUpdateUser(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
    	
        Optional<User> user = userService.getUserById(id);
        
        if (!user.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        userService.deleteUserById(id);
        return ResponseEntity.ok().body("user deleted successfully of Id:"+id);
    }

}
