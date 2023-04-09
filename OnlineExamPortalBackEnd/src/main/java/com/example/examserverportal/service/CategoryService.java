package com.example.examserverportal.service;

import com.example.examserverportal.model.exam.Category;

import java.util.Set;

public interface CategoryService {

    public Category addCategory(Category category);

    public Category updateCategory(Category category);

    public Set<Category> getCategories();

    public Category getCategory(Long categoryId);

    public Category deleteCategory(Long categoryId);
}
