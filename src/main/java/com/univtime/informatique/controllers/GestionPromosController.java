package com.univtime.informatique.controllers;
import com.univtime.informatique.dto.promoDto.PromoDto;
import com.univtime.informatique.services.PromoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/gestion-promo") // Préfixe clair pour la gestion des promos
public class GestionPromosController {
    public final PromoService promoService;
    public GestionPromosController(PromoService promoService) {
        this.promoService = promoService;
    }
    @GetMapping("/gestion-promos")
    public String gestionPromos() {
        return "gestionnaire_promos";
    }

    //pour avoir toute les promos
    public String listAllPromos(Model model) {
        List<PromoDto> promos = promoService.findAllPromos();
        if (promos == null) {
            promos = new ArrayList<>();
        }
        model.addAttribute("promos", promos);
        return "gestion_promos";
    }
    @PostMapping("/new")
    public String createPromo(@ModelAttribute PromoDto promoDto) {
        promoService.createPromo(promoDto);
        // Redirige vers la méthode qui liste toutes les promos
        return "redirect:/gestion_promos";
    }

    @GetMapping("/{id}/delete")
    public String deletePromo(@PathVariable Integer id) {
        promoService.deletePromoById(id);
        return "redirect:/gestion_promos";
    }

}

