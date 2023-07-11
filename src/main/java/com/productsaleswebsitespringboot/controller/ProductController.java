package com.productsaleswebsitespringboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.productsaleswebsitespringboot.model.Product;
import com.productsaleswebsitespringboot.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/new")
    public String adicionarUsuario(Model model) {
        model.addAttribute("usuario", new Product());
        return "/auth/admin/productform";
    }

    @RequestMapping("/admin/list")
    public String list(Model model) {
        List<Product> products = productService.getAllProducts(Sort.by(Sort.Direction.ASC, "id"));
        model.addAttribute("products", products);
        return "/auth/admin/productlist";
    }
}
