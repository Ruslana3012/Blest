package com.example.blast.services;

import com.example.blast.models.Photo;
import com.example.blast.models.Sofa;
import com.example.blast.repositories.SofaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SofaService {
    private final SofaRepository sofaRepository;

    public void saveNewSofa(Sofa sofa, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        Photo photo1;
        Photo photo2;
        Photo photo3;

        if (file1.getSize() != 0) {
            photo1= toImageEntity(file1);
            photo1.setPreviewPhoto(true);
            sofa.addPhotoToSofa(photo1);
        } else if (file2.getSize() != 0) {
            photo2= toImageEntity(file2);
            sofa.addPhotoToSofa(photo2);
        } else if (file3.getSize() != 0) {
            photo3= toImageEntity(file3);
            sofa.addPhotoToSofa(photo3);
        }

        Sofa sofaFromDB = sofaRepository.save(sofa);
        sofaFromDB.setPreviewPhotoId(sofaFromDB.getPhotos().get(0).getId());
        sofaRepository.save(sofa);
    }

    private Photo toImageEntity(MultipartFile file) throws IOException {
        Photo photo = new Photo();
        photo.setName(file.getName());
        photo.setOriginalFileName(file.getOriginalFilename());
        photo.setContentType(file.getContentType());
        photo.setSize(file.getSize());
        photo.setBytes(file.getBytes());
        return photo;
    }
    public List<Sofa> getAllSofas() { return sofaRepository.findAll(); }

    public List<Sofa> getAllByType(String type) { return sofaRepository.findAllByType(type); }

    public List<Sofa> getAllByPrice(Long price) { return sofaRepository.findAllByPrice(price); }

    public Sofa getById(Long id) { return sofaRepository.findById(id); }
}
