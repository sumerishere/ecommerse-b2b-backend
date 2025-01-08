package com.b2b.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.b2b.model.User;
import com.b2b.repository.UserRepository;

@Service
@CrossOrigin("*")
public class UserService {
	
	 @Autowired
	 private UserRepository userRepository;
	 
	 
	 public User saveUser(User user) {
	        return userRepository.save(user);
	  }
	 
	 

}
