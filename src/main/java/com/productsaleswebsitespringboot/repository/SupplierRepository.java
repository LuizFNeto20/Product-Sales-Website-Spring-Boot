package com.productsaleswebsitespringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.productsaleswebsitespringboot.model.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long>{
    
}
