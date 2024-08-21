package org.example.service.impl;

import org.example.entity.ImageFile;
import org.example.repository.FileDataRepository;
import org.example.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    FileDataRepository fileDataRepository;

    String baseURL = "/Users/dashannadeema/Developer/e-commerce/e-commerce-backend/ecommerce-backend/images/";

    @Override
    public boolean uploadImage(MultipartFile multipartFile) {
        String name = multipartFile.getOriginalFilename();
        System.out.println(name);
        ImageFile saved = fileDataRepository.save(ImageFile.builder().type(multipartFile.getContentType()).filPath(baseURL + name).build());
        return saved != null;
    }

    @Override
    public byte[] getImage(String name) {
        try {
            String filePath = baseURL + name;
            return Files.readAllBytes(Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
