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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/gestionnaire-edt/recapitulatif")
public class RecapitulatifController {

    private final PromoService promoService;

    public RecapitulatifController(PromoService promoService) {
        this.promoService = promoService;
    }

    @GetMapping("/{idPromo}/{numSemestre}")
    public String afficherRecapitulatif(@PathVariable Integer idPromo,
                                        @PathVariable Integer numSemestre,
                                        Model model) {
        PromoDto promo = promoService.findPromoDtoById(idPromo);
        if (promo == null) return "redirect:/";

        //Recap recap = recapService.getRecapForPromo(idPromo, numSemestre);

        model.addAttribute("promo", promo);
        model.addAttribute("numSemestre", numSemestre);
        //model.addAttribute("recap", recap);

        return "gestionnaire_edt/recapitulatif";
    }
}