package com.univtime.informatique.controllers;

import com.univtime.informatique.dto.promoDto.PromoDto;
import com.univtime.informatique.services.PromoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class IndexController {
    private final PromoService promoService;

    public IndexController(PromoService promoService) {
        this.promoService = promoService;
    }

    @GetMapping("/")
    public String showIndex(Model model) {
        List<PromoDto> promos = promoService.findAllPromos();
        //List<PromoDto> promos = new ArrayList<>();
        model.addAttribute(
                "promotions",
                promos.stream()
                .sorted(Comparator.comparing(PromoDto::getIdPromo).reversed()).limit(3)
                .collect(Collectors.toList())
        );
        return "index";
    }
}
