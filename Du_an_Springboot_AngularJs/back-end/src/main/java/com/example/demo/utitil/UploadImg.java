package com.example.demo.utitil;

import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class UploadImg {

    @Autowired
    ServletContext context;

    public File save(MultipartFile file, String path) {
        File dir = new File(context.getRealPath("/image/"));
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String s = System.currentTimeMillis() + file.getOriginalFilename();
        String name = Integer.toHexString(s.hashCode()) + s.substring(s.lastIndexOf("."));
        try {
            File fileSave = new File(dir, name);
            file.transferTo(fileSave);
            return fileSave;
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }
    }
}
