package com.example.blast.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sofas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sofa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @OneToMany(mappedBy = "sofa",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private List<Photo> photos = new ArrayList<>();
    private Long previewPhotoId;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type;
    @Column(name = "price")
    private Long price;
    @Column(name = "currency")
    private String currency;
    @Column(name = "description")
    private String description;
    @Column(name = "video")
    private String video;

    public void addPhotoToSofa(Photo photo) {
        photo.setSofa(this);
        photos.add(photo);
    }

    public Sofa (String name, String type, Long price, String currency, String description, String video) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.currency = currency;
        this.description = description;
        this.video = video;
    }
}
