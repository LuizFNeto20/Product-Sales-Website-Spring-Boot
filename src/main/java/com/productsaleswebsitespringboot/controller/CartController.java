package com.productsaleswebsitespringboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.productsaleswebsitespringboot.model.Cart;
import com.productsaleswebsitespringboot.model.Cart_Product;
import com.productsaleswebsitespringboot.model.Product;
import com.productsaleswebsitespringboot.security.UserDetail;
import com.productsaleswebsitespringboot.service.CartService;
import com.productsaleswebsitespringboot.service.Cart_ProductService;
import com.productsaleswebsitespringboot.service.ProductService;
import com.productsaleswebsitespringboot.service.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @Autowired
    private Cart_ProductService cart_ProductService;

    @RequestMapping("/show")
    public String showCart(Model model) {

        model.addAttribute("cart_products", cart_ProductService.getAllCart_Product(Sort.by(Sort.Direction.ASC, "id")));
        model.addAttribute("products", productService.getAllProducts(Sort.by(Sort.Direction.ASC, "id")));

        return "/auth/user/Cart";
    }

    @GetMapping("/save/{id}")
    public String saveNewCart(@Valid Cart cart, @Valid Cart_Product cart_Product, Model model,
            @PathVariable("id") long id) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            UserDetail userDetail = (UserDetail) auth.getPrincipal();

            cart = cartService.getCartByUserId(userDetail.getId());

            if (cart == null) {
                cart = new Cart();
                cart.setUser(userService.getUserById(userDetail.getId()));
                cartService.saveCart(cart);
            }
        }
        Product product = productService.getProductById(id);

        List<Cart_Product> cart_Products = cart_ProductService.getAllCart_Product(Sort.by(Sort.Direction.ASC, "id"));

        cart_Product = cart_Products.stream().filter(cp -> cp.getProduct().getId() == id).findFirst().orElse(null);

        if (cart_Product == null) {
            cart_Product = new Cart_Product();
            cart_Product.setCart(cart);
            cart_Product.setProduct(product);
            cart_Product.setQuantity(1);
            cart_ProductService.saveCart_Product(cart_Product);
        } else {
            cart_Product.setQuantity(cart_Product.getQuantity() + 1);
        }

        cart_ProductService.saveCart_Product(cart_Product);

        return "redirect:/cart/show";
    }

    @GetMapping("/delete/{id}")
    public String deleteProductCart(@PathVariable("id") long id) {

        cart_ProductService.deleteCartProduct(id);

        return "redirect:/cart/show";
    }

    @GetMapping("/cancel")
    public String cancelCart() {

        cartService.cancelCart();

        return "redirect:/";
    }
}
