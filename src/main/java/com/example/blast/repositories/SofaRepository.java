package com.example.blast.repositories;

import com.example.blast.models.Sofa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SofaRepository extends JpaRepository<Sofa, String> {
    @Override
    List<Sofa> findAll();

    List<Sofa> findAllByType(String type);

    List<Sofa> findAllByPrice(Long price);

    Sofa findById(Long id);
}
