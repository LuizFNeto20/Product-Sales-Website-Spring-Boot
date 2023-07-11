package com.productsaleswebsitespringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.productsaleswebsitespringboot.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
    
}
