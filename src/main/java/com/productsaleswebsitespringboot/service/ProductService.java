package com.productsaleswebsitespringboot.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.productsaleswebsitespringboot.model.Product;

public interface ProductService {
    
    public List<Product> getAllProducts(Sort sort);
}
