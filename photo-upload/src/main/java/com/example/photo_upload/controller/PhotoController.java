package com.example.photo_upload.controller;

import com.example.photo_upload.model.Photo;
import com.example.photo_upload.service.PhotoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class PhotoController {

    private final PhotoService photoService;

    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping("/upload")
    public String showUploadPage() {
        return "upload";
    }

    @PostMapping("/upload")
    public String uploadPhoto(@RequestParam("file") MultipartFile file, Model model) {
        try {
            Photo photo = photoService.uploadPhoto(file);
            model.addAttribute("message", "Photo uploaded successfully!");
            model.addAttribute("photo", photo);
        } catch (IOException e) {
            model.addAttribute("message", "Failed to upload photo.");
        }
        return "result";
    }
}
