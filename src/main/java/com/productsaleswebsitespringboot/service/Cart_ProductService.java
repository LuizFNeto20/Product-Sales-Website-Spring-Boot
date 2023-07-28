package com.productsaleswebsitespringboot.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.productsaleswebsitespringboot.model.Cart_Product;

public interface Cart_ProductService {

    public void deleteCart_Product(Long id);

    public Cart_Product getCart_ProductById(Long id);

    public Cart_Product saveCart_Product(Cart_Product cartProduct);

    public List<Cart_Product> getAllCart_Product(Sort sort);

    public void deleteCartProduct(long id);

}
