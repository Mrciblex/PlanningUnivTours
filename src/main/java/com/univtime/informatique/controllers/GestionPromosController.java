package com.univtime.informatique.controllers;
import com.univtime.informatique.dto.groupeDto.GroupeDto;
import com.univtime.informatique.dto.promoDto.PromoDto;
import com.univtime.informatique.services.PromoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/gestion-promos")
public class GestionPromosController {
    public final PromoService promoService;
    public GestionPromosController(PromoService promoService) {
        this.promoService = promoService;
    }

    @GetMapping
    public String listAllPromos(Model model) {
        List<PromoDto> promos = promoService.findAllPromos();
        if (promos == null) {
            promos = new ArrayList<>();
        }
        model.addAttribute("promos", promos);

        // ⚠️ Objet vide pour le formulaire Ajouter
        model.addAttribute("newPromo", new PromoDto());

        return "gestionnaire_promos";
    }
    @PostMapping("/new")
    public String createPromo(@ModelAttribute PromoDto promoDto) {
        promoService.createPromo(promoDto);
        // Redirige vers la méthode qui liste toutes les promos
        return "redirect:/gestion-promos";
    }

    @PostMapping("/{id}/edit")
    public String updatepromo(@PathVariable Integer id, @ModelAttribute PromoDto promoDto) {

        promoDto.setIdPromo(id);
        promoService.updatePromo(new PromoDto());

        return "redirect:/gestion-promos";
    }

    @GetMapping("/{id}/delete")
    public String deletePromo(@PathVariable Integer id) {
        promoService.deletePromoById(id);;
        return "redirect:/gestion-promos";
    }


}

