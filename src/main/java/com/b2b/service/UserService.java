package com.b2b.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.b2b.bcryptPasswordEncoder.BcryptEncoderConfig;
import com.b2b.model.User;
import com.b2b.repository.UserRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class UserService {
	
    @Autowired
	private UserRepository userRepository;
	 
	@Autowired
	private BcryptEncoderConfig passwordEncoder;
	
	@Autowired
	JavaMailSender sender;
	
	
	//------ mail api --------//
	
	public void signUpMail(String firstName, String to) throws MessagingException, IOException {
	    SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
	    simpleMailMessage.setTo(to);
	    
	    String subject = "Welcome to Bulkify B2B!";
	    
	    simpleMailMessage.setSubject(subject);
	    
	    // HTML body with styling
	    String htmlBody = """
	            <div style="font-family: Arial, sans-serif;">
	                <div style="background-color: #3b82f6; padding: 20px; text-align: center; border-radius: 8px;">
	                    <h1 style="color: white; margin: 0;">Bulkify B2B</h1>
	                </div>
	                
	                <div style="padding: 20px;">
	                    <p>Hi %s,</p>
	                    
	                    <p>Welcome to Bulkify B2B! Thank you for joining our wholesale e-commerce platform.</p>
	                    
	                    <p>With Bulkify B2B, you get access to:</p>
	                    <ul>
	                        <li>Wholesale pricing on bulk orders</li>
	                        <li>Direct connections with manufacturers and suppliers</li>
	                        <li>Streamlined procurement process</li>
	                        <li>Dedicated business support</li>
	                    </ul>
	                    
	                    <p>Start exploring our platform to discover competitive wholesale prices and grow your business with us!</p>
	                    
	                    <p>Best regards,<br>
	                    Team Bulkify</p>
	                    
	                    <p style="font-size: 12px; color: #666;">
	                        *** This is an automated message. Please do not reply to this email. ***
	                    </p>
	                </div>
	            </div>
	            """.formatted(firstName);
	    
	    // Plain text alternative
	    String plainText = "Hi " + firstName + ",\n\n" +
	            "Welcome to Bulkify B2B! Thank you for joining our wholesale e-commerce platform.\n\n" +
	            "With Bulkify B2B, you get access to:\n" +
	            "• Wholesale pricing on bulk orders\n" +
	            "• Direct connections with manufacturers and suppliers\n" +
	            "• Streamlined procurement process\n" +
	            "• Dedicated business support\n\n" +
	            "Start exploring our platform to discover competitive wholesale prices and grow your business with us!\n\n" +
	            "Best regards,\n" +
	            "Team Bulkify\n\n\n" +
	            "*** This is an automated message. Please do not reply to this email. ***";
	    
	    MimeMessage mime = sender.createMimeMessage();
	    MimeMessageHelper mimeHelper = new MimeMessageHelper(mime, true, "UTF-8");
	    mimeHelper.setTo(to);
	    mimeHelper.setSubject(subject);
	    mimeHelper.setText(plainText, htmlBody);  // Second parameter true enables HTML
	    
	    sender.send(mime);
	 }
	
	
	 
	  public ResponseEntity<?> saveUser(User user) {
		 
		 
		 if(user!=null) {
			 
			 String encodedPassword = passwordEncoder.encode(user.getPassword());
		     user.setPassword(encodedPassword);
		     
		     return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK); 
		 }
		 return new ResponseEntity<>("user shoul not null", HttpStatus.BAD_REQUEST);
		
	   }
	  
	  
	  public ResponseEntity<?> getUserByEmailAndPassword(String email, String password) {
	       
	      Optional<User> userOptional = userRepository.findByEmail(email);
	        
	      if (userOptional.isPresent()) {
	    	  
	    	  User user = userOptional.get();
	          
	    	  if (passwordEncoder.matches(password, user.getPassword())) {
	            return new ResponseEntity<>(user, HttpStatus.OK); 
	          }
	      }
	      return new ResponseEntity<>("invalid credentials", HttpStatus.BAD_REQUEST); 
	   }
	 
	 
	   public User saveOrUpdateUser(User user) {
	       return userRepository.save(user);
	   }
	
	   
	   public Optional<User> getUserById(Long id) {
	      return userRepository.findById(id);
	   }
	
	   public void deleteUserById(Long id) {
	      userRepository.deleteById(id);
	   }
	   
	   
	 
	 

}
