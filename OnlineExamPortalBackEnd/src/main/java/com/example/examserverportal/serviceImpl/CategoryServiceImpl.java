package com.example.examserverportal.serviceImpl;

import com.example.examserverportal.helper.IdNotFound;
import com.example.examserverportal.model.exam.Category;
import com.example.examserverportal.repository.CategoryRepository;
import com.example.examserverportal.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public Category addCategory(Category category) {
        return  this.categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        return this.categoryRepository.save(category);
    }

    @Override
    public Set<Category> getCategories() {
        return  new LinkedHashSet<>(this.categoryRepository.findAll());
    }

    @Override
    public Category getCategory(Long categoryId) {
     Category category=this.categoryRepository.findById(categoryId).
             orElseThrow(()-> new IdNotFound("Category", "id", "categoryId"));
     return category;
    }

    @Override
    public Category deleteCategory(Long categoryId) {
        Category category=this.categoryRepository.findById(categoryId).
                orElseThrow(()-> new IdNotFound("Category", "id", "categoryId"));
        category.setCid(categoryId);
        this.categoryRepository.delete(category);

        return category;
    }
}
