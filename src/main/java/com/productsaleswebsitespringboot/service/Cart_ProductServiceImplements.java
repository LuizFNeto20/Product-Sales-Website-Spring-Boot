package com.productsaleswebsitespringboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.productsaleswebsitespringboot.model.Cart;
import com.productsaleswebsitespringboot.model.Cart_Product;
import com.productsaleswebsitespringboot.repository.Cart_ProductRepository;
import com.productsaleswebsitespringboot.security.UserDetail;

@Service
public class Cart_ProductServiceImplements implements Cart_ProductService {

    @Autowired
    private Cart_ProductRepository cart_ProductRepository;

    @Autowired
    private CartService cartService;

    @Override
    public void deleteCart_Product(Long id) {
        Cart_Product cart_Product = getCart_ProductById(id);
        cart_ProductRepository.delete(cart_Product);    }

    @Override
    public Cart_Product getCart_ProductById(Long id) {
        Optional<Cart_Product> optionalCart_Product = cart_ProductRepository.findById(id);
        if (optionalCart_Product.isPresent()) {
            return optionalCart_Product.get();
        } else {
            throw new IllegalStateException("Cart_Product not found for the given ID.");
        }
    }

    @Override
    public Cart_Product saveCart_Product(Cart_Product cartProduct) {
        return cart_ProductRepository.save(cartProduct);
    }

    @Override
    public List<Cart_Product> getAllCart_Product(Sort sort) {
        List<Cart_Product> cart_Products = cart_ProductRepository.findAll(sort);
        return cart_Products;
    }

    @Override
    public void deleteCartProduct(long id) {

        Cart cart;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            UserDetail userDetail = (UserDetail) auth.getPrincipal();

            cart = cartService.getCartByUserId(userDetail.getId());

            if (cart == null) {
                throw new IllegalStateException("Cart not found for the authenticated user.");
            }

            List<Cart_Product> cart_Products = getAllCart_Product(Sort.by(Sort.Direction.ASC, "id"));

            Cart_Product cart_Product = cart_Products.stream()
                    .filter(cp -> cp.getProduct().getId() == id && cp.getCart().getId() == cart.getId())
                    .findFirst().orElse(null);

            if (cart_Product != null) {
                cart_ProductRepository.delete(cart_Product);
            } else {
                throw new IllegalStateException("Cart_Product not found for the given product ID and cart.");
            }
        }

    }

}
