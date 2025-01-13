package com.b2b.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.b2b.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	
	@Autowired
	ProductService productService;

	
	
}
