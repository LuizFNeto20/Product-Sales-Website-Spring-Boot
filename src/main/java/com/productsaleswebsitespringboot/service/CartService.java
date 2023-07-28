package com.productsaleswebsitespringboot.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.productsaleswebsitespringboot.model.Cart;

public interface CartService {
    public void deleteCart(Long id);

    public Cart getCartById(Long id);

    public Cart saveCart(Cart cart);

    public List<Cart> getAllCart(Sort sort);

    public Cart getCartByUserId(Long id);

    public void cancelCart();

}
