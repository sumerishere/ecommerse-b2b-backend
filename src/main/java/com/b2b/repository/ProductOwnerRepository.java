package com.b2b.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.b2b.model.ProductOwnerModel;

@Repository
public interface ProductOwnerRepository extends JpaRepository<ProductOwnerModel, Long>{
	
	
}
