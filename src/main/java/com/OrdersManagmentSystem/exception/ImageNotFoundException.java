package com.OrdersManagmentSystem.exception;

import com.OrdersManagmentSystem.service.ImageService;

public class ImageNotFoundException extends RuntimeException{

    public ImageNotFoundException(String message){
        super(message);
    }
}
