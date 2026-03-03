package com.univtime.informatique.controllers;

import com.univtime.informatique.dto.promoDto.PromoDto;
import com.univtime.informatique.services.PromoService;
import com.univtime.informatique.services.RecapitulatifService;
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
    private final RecapitulatifService recapService;

    public RecapitulatifController(PromoService promoService, RecapitulatifService recapService) {
        this.promoService = promoService;
        this.recapService = recapService;
    }

    // Records encapsulés pour le modèle de vue
    public record Recap(
            int nbEtudiants, int nbGroupes, int nbSousGroupes,
            int nbMatieres, int nbEnseignants, int nbSemaines,
            List<MatiereStats> matieres, List<ProfStats> profs
    ) {}

    public record MatiereStats(
            String nom, double totalReel, double totalPrevu,
            TypeStats cm, TypeStats td, TypeStats tp
    ) {}

    public record TypeStats(double reel, double prevu) {
        public int getPercent() {
            return prevu > 0 ? (int) Math.round((reel / prevu) * 100) : 0;
        }
    }

    public record ProfStats(
            String nomComplet, double cmH, double cmMax, double tdH, double tdMax,
            double tpH, double tpMax, double totalH, double quotaMax, String status
    ) {
        public boolean isOverloaded() { return totalH > quotaMax; }
    }

    @GetMapping("/{idPromo}/{numSemestre}")
    public String afficherRecapitulatif(@PathVariable("idPromo") Integer idPromo,
                                        @PathVariable("numSemestre") Integer numSemestre,
                                        Model model) {
        PromoDto promo = promoService.findPromoDtoById(idPromo);
        if (promo == null) return "redirect:/";

        Recap recap = recapService.getRecapForPromo(idPromo, numSemestre);

        model.addAttribute("promo", promo);
        model.addAttribute("numSemestre", numSemestre);
        model.addAttribute("recap", recap);

        return "gestionnaire_edt/recapitulatif";
    }
}