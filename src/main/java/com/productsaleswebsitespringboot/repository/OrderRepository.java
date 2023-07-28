package com.productsaleswebsitespringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.productsaleswebsitespringboot.model.Orders;

public interface OrderRepository extends JpaRepository<Orders, Long>{

}
