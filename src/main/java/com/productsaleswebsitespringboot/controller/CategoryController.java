package com.productsaleswebsitespringboot.controller;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.productsaleswebsitespringboot.model.Category;
import com.productsaleswebsitespringboot.service.CategoryService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/new")
    public String newCategory(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("isNewCategory", true);
        return "/auth/admin/forms/CategoryForm";
    }

    @PostMapping("/save")
    public String saveCategory(@Valid Category category, BindingResult result,
            RedirectAttributes attributes, Model model) {

        if (result.hasErrors()) {
            return "/auth/admin/forms/CategoryForm";
        }

        categoryService.saveCategory(category);

        attributes.addFlashAttribute("mensagem", "saved category");
        return "redirect:/category/new";
    }

    @RequestMapping("/admin/list")
    public String list(Model model) {
        List<Category> categoryList = categoryService.getAllCategories(Sort.by(Sort.Direction.ASC, "id"));
        model.addAttribute("categoryList", categoryList);
        return "/auth/admin/listings/CategoryList";
    }

    @GetMapping("/admin/delete/{id}")
    public String deleteCategory(Model model, @PathVariable("id") long id) {
        categoryService.deleteCategory(id);
        return "redirect:/category/admin/list";
    }

    @GetMapping("/admin/edit/{id}")
    public String editCategory(Model model, @PathVariable("id") long id) {
        Category category = categoryService.getCategoryById(id);
        model.addAttribute("category", category);
        model.addAttribute("isNewCategory", false);
        return "/auth/admin/forms/CategoryForm";
    }

    @PostMapping("/admin/update/{id}")
    public String updateCategory(@Valid Category category, BindingResult result,
            @PathVariable("id") long id) {

        if (result.hasErrors()) {
            category.setId(id);
            return "/auth/admin/forms/CategoryForm";
        }

        categoryService.updateCategory(category);

        return "redirect:/category/admin/list";
    }

}
