package com.productsaleswebsitespringboot.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.productsaleswebsitespringboot.model.Product;

public interface ProductService {
    
    public void deleteProduct(Long id);

    public Product getProductById(Long id);

    public Product saveProduct(Product product);

    public void updateProduct(Product product);

    public List<Product> getAllProducts(Sort sort);
}
