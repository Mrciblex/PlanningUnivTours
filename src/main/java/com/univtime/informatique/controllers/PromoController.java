package com.univtime.informatique.controllers;

import com.univtime.informatique.dto.promoDto.PromoDto;
import com.univtime.informatique.services.PromoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/promos")
public class PromoController {

    private final PromoService promoService;

    public PromoController(PromoService promoService) {
        this.promoService = promoService;
    }
    /**
    @GetMapping
    public String listAllPromos(Model model) {
        List<PromoDto> promos = promoService.findAllPromos();
        model.addAttribute("promos", promos);
        return "gestion_promos";
    }*/

    @GetMapping("/{id}")
    public String getPromoById(@PathVariable Integer id, Model model) {
        PromoDto promo = promoService.findPromoDtoById(id);
        model.addAttribute("promo", promo);
        return "promoDetail";
    }

    @GetMapping("/new")
    public String formCreation(Model model) {
        model.addAttribute("promo", new PromoDto());
        return "newPromo";
    }

    @PostMapping("/new")
    public String createPromo(@ModelAttribute PromoDto promoDto) {
        PromoDto saved = promoService.createPromo(promoDto);
        return "redirect:/promos/" + saved.getIdPromo();
    }

    @GetMapping("/{id}/edit")
    public String formDeModification(@PathVariable Integer id, Model model) {
        PromoDto promo = promoService.findPromoDtoById(id);
        model.addAttribute("promo", promo);
        return "editPromo";
    }


    @PostMapping("/{id}/edit")
    public String updatePromo(@PathVariable Integer id, @ModelAttribute PromoDto promoDto) {

        promoDto.setIdPromo(id);
        promoService.updatePromo(promoDto);

        return "redirect:/promos/" + id;
    }

    @GetMapping("/{id}/delete")
    public String deletePromo(@PathVariable Integer id) {
        promoService.deletePromoById(id);
        return "redirect:/promos";
    }
}
