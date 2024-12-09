package com.OrdersManagmentSystem.service;

import com.OrdersManagmentSystem.dto.ImageDto;
import com.OrdersManagmentSystem.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    Image getImageById(Long id);
    List<ImageDto> saveImages(List<MultipartFile> file, Long productId);
    void updateImage(MultipartFile file, Long id);
    void deleteImageById(Long id);
}
