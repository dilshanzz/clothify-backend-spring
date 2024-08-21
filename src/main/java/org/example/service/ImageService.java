package org.example.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    public boolean uploadImage(MultipartFile multipartFile);

    public byte[] getImage(String name);
}
