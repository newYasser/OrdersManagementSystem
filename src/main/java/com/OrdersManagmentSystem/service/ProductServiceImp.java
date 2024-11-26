package com.OrdersManagmentSystem.service;

import com.OrdersManagmentSystem.entity.Category;
import com.OrdersManagmentSystem.entity.Product;
import com.OrdersManagmentSystem.exception.ProductNotFoundException;
import com.OrdersManagmentSystem.repository.CategoryRepository;
import com.OrdersManagmentSystem.repository.ProductRepository;
import com.OrdersManagmentSystem.request.AddProductRequest;
import com.OrdersManagmentSystem.request.ProductUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.color.ProfileDataException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Product addProduct(AddProductRequest request) {

        Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
                .orElseGet(() -> {
                    Category newCategory = new Category(request.getCategory().getName());
                    return categoryRepository.save(newCategory);
                });


        request.setCategory(category);
        return productRepository.save(creatProduct(request));
    }

    private Product creatProduct(AddProductRequest request){
        return new Product(
                request.getName(),
                request.getDescription(),
                request.getBrand(),
                request.getPrice(),
                request.getQuantity(),
                request.getCategory()

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
    public Product updateProduct(ProductUpdateRequest request, Long id) {

        return productRepository.findById(id)
                .map(product -> updateProduct(product,request))
                .map(productRepository::save)
                .orElseThrow(() -> new ProductNotFoundException("Product Not Found"));

    }

    private Product updateProduct(Product product, ProductUpdateRequest request){
        product.setName(request.getName());
        product.setBrand(request.getBrand());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());
        product.setQuantity(request.getQuantity());
        Category category = categoryRepository.findByName(request.getCategory().getName());
        product.setCategory(category);
        return product;
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
