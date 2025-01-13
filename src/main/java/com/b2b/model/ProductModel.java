package com.b2b.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ProductModel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String productName;
	
	@Column(columnDefinition = "LongText")
	private String productDescription;
	private String productPrice;
	
	
	@Lob
	private byte[] productImage;
	 
	   
	@ManyToOne
    @JoinColumn(name = "owner_id")
    private ProductOwnerModel owner;
	
	
	public ProductModel() {}

	public ProductModel(Long id, String productName, String productDescription, String productPrice) {
		this.id = id;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productPrice = productPrice;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}



	public byte[] getProductImage() {
		return productImage;
	}



	public void setProductImage(byte[] productImage) {
		this.productImage = productImage;
	}

	public ProductOwnerModel getOwner() {
		return owner;
	}

	public void setOwner(ProductOwnerModel owner) {
		this.owner = owner;
	}

	
	
	

}
