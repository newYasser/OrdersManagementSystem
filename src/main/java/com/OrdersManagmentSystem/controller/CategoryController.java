package com.OrdersManagmentSystem.controller;


import com.OrdersManagmentSystem.entity.Category;
import com.OrdersManagmentSystem.exception.CategoryAlreadyExistsException;
import com.OrdersManagmentSystem.exception.CategoryNotFoundException;
import com.OrdersManagmentSystem.response.Response;
import com.OrdersManagmentSystem.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/all")
    public ResponseEntity<Response> getAllCategories(){

        try{
            List<Category>categories = categoryService.getAllCategory();
            return ResponseEntity.ok(new Response("Success",categories));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response("Error", e.getMessage()));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Response> addCategory(@RequestBody Category category){
        try {
            Category newCategory = categoryService.addCategory(category);
            return ResponseEntity.ok(new Response("Success",newCategory));
        } catch (CategoryAlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new Response("Failed",null));
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getCategoryById(@PathVariable Long id){

        try {
            Category category = categoryService.getCategoryById(id);
            return ResponseEntity.ok(new Response("Found",category));
        } catch (CategoryNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Response("Not Found",null));
        }

    }

    @GetMapping("/get/{name}")
    public ResponseEntity<Response> getCategoryByName(@PathVariable String name){
        try {
            Category category = categoryService.getCategoryByName(name);
            return ResponseEntity.ok(new Response("Found",category));
        } catch (CategoryNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Response("Not Found",null));
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteCategory(@PathVariable Long id){
        try {
            categoryService.deleteCategoryById(id);
            return ResponseEntity.ok(new Response("Success",null));
        } catch (CategoryNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Response("Not Found",null));
        }

    }





}
