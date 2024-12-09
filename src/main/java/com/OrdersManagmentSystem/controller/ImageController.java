package com.OrdersManagmentSystem.controller;

import com.OrdersManagmentSystem.dto.ImageDto;
import com.OrdersManagmentSystem.entity.Image;
import com.OrdersManagmentSystem.exception.ImageNotFoundException;
import com.OrdersManagmentSystem.response.Response;
import com.OrdersManagmentSystem.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("${api.prefix}/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<Response> saveImages(@RequestParam List<MultipartFile> files,
                                               @RequestParam Long productId){
        try {
            List<ImageDto> imageDtos = imageService.saveImages(files,productId);
            return ResponseEntity.ok(new Response("Upload success!",imageDtos));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response("Upload Failed",e.getMessage()));
        }


    }

    @GetMapping("/image/download/{imageId}")
    public ResponseEntity<Resource> downloadImage(@PathVariable Long imageId) throws SQLException {

        Image image = imageService.getImageById(imageId);
        ByteArrayResource resource =
                new ByteArrayResource(image.getImage().getBytes(1,(int) image.getImage().length()));

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(image.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getName()+"\"")
                .body(resource);
    }

    @PutMapping("/image/{imageId}/update")
    public ResponseEntity<Response> updateImage(@PathVariable Long imageId, @RequestBody MultipartFile file){

        try {
            Image image = imageService.getImageById(imageId);
            imageService.updateImage(file, imageId);
            return ResponseEntity.ok(new Response("Update Success", null));
        }catch (ImageNotFoundException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response("Upload Failed",e.getMessage()));
        }
    }

    @DeleteMapping("/image/{imageId}/delete")
    public ResponseEntity<Response> DeleteImage(@PathVariable Long imageId) {

        try {
            Image image = imageService.getImageById(imageId);
            imageService.deleteImageById(imageId);
            return ResponseEntity.ok(new Response("Update Success", null));
        }catch (ImageNotFoundException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response("Upload Failed",e.getMessage()));
        }
    }


}
