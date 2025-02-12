package com.b2b.controller;

import java.awt.PageAttributes.MediaType;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.b2b.dtos.ProductDTO;
import com.b2b.model.ProductModel;
import com.b2b.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	
	@Autowired
	ProductService productService;
	
	@PostMapping("/add-product")
	public ResponseEntity<ProductModel> createProduct(
        @RequestPart("product") ProductModel product,
        @RequestPart(value = "image") MultipartFile file) {
        return productService.createProduct(product, file);
    }

	
//	@PostMapping("/add-product")
//    public ResponseEntity<ProductModel> createProduct(
//            @RequestPart("product") ProductDTO productDTO,
//            @RequestPart(value = "image", required = false) MultipartFile image) {
//        return productService.createProduct(productDTO, image);
//    }
	    
    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductModel> updateProduct(
            @PathVariable Long id,
            @RequestPart("product") ProductDTO productDTO,
            @RequestPart(value = "image", required = false) MultipartFile image) {
        return productService.updateProduct(id, productDTO, image);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }
	
	
}
