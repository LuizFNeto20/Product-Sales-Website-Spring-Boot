package com.productsaleswebsitespringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.productsaleswebsitespringboot.model.Product;
import com.productsaleswebsitespringboot.model.Review;
import com.productsaleswebsitespringboot.model.Users;
import com.productsaleswebsitespringboot.service.ProductService;
import com.productsaleswebsitespringboot.service.ReviewService;
import com.productsaleswebsitespringboot.service.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @PostMapping("/save/{id}")
    public String saveReview(@Valid Review review, BindingResult result,
            RedirectAttributes attributes, Model model, @PathVariable("id") long idProduct, @RequestParam("user_id") long idUser) {

        if (result.hasErrors()) {
            return "redirect:/product/info/" + idProduct;
        }

        Product product = productService.getProductById(idProduct);
        Users user = userService.getUserById(idUser);

        review.setProduct(product);
        review.setUser(user);

        reviewService.saveReview(review);

        return "redirect:/product/info/" + idProduct;
    }

    @GetMapping("/admin/delete/{id}")
    public String deleteReview(@PathVariable("id") long idReview) {
        Review review = reviewService.getReviewById(idReview);
        long idProduct = review.getProduct().getId();
        reviewService.deleteReview(idReview);
        return "redirect:/product/info/" + idProduct;
    }
}
