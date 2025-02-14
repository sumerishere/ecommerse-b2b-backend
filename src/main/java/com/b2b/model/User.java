package com.b2b.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String firstName;
	private String lastName;
	private String mobile;
	private String address;
	private String username;
	private String email;
	private String password;
	
	private List<String> roles;
	
	public User() {}
	
	

	public User(Long id, String firstName, String lastName, String mobile, String address, String username, String email,
			String password, List<String> roles) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobile = mobile;
		this.address = address;
		this.username = username;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	

	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	public List<String> getRoles() {
		return roles;
	}



	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	
	
}
