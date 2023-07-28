package com.productsaleswebsitespringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.productsaleswebsitespringboot.model.Cart;


public interface CartRepository extends JpaRepository<Cart, Long>{
    Cart findById(long id);
}

