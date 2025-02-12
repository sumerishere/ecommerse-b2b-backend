package com.b2b.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.b2b.dtos.ProductDTO;
import com.b2b.model.ProductModel;
import com.b2b.model.ProductOwnerModel;
import com.b2b.repository.ProductOwnerRepository;
import com.b2b.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ProductOwnerRepository productOwnerRepository;
	
	
	public ResponseEntity<ProductModel> createProduct(ProductModel product, MultipartFile file) {
		
        try {
            if (file != null && !file.isEmpty()) {
                product.setProductImage(file.getBytes());
            }
            ProductModel savedProduct = productRepository.save(product);
            return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
        } 
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	
	
//    public ResponseEntity<ProductModel> createProduct(ProductDTO productDTO, MultipartFile image) {
//        try {
//            ProductModel product = new ProductModel();
//            product.setProductName(productDTO.getProductName());
//            product.setProductDescription(productDTO.getProductDescription());
//            product.setProductPrice(productDTO.getProductPrice());
//            
//            // Handle image
//            if (image != null && !image.isEmpty()) {
//                product.setProductImage(image.getBytes());
//            }
//            
//            // Set owner if ownerId is provided
//            if (productDTO.getOwnerId() != null) {
//                ProductOwnerModel owner = productOwnerRepository.findById(productDTO.getOwnerId())
//                    .orElseThrow(() -> new RuntimeException("Owner not found"));
//                product.setOwner(owner);
//            }
//            
//            ProductModel savedProduct = productRepository.save(product);
//            return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
    
    
    public ResponseEntity<ProductModel> updateProduct(Long id, ProductDTO productDTO, MultipartFile image) {
        try {
            Optional<ProductModel> existingProduct = productRepository.findById(id);
            
            if (existingProduct.isPresent()) {
            	
                ProductModel product = existingProduct.get();
                
                product.setProductName(productDTO.getProductName());
                product.setProductDescription(productDTO.getProductDescription());
                product.setProductPrice(productDTO.getProductPrice());
                
                // Update image only if new image is provided
                if (image != null && !image.isEmpty()) {
                    product.setProductImage(image.getBytes());
                }
               
                return new ResponseEntity<>(productRepository.save(product), HttpStatus.OK);
                
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    public ResponseEntity<Void> deleteProduct(Long id) {
    	
        try {
        	
            Optional<ProductModel> product = productRepository.findById(id);
            
            if (product.isPresent()) {
                productRepository.deleteById(id);
                
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } 
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } 
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
