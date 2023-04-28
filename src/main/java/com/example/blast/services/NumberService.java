package com.example.blast.services;

import com.example.blast.models.Number;
import com.example.blast.repositories.NumberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NumberService {
    private final NumberRepository numberRepository;

    public void saveNewPhoneNumber(Number number) {
        numberRepository.save(number);
    }
}
