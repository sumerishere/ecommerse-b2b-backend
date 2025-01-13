package com.b2b.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.b2b.model.ProductModel;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long>{

	@Query(value = "SELECT p.product_name, p.product_price, CONCAT(o.first_name, ' ', o.last_name) as owner_name, o.mobile_number, o.email FROM product_model p INNER JOIN product_owner_model o ON p.owner_id = o.id", nativeQuery = true)
	List<Object[]> getProductOwnerDetails();
}
