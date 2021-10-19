package com.shopit.productmicroservice.controller;

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

import com.shopit.productmicroservice.repository.ProductRepository;
import com.shopit.productmicroservice.entity.Product;
import com.shopit.productmicroservice.exception.ResourceNotFoundException;

@CrossOrigin(origins = "http:localhost:4200")
@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private	ProductRepository productRepository;
	
	//Get all products
	@GetMapping
	public List<Product> listAllProducts(){
		return this.productRepository.findAll();
	}
	
	//Get products by id
	@GetMapping("/{id}")
	public Product getProductById(@PathVariable (value = "id") long productId) {
		return this.productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product Not Found "+ productId));
	}
	
	//Create product
	@PostMapping
	public Product createProduct(@RequestBody Product product) {
		return this.productRepository.save(product);
	}
	
	//Update product
	@PutMapping("/{id}")
	public Product updateProduct(@RequestBody Product product, @PathVariable ("id") long productId) {
		Product existing =  this.productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product Not Found "+ productId)); 
		existing.setProductName(product.getProductName());
		existing.setProductDescription(product.getProductDescription());
		existing.setPrice(product.getPrice());
		existing.setExpiryDate(product.getExpiryDate());
		existing.setQuantity(product.getQuantity());
		
		return this.productRepository.save(existing);
	}
	
	//Delete product
	@DeleteMapping("/{id}")
	public ResponseEntity<Product> deleteProduct(@PathVariable ("id") long productId){
		Product toDeleteProduct =  this.productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product Not Found "+ productId)); 
		this.productRepository.delete(toDeleteProduct);
		return ResponseEntity.ok().build();
	}
	
}
