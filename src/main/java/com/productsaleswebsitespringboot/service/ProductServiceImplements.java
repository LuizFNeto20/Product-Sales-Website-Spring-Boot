package com.productsaleswebsitespringboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.productsaleswebsitespringboot.model.Product;
import com.productsaleswebsitespringboot.repository.ProductRepository;

@Service
public class ProductServiceImplements implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts(Sort sort) {
        List<Product> productList = productRepository.findAll(sort);
        return productList;
    }
    
}
