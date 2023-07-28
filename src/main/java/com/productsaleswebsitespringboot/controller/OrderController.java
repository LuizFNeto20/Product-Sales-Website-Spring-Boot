package com.productsaleswebsitespringboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.productsaleswebsitespringboot.model.Cart;
import com.productsaleswebsitespringboot.model.Orders;
import com.productsaleswebsitespringboot.model.Product;
import com.productsaleswebsitespringboot.model.Status;
import com.productsaleswebsitespringboot.security.UserDetail;
import com.productsaleswebsitespringboot.service.CartService;
import com.productsaleswebsitespringboot.service.OrderService;
import com.productsaleswebsitespringboot.service.ProductService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @RequestMapping("/admin/list")
    public String listOrders(Model model) {

        model.addAttribute("orderList", orderService.getAllOrder(Sort.by(Sort.Direction.ASC, "id")));

        return "/auth/admin/listings/OrderList";
    }

    @GetMapping("/save")
    public String saveNewOrder(@Valid Orders order, @Valid Cart cart) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            UserDetail userDetail = (UserDetail) auth.getPrincipal();

            Cart userCart = cartService.getCartByUserId(userDetail.getId());

            order.setUser(userCart.getUser());
            order.setStatus(Status.inProgress);

            List<Product> products = productService.getAllProducts(Sort.by(Sort.Direction.ASC, "id"));

            products = products.stream()
                    .filter(p -> userCart.getCart_Product().stream().anyMatch(cp -> cp.getProduct().getId() == p.getId()))
                    .toList();

            if (products.isEmpty()) {
                throw new IllegalStateException("No products found in the cart.");
            } else {
                order.setProducts(products);
            }

            orderService.saveOrder(order);

            cart = cartService.getCartById(userCart.getId());
            cart.setOrder(order);
            cartService.saveCart(cart);
        }

        cartService.cancelCart();

        return "redirect:/";
    }
}
