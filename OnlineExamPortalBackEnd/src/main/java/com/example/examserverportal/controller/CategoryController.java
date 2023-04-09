package com.example.examserverportal.controller;


import com.example.examserverportal.model.exam.Category;
import com.example.examserverportal.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.UserTransaction;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")

public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //add category
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public ResponseEntity<Category> addCategory(@RequestBody Category category){
        Category category1= this.categoryService.addCategory(category);
        return new ResponseEntity<Category>(category1, HttpStatus.CREATED);
    }


//    orElseThrow(()-> new  ResourceNotFoundException("user", "id", userId));
    //get category
    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategory(@PathVariable("categoryId") Long categoryId){
        Category category= this.categoryService.getCategory(categoryId);
        return new ResponseEntity<Category>(category, HttpStatus.ACCEPTED);

    }

    //get all category
    @GetMapping("/")
    public ResponseEntity<Object> getCategory(){
      Set<Category> category1= this.categoryService.getCategories();

        return new  ResponseEntity<Object>(category1, HttpStatus.ACCEPTED);

    }

    //update category
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category){
        Category category2=this.categoryService.updateCategory(category);
        return new ResponseEntity<Category>(category2, HttpStatus.OK) ;
    }

    //delete category
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable("categoryId") Long categoryId){

        Category category3=this.categoryService.deleteCategory(categoryId);
        return new ResponseEntity<String>("Deleted successfully..!!", HttpStatus.OK);
    }


}
