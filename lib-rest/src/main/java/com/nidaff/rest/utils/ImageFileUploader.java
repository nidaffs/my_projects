package com.nidaff.rest.utils;

import com.nidaff.api.dto.UserDto;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class ImageFileUploader {

    private static final String IMAGE_EXTENSION = ".png";
    
    private static final String FOLDER_PATH = "classpath:static/images/";

    public void createOrUpdateImage(UserDto dto, MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            String login = dto.getLogin();
            String filePath = new StringBuilder(FOLDER_PATH).append(login).append(IMAGE_EXTENSION).toString();
            File userImage;
            try {
                userImage = ResourceUtils.getFile(filePath);
            } catch (FileNotFoundException e) {
                URL fileUrl = ResourceUtils.getURL(FOLDER_PATH);
                userImage = new File(
                        new StringBuilder(fileUrl.getPath()).append(login).append(IMAGE_EXTENSION).toString());
            }
            Path path = Paths.get(userImage.getPath());
            byte[] bytes = file.getBytes();
            Files.write(path, bytes);
        }
    }
    
}
