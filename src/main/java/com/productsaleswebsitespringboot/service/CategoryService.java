package com.productsaleswebsitespringboot.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.productsaleswebsitespringboot.model.Category;

public interface CategoryService {

    public void deleteCategory(Long id);

    public Category getCategoryById(Long id);

    public Category saveCategory(Category category);

    public void updateCategory(Category category);

    public List<Category> getAllCategories(Sort sort);
}
