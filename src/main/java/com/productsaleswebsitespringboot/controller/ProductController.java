package com.productsaleswebsitespringboot.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.productsaleswebsitespringboot.model.Category;
import com.productsaleswebsitespringboot.model.Product;
import com.productsaleswebsitespringboot.model.Review;
import com.productsaleswebsitespringboot.model.Supplier;
import com.productsaleswebsitespringboot.service.CategoryService;
import com.productsaleswebsitespringboot.service.ProductService;
import com.productsaleswebsitespringboot.service.ReviewService;
import com.productsaleswebsitespringboot.service.SupplierService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/product")
public class ProductController {

    private static String imagePath = "/Users/luizf/Desktop/Projetos/Spring Boot/Product-Sales-Website-Spring-Boot/src/main/resources/templates/auth/admin/image/";

    @Autowired
    private ProductService productService;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/new")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("isNewProduct", true);

        List<Supplier> suppliers = supplierService.getAllSuppliers(Sort.by(Sort.Direction.ASC, "id"));
        model.addAttribute("suppliers", suppliers);

        List<Category> categories = categoryService.getAllCategories(Sort.by(Sort.Direction.ASC, "id"));
        model.addAttribute("categories", categories);

        return "/auth/admin/forms/ProductForm";
    }

    @GetMapping("/showImage/{image}")
    @ResponseBody
    public byte[] showImage(@PathVariable("image") String image) throws IOException {
        File fileImage = new File(imagePath + image);
        if (image != null) {
            return Files.readAllBytes(fileImage.toPath());
        }

        return null;
    }

    @PostMapping("/save")
    public String saveProduct(@Valid Product product, BindingResult result,
            RedirectAttributes attributes, Model model, @RequestParam("file") MultipartFile file) {

        if (result.hasErrors()) {
            return "/auth/admin/forms/ProductForm";
        }

        try {
            if (!file.isEmpty()) {
                byte[] bytes = file.getBytes();

                String ImageName = String.valueOf(product.getId()) + file.getOriginalFilename();

                Path path = Paths.get(imagePath + ImageName);
                Files.write(path, bytes);

                product.setImage(ImageName);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        productService.saveProduct(product);

        attributes.addFlashAttribute("mensagem", "saved product");
        return "redirect:/product/new";
    }

    @RequestMapping("/admin/list")
    public String list(Model model) {
        List<Product> products = productService.getAllProducts(Sort.by(Sort.Direction.ASC, "id"));
        model.addAttribute("products", products);
        return "/auth/admin/listings/ProductList";
    }

    @GetMapping("/admin/delete/{id}")
    public String deleteProduct(@PathVariable("id") long id) {
        productService.deleteProduct(id);
        return "redirect:/product/admin/list";
    }

    @GetMapping("/admin/edit/{id}")
    public String editProduct(Model model, @PathVariable("id") long id) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("isNewProduct", false);

        List<Supplier> suppliers = supplierService.getAllSuppliers(Sort.by(Sort.Direction.ASC, "id"));
        model.addAttribute("suppliers", suppliers);

        List<Category> categories = categoryService.getAllCategories(Sort.by(Sort.Direction.ASC, "id"));
        model.addAttribute("categories", categories);

        return "/auth/admin/forms/ProductForm";
    }

    @PostMapping("/admin/update/{id}")
    public String updateProduct(@Valid Product product, BindingResult result,
            @PathVariable("id") long id, @RequestParam("file") MultipartFile file,
            @RequestParam("oldImage") String oldImage) {

        if (result.hasErrors()) {
            product.setId(id);
            return "/auth/admin/forms/ProductForm";
        }

        try {
            if (!file.isEmpty()) {
                byte[] bytes = file.getBytes();

                String ImageName = String.valueOf(product.getId()) + file.getOriginalFilename();

                Path path = Paths.get(imagePath + ImageName);
                Files.write(path, bytes);

                product.setImage(ImageName);
            } else {
                product.setImage(oldImage);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        productService.updateProduct(product);

        return "redirect:/product/admin/list";
    }

    @GetMapping("/info/{id}")
    public String info(Model model, @PathVariable("id") long id) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("review", new Review());
        model.addAttribute("reviews", reviewService.getAllReviews(Sort.by(Sort.Direction.ASC, "id")));

        List<Review> reviews = reviewService.getAllReviews(Sort.by(Sort.Direction.ASC, "id"));

        int avg = 0, sum = 0, count = 0;

        if (!reviews.isEmpty()) {
            count = 0;
            for (Review review : reviews) {
                if (review.getProduct().getId() == id) {
                    sum = sum + review.getAssessment();
                    count++;
                }
            }
        }

        if (count > 0) {
            avg = sum / count;
        } else {
            avg = 0;
        }
        
        model.addAttribute("avgAssessment", avg);

        return "/auth/user/ProductInformation";
    }
}
