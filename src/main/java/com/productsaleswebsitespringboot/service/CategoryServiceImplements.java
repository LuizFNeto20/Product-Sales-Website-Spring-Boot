package com.productsaleswebsitespringboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.productsaleswebsitespringboot.model.Category;
import com.productsaleswebsitespringboot.repository.CategoryRepository;

@Service
public class CategoryServiceImplements implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void deleteCategory(Long id) {
        Category category = getCategoryById(id);
        categoryRepository.delete(category);
    }

    @Override
    public Category getCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            return category.get();
        } else {
            throw new IllegalArgumentException(id + " não encontrado");
        }
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void updateCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategories(Sort sort) {
        List<Category> categoryList = categoryRepository.findAll(sort);
        return categoryList;
    }

}
