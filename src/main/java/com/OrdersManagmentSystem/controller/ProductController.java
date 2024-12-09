package com.OrdersManagmentSystem.controller;


import com.OrdersManagmentSystem.entity.Product;
import com.OrdersManagmentSystem.exception.ProductNotFoundException;
import com.OrdersManagmentSystem.request.AddProductRequest;
import com.OrdersManagmentSystem.request.ProductUpdateRequest;
import com.OrdersManagmentSystem.response.Response;
import com.OrdersManagmentSystem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<Response> getAllProducts(){

        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(new Response("Success",products));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getProductById(@PathVariable Long id){

        try{
            Product product = productService.getProductById(id);
            return ResponseEntity.ok(new Response("success",product));
        } catch (ProductNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Response(e.getMessage(),null));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Response>addProduct(@RequestBody AddProductRequest product){

        try {
            Product newProduct = productService.addProduct(product);
            return ResponseEntity.ok(new Response("success",product));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response(e.getMessage(),null));

        }
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<Response> updateProduct(@RequestBody ProductUpdateRequest request,
                                                  @PathVariable Long productId) {
        try{
            Product product = productService.updateProduct(request,productId);
            return ResponseEntity.ok(new Response("success",product));
        } catch (ProductNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Response(e.getMessage(),null));

        }

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteProduct(@PathVariable Long id){
        try{
            productService.deleteProductById(id);
            return ResponseEntity.ok(new Response("Deleted",null));
        } catch (ProductNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Response(e.getMessage(),null));

        }

    }

    @GetMapping("get/{name}")
    public ResponseEntity<Response> getProductName(@PathVariable String name){

        try {
            List<Product> products = productService.getProductsByName(name);

            if(products.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new Response("No Products Found",null));
            }
            return ResponseEntity.ok(new Response("Success",products));

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response(e.getMessage(),null));
        }
    }

    @GetMapping("/by/category-and-brand")
    public ResponseEntity<Response> getProductByCategoryAndBrand(@RequestParam String categoryName,
                                                             @RequestParam String brandName){

        try {
            List<Product> products = productService.getProductsByCategoryAndBrands(categoryName,brandName);

            if(products.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new Response("No Products Found",null));
            }
            return ResponseEntity.ok(new Response("Success",products));

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response(e.getMessage(),null));
        }
    }
    @GetMapping("by/category/{category}")
    public ResponseEntity<Response> getProductByCategory(@PathVariable String category){

        try {
            List<Product> products = productService.getProductsByCategory(category);

            if(products.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new Response("No Products Found",null));
            }
            return ResponseEntity.ok(new Response("Success",products));

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response(e.getMessage(),null));
        }
    }

    @GetMapping("by/brand/{brand}")
    public ResponseEntity<Response> getProductByBrand(@PathVariable String brand){

        try {
            List<Product> products = productService.getProductsByBrand(brand);

            if(products.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new Response("No Products Found",null));
            }
            return ResponseEntity.ok(new Response("Success",products));

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response(e.getMessage(),null));
        }
    }


}
