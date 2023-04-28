package com.example.blast.repositories;

import com.example.blast.models.Number;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NumberRepository extends JpaRepository<Number, String> {
    @Override
    List<Number> findAll();
}
