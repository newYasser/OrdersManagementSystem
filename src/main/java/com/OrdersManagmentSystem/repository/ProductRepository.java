package com.OrdersManagmentSystem.repository;

import com.OrdersManagmentSystem.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findByCategoryName(String category);
    List<Product> findByCategoryNameAndBrand(String category, String brand);
    List<Product> findByBrand(String brand);
    Long countByBrandAndName(String brand,String name);
    List<Product> findByName(String name);

}
