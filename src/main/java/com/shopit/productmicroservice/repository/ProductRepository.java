package com.shopit.productmicroservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopit.productmicroservice.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository <Product, Long>{
	
}
