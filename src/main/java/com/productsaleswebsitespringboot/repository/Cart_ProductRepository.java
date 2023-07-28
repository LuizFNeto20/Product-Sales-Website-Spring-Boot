package com.productsaleswebsitespringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.productsaleswebsitespringboot.model.Cart_Product;

public interface Cart_ProductRepository extends JpaRepository<Cart_Product, Long>{
    
}
