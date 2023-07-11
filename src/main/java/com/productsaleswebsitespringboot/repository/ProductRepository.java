package com.productsaleswebsitespringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.productsaleswebsitespringboot.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>  {
    
}
