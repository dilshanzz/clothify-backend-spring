package org.example.controller;

import org.example.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/img")
@CrossOrigin
public class ImageFileController {
    @Autowired
    ImageService imageService;

    @PostMapping("/add")
    public boolean uploadImage(@RequestParam("image") MultipartFile image){
        return imageService.uploadImage(image);
    }

    @GetMapping("/get/{name}")
    public ResponseEntity<byte[]> getImage(@PathVariable String name) {
        byte[] imageData = imageService.getImage(name);
        if (imageData != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "image/jpeg"); // Change this if your images are of a different type
            return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
