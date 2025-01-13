package com.b2b.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.model.ProductOwnerModel;
import com.b2b.repository.ProductOwnerRepository;

@Service
public class ProductOwnerService {
	
	@Autowired
	ProductOwnerRepository productOwnerRepository;
	
	

    public ProductOwnerModel createOwner(ProductOwnerModel owner) {
        return productOwnerRepository.save(owner);
    }

    public List<ProductOwnerModel> getAllOwners() {
        return productOwnerRepository.findAll();
    }

    
    public ProductOwnerModel getOwnerById(Long id) {
        return productOwnerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Owner not found with id: " + id));
    }

  
    public ProductOwnerModel updateOwner(Long id, ProductOwnerModel ownerDetails) {
        ProductOwnerModel owner = productOwnerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Owner not found with id: " + id));

        owner.setFirstName(ownerDetails.getFirstName());
        owner.setLastName(ownerDetails.getLastName());
        owner.setAddress(ownerDetails.getAddress());
        owner.setMobileNumber(ownerDetails.getMobileNumber());
        owner.setEmail(ownerDetails.getEmail());
        owner.setGovernmentId(ownerDetails.getGovernmentId());
        owner.setPassword(ownerDetails.getPassword());

        return productOwnerRepository.save(owner);
    }

  
    public void deleteOwner(Long id) {
        ProductOwnerModel owner = productOwnerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Owner not found with id: " + id));
        productOwnerRepository.delete(owner);
    }
	
}
