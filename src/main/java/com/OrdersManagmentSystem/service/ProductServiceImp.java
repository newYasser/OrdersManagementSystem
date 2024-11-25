package com.OrdersManagmentSystem.service;

import com.OrdersManagmentSystem.entity.Category;
import com.OrdersManagmentSystem.entity.Product;
import com.OrdersManagmentSystem.exception.ProductNotFoundException;
import com.OrdersManagmentSystem.repository.ProductRepository;
import com.OrdersManagmentSystem.request.AddProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.color.ProfileDataException;
import java.util.List;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product addProduct(AddProductRequest request) {

        return null;
    }

    private Product addProduct(AddProductRequest request, Category category){
        return new Product(
                request.getName(),
                request.getDescription(),
                request.getBrand(),
                request.getPrice(),
                request.getQuantity(),
                category

        );
    }
    @Override
    public Product getProductById(Long id) {

        return productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("Product Not Found"));
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.findById(id)
                .ifPresentOrElse(productRepository::delete,
                        ()-> {throw new ProfileDataException("Product Not Found");});
    }

    @Override
    public void updateProduct(Product product, Long id) {

    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrands(String category, String brand) {
        return productRepository.findByCategoryNameAndBrand(category,brand);
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findByCategoryName(name);
    }

    @Override
    public Long countProductsByBrandsAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand,name);
    }
}
