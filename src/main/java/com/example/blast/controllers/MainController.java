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

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final SofaService sofaService;
    private final NumberService numberService;

    @GetMapping("/blast_ua")
    public String getMainPage(Principal principal,
                              Model model) {
        model.addAttribute("user", principal);
        return "main-page";
    }

    @GetMapping("/sofas")
    public String getAllSofas(Principal principal, Model model) {
        List<Sofa> sofas = sofaService.getAllSofas();
        sofas.forEach(sofa -> sofa.getPhotos()
                .forEach(photo -> photo.setBase64Photo(sofaService.encodedString(photo.getBytes()))));
        model.addAttribute("sofas", sofas);
        model.addAttribute("user", principal);
        return "sofas";
    }

    @GetMapping("/sofas/{type}")
    public String getAllSofasByType(@PathVariable(name = "type") String type, Model model, Principal principal) {
        List<Sofa> sofas = sofaService.getAllByType(type);
        sofas.forEach(sofa -> sofa.getPhotos()
                .forEach(photo -> photo.setBase64Photo(sofaService.encodedString(photo.getBytes()))));
        model.addAttribute("sofas", sofas);
        model.addAttribute("user", principal);
        return "sofas";
    }

    @GetMapping("/sofas/by/price")
    public List<Sofa> getAllSofasByPrice(@RequestParam(name = "price") Long price, Principal principal, Model model) {
        model.addAttribute("user", principal);
        return sofaService.getAllByPrice(price);
    }

    @PostMapping("/my-sofa")
    public String addSofa(@RequestParam("name") String name,
                          @RequestParam("type") String type,
                          @RequestParam("price") Long price,
                          @RequestParam("currency") String currency,
                          @RequestParam("description") String description,
                          @RequestParam("video") String video,
                          @RequestParam("file1") MultipartFile file1,
                          @RequestParam("file2") MultipartFile file2,
                          @RequestParam("file3") MultipartFile file3,
                          Principal principal,
                          Model model) throws IOException {
        Sofa sofa = new Sofa(name, type, price, currency, description, video);
        sofaService.saveNewSofa(sofa, file1, file2, file3);
        model.addAttribute("user", principal);
        return "main-page";
    }

    @GetMapping("/my-sofa")
    public String addSofa(Model model, Principal principal) {
        model.addAttribute("user", new Sofa());
        model.addAttribute("user", principal);
        return "my-sofa";
    }

    @GetMapping("/sofa/{id}")
    public String sofaInfo(@PathVariable Long id, Model model, Principal principal) {
        Sofa sofa = sofaService.getById(id);
        model.addAttribute("sofa", sofa);
        model.addAttribute("photos", sofa.getPhotos());
        model.addAttribute("user", principal);
        return "sofa-info";
    }

    @RequestMapping(value = "/saveNumber", method = RequestMethod.POST)
    public String saveNumber(@ModelAttribute Number number, Principal principal, Model model) {
        numberService.saveNewPhoneNumber(number);
        model.addAttribute("user", principal);
        return "main-page";
    }

    @GetMapping("/christmas")
    public String getNewsChristmas(Model model, Principal principal) {
        model.addAttribute("user", principal);
        return "christmas";
    }

    @GetMapping("/all4Ukraine")
    public String getNewsAll4Ukraine(Model model, Principal principal) {
        model.addAttribute("user", principal);
        return "all4Ukraine";
    }

    @GetMapping("/vidnovlyumo-robotu")
    public String getNewsVidnovlyumoRobotu(Model model, Principal principal) {
        model.addAttribute("user", principal);
        return "vidnovlyumo-robotu";
    }

    @GetMapping("/razom-do-peremogi")
    public String getNewsRazomDoPeremogi(Model model, Principal principal) {
        model.addAttribute("user", principal);
        return "razom-do-peremogi";
    }

    @GetMapping("/about")
    public String getAboutUs(Model model, Principal principal) {
        model.addAttribute("user", principal);
        return "about";
    }

    @GetMapping("/contacts")
    public String getContacts(Model model, Principal principal) {
        model.addAttribute("user", principal);
        return "contacts";
    }

    @GetMapping("/guarantees")
    public String getGuarantees(Model model, Principal principal) {
        model.addAttribute("user", principal);
        return "guarantees";
    }

    @GetMapping("/payment-and-delivery")
    public String getPaymentAndDelivery(Model model, Principal principal) {
        model.addAttribute("user", principal);
        return "payment-and-delivery";
    }

}

