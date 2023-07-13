package com.productsaleswebsitespringboot.service;

import java.util.List;
import java.util.Optional;

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
    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return product.get();
        } else {
            throw new IllegalArgumentException(id + " não encontrado");
        }
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void updateProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts(Sort sort) {
        List<Product> productList = productRepository.findAll(sort);
        return productList;
    }

}
