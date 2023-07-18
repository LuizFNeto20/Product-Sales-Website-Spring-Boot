package com.productsaleswebsitespringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.productsaleswebsitespringboot.service.ProductService;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("products", productService.getAllProducts(Sort.by(Sort.Direction.ASC, "id")));
        return "index";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        return "login";
    }
}
