package com.OrdersManagmentSystem.exception;

public class CategoryAlreadyExistsException extends RuntimeException {

    public CategoryAlreadyExistsException(String message){
        super(message);
    }
}
