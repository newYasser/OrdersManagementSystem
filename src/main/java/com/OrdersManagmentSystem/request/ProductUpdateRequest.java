package com.OrdersManagmentSystem.request;

import com.OrdersManagmentSystem.entity.Category;
import lombok.Data;

@Data
public class ProductUpdateRequest {
    private Long id;
    private String name;
    private String description;
    private String brand;
    private Double price;
    private int quantity;
    private Category category;
}
