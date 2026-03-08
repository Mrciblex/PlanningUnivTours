/*
 * Copyright (c) 2026 Ademi Musa. Tous droits réservés.
 * Projet : univTime - Logiciel de gestion d'emplois du temps.
 *
 * Ce code source et l'algorithme associé sont la propriété exclusive de l'auteur.
 * Toute reproduction, modification ou distribution non autorisée, par quelque moyen que ce soit, est strictement interdite.
 *
 * Ce fichier fait partie du projet univTime, concédé sous licence d'usage
 * restreinte à l'Université de Tours, 37000, en France.
 */

package com.univtime.informatique.controllers;

import com.univtime.informatique.dto.promoDto.PromoDto;
import com.univtime.informatique.services.PromoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/gestion-promos")
public class GestionPromosController {
    private final PromoService promoService;

    public GestionPromosController(PromoService promoService) {
        this.promoService = promoService;
    }

    @GetMapping
    public String listAllPromos(Model model) {
        List<PromoDto> promos = promoService.findAllPromos();
        model.addAttribute("promos", promos != null ? promos : new ArrayList<>());
        return "gestionnaire_promos";
    }

    @PostMapping("/new")
    @ResponseBody
    public ResponseEntity<?> createPromo(@RequestBody PromoDto promoDto) {
        try {
            promoService.createPromo(promoDto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/{id}/edit")
    @ResponseBody
    public ResponseEntity<?> updatePromo(@PathVariable Integer id, @RequestBody PromoDto promoDto) {
        try {
            promoDto.setIdPromo(id);
            promoService.updatePromo(promoDto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/{id}/delete")
    @ResponseBody
    public ResponseEntity<?> deletePromo(@PathVariable Integer id) {
        try {
            promoService.deletePromoById(id);
            return ResponseEntity.ok().body(Map.of("message", "Succès"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}