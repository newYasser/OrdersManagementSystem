package com.OrdersManagmentSystem.exception;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(String message){
            super(message);
    }
}
