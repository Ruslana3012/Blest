package com.example.blast.controllers;

import com.example.blast.models.Number;
import com.example.blast.models.Sofa;
import com.example.blast.services.NumberService;
import com.example.blast.services.SofaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final SofaService sofaService;
    private final NumberService numberService;

    @GetMapping("/blast_ua")
    public String getMainPage() {
        return "main-page";
    }

    @GetMapping("/sofas")
    public String getAllSofas(Model model) {
        List<Sofa> sofas = sofaService.getAllSofas();
        sofas.forEach(sofa -> sofa.getPhotos()
                .forEach(photo -> photo.setBase64Photo(sofaService.encodedString(photo.getBytes()))));
        model.addAttribute("sofas", sofas);
        return "sofas";
    }

    @GetMapping("/sofas/{type}")
    public String getAllSofasByType(@PathVariable(name = "type") String type, Model model) {
        List<Sofa> sofas = sofaService.getAllByType(type);
        sofas.forEach(sofa -> sofa.getPhotos()
                .forEach(photo -> photo.setBase64Photo(sofaService.encodedString(photo.getBytes()))));
        model.addAttribute("sofas", sofas);
        return "sofas";
    }

    @GetMapping("/sofas/by/price")
    public List<Sofa> getAllSofasByPrice(@RequestParam(name = "price") Long price) {
        return sofaService.getAllByPrice(price);
    }

    @PostMapping ("/my-sofa")
    public String addSofa(@RequestParam("name") String name,
                          @RequestParam("type") String type,
                          @RequestParam("price") Long price,
                          @RequestParam("currency") String currency,
                          @RequestParam("description") String description,
                          @RequestParam("video") String video,
                          @RequestParam("file1") MultipartFile file1,
                          @RequestParam("file2") MultipartFile file2,
                          @RequestParam("file3") MultipartFile file3) throws IOException {
        Sofa sofa = new Sofa(name, type, price, currency, description, video);
        sofaService.saveNewSofa(sofa, file1, file2, file3);
        return "main-page";
    }

    @GetMapping("/my-sofa")
    public String addSofa(Model model) {
        model.addAttribute("user", new Sofa());
        return "my-sofa";
    }

    @GetMapping("/sofa/{id}")
    public String sofaInfo(@PathVariable Long id, Model model) {
        Sofa sofa = sofaService.getById(id);
        model.addAttribute("sofa", sofa);
        model.addAttribute("photos", sofa.getPhotos());
        return "sofa-info";
    }

    @RequestMapping(value = "/saveNumber", method = RequestMethod.POST)
    public String saveNumber(@ModelAttribute Number number) {
        numberService.saveNewPhoneNumber(number);
        return "main-page";
    }

    @GetMapping("/christmas")
    public String getNewsChristmas() {
        return "christmas";
    }

    @GetMapping("/all4Ukraine")
    public String getNewsAll4Ukraine() {
        return "all4Ukraine";
    }

    @GetMapping("/vidnovlyumo-robotu")
    public String getNewsVidnovlyumoRobotu() {
        return "vidnovlyumo-robotu";
    }

    @GetMapping("/razom-do-peremogi")
    public String getNewsRazomDoPeremogi() {
        return "razom-do-peremogi";
    }

    @GetMapping("/about")
    public String getAboutUs() {
        return "about";
    }

    @GetMapping("/contacts")
    public String getContacts() { return "contacts"; }

    @GetMapping("/guarantees")
    public String getGuarantees() {
        return "guarantees";
    }

    @GetMapping("/payment-and-delivery")
    public String getPaymentAndDelivery() {
        return "payment-and-delivery";
    }

}

