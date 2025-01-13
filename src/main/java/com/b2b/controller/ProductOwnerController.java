package com.b2b.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.b2b.model.ProductOwnerModel;
import com.b2b.service.ProductOwnerService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/owner")
public class ProductOwnerController {

	@Autowired
	ProductOwnerService productOwnerSerivce;
	

    @PostMapping("/add-owner")
    public ResponseEntity<ProductOwnerModel> createOwner(@RequestBody ProductOwnerModel owner) {
        ProductOwnerModel savedOwner = productOwnerSerivce.createOwner(owner);
        return ResponseEntity.ok(savedOwner);
    }

    
    @GetMapping
    public ResponseEntity<List<ProductOwnerModel>> getAllOwners() {
        List<ProductOwnerModel> owners = productOwnerSerivce.getAllOwners();
        return ResponseEntity.ok(owners);
    }

   
    @GetMapping("/{id}")
    public ResponseEntity<ProductOwnerModel> getOwnerById(@PathVariable Long id) {
        ProductOwnerModel owner = productOwnerSerivce.getOwnerById(id);
        return ResponseEntity.ok(owner);
    }

   
    @PutMapping("/{id}")
    public ResponseEntity<ProductOwnerModel> updateOwner(@PathVariable Long id, @RequestBody ProductOwnerModel ownerDetails) {
        ProductOwnerModel updatedOwner = productOwnerSerivce.updateOwner(id, ownerDetails);
        return ResponseEntity.ok(updatedOwner);
    }

   
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable Long id) {
    	productOwnerSerivce.deleteOwner(id);
        return ResponseEntity.noContent().build();
    }
	
	
}
