package com.OrdersManagmentSystem.service.impl;

import com.OrdersManagmentSystem.dto.ImageDto;
import com.OrdersManagmentSystem.entity.Image;
import com.OrdersManagmentSystem.entity.Product;
import com.OrdersManagmentSystem.exception.ImageNotFoundException;
import com.OrdersManagmentSystem.repository.ImageRepository;
import com.OrdersManagmentSystem.service.ImageService;
import com.OrdersManagmentSystem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageServiceImp implements ImageService {


    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ProductService productService;

    @Override
    public Image getImageById(Long id) {
        return imageRepository.findById(id)
                .orElseThrow(()->new ImageNotFoundException("Image Not Found"));
    }


    // TO DO : needs to be simpler
    @Override
    public List<ImageDto> saveImages(List<MultipartFile> files, Long productId) {
        Product product = productService.getProductById(productId);
        List<ImageDto> savedImagesDto = new ArrayList<>();

        for(MultipartFile file: files){
            try {
                Image image = new Image();
                image.setName(file.getOriginalFilename());
                image.setType(file.getContentType());
                image.setImage(new SerialBlob(file.getBytes()));
                image.setProduct(product);

                String downloadUrl = "/api/v1/images/image/download/";
                image.setDownloadUrl(downloadUrl + image.getId());
                Image savedImage = imageRepository.save(image);
                savedImage.setDownloadUrl(downloadUrl + savedImage.getId());
                imageRepository.save(image);

                ImageDto imageDto = new ImageDto();
                imageDto.setImageId(savedImage.getId());
                imageDto.setDownloadUrl(savedImage.getDownloadUrl());
                imageDto.setImageName(savedImage.getName());

                savedImagesDto.add(imageDto);

            } catch (IOException | SQLException e){
                throw new RuntimeException(e.getMessage());
            }
        }
        return savedImagesDto;
    }

    @Override
    public void updateImage(MultipartFile file, Long id) {

        Image image = getImageById(id);
        try {
            image.setName(file.getOriginalFilename());
            image.setImage(new SerialBlob(file.getBytes()));
            imageRepository.save(image);
        }
        catch (IOException | SQLException e){
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public void deleteImageById(Long id) {
        imageRepository.findById(id)
                .ifPresentOrElse(imageRepository::delete,()->{
                    throw new ImageNotFoundException("Image Not Found");
                });
    }
}
