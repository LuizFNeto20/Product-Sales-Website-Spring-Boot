package com.productsaleswebsitespringboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.productsaleswebsitespringboot.model.Cart;
import com.productsaleswebsitespringboot.repository.CartRepository;
import com.productsaleswebsitespringboot.security.UserDetail;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

@Service
public class CartServiceImplements implements CartService {

    @Autowired
    private final EntityManager entityManager;

    public CartServiceImplements(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Autowired
    private CartRepository cartRepository;

    @Override
    public void deleteCart(Long id) {
        Cart cart = getCartById(id);
        cartRepository.delete(cart);
    }

    @Override
    public Cart getCartById(Long id) {
        Optional<Cart> cart = cartRepository.findById(id);
        if (cart.isPresent()) {
            return cart.get();
        } else {
            return null;
        }
    }

    @Override
    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }
    
    @Override
    public List<Cart> getAllCart(Sort sort) {
        List<Cart> carts = cartRepository.findAll(sort);
        return carts;
    }

    @Override
    public Cart getCartByUserId(Long id) {
        try {
            TypedQuery<Cart> query = entityManager.createQuery(
                "SELECT c FROM Cart c WHERE c.user.id = :userId", Cart.class);
            query.setParameter("userId", id);

            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void cancelCart() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            UserDetail userDetail = (UserDetail) auth.getPrincipal();

            Cart cart = getCartByUserId(userDetail.getId());

            deleteCart(cart.getId());
        }

    }
}
