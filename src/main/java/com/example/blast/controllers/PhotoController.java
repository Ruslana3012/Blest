package com.example.blast.controllers;

import com.example.blast.models.Photo;
import com.example.blast.repositories.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.ByteArrayInputStream;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class PhotoController {
    private final PhotoRepository photoRepository;

    @GetMapping("/photos/{id}")
    private ResponseEntity<?> getPhotoById(@PathVariable Long id, Model model, Principal principal) {
        model.addAttribute("user", principal);
        Photo photo = photoRepository.findById(id).orElse(null);
        return ResponseEntity.ok()
                .header("fileName", photo.getOriginalFileName())
                .contentType(MediaType.valueOf(photo.getContentType()))
                .contentLength(photo.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(photo.getBytes())));
    }
}
