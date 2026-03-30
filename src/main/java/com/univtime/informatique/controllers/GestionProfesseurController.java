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

/*
 * Copyright (c) 2026 Ademi Musa. Tous droits réservés.
 * Projet : univTime - Logiciel de gestion d'emplois du temps.
 */

package com.univtime.informatique.controllers;

import com.univtime.informatique.dto.jourDto.JourDto;
import com.univtime.informatique.dto.professeurDto.ProfesseurDto;
import com.univtime.informatique.services.JourService;
import com.univtime.informatique.services.ProfesseurService;
import com.univtime.informatique.services.PromoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/gestionnaire-edt/professeurs")
public class GestionProfesseurController {

    private final ProfesseurService professeurService;
    private final PromoService promoService;
    private final JourService jourService;

    public GestionProfesseurController(ProfesseurService professeurService,
                                       PromoService promoService,
                                       JourService jourService) {
        this.professeurService = professeurService;
        this.promoService = promoService;
        this.jourService = jourService;
    }

    @GetMapping("/promo/{idPromo}")
    public String list(Model model, @PathVariable Integer idPromo) {
        List<ProfesseurDto> professeurs = professeurService.findAllProfesseurs();

        List<JourDto> jourDtos = jourService.findAllJours();
        Map<Integer, List<JourDto>> disposParProf = new HashMap<>();

        jourDtos.forEach(jourDto -> {
            Integer idProf = jourDto.getProfesseurDto().getIdProf();
            disposParProf.computeIfAbsent(idProf, _ -> new java.util.ArrayList<>()).add(jourDto);
        });

        model.addAttribute("professeurs", professeurs);
        model.addAttribute("jourParProf", disposParProf);
        model.addAttribute("promo", promoService.findPromoDtoById(idPromo));
        model.addAttribute("idPromo", idPromo); // Ajout explicite pour faciliter les routes Thymeleaf
        return "gestionnaire_edt/gestion_professeurs";
    }

    @PostMapping("/promo/{idPromo}/new")
    public String create(@PathVariable Integer idPromo, @ModelAttribute ProfesseurDto profDto) {
        professeurService.createProfesseur(profDto);
        return "redirect:/gestionnaire-edt/professeurs/promo/" + idPromo;
    }

    @PostMapping("/promo/{idPromo}/edit")
    public String update(@PathVariable Integer idPromo, @ModelAttribute ProfesseurDto profDto) {
        professeurService.updateProfesseur(profDto);
        return "redirect:/gestionnaire-edt/professeurs/promo/" + idPromo;
    }

    @PostMapping("/promo/{idPromo}/delete")
    public String delete(@PathVariable Integer idPromo, @RequestParam Integer idProfesseur, RedirectAttributes ra) {
        try {
            professeurService.deleteProfesseurById(idProfesseur);
        } catch (Exception e) {
            ra.addFlashAttribute("errorMessage", "Impossible de supprimer ce professeur car il est lié à des éléments d'emploi du temps.");
        }
        return "redirect:/gestionnaire-edt/professeurs/promo/" + idPromo;
    }
}
