package com.OrdersManagmentSystem.service;

import com.OrdersManagmentSystem.entity.Product;
import com.OrdersManagmentSystem.exception.ProductNotFoundException;
import com.OrdersManagmentSystem.request.AddProductRequest;
import com.OrdersManagmentSystem.request.ProductUpdateRequest;

import java.util.List;

public interface ProductService {

    Product addProduct(AddProductRequest request);
    Product getProductById(Long id) throws ProductNotFoundException;
    void deleteProductById(Long id);
    public Product updateProduct(ProductUpdateRequest request, Long id);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByCategoryAndBrands(String category, String brand);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByName(String name);
    Long countProductsByBrandsAndName(String brand, String name);

}
