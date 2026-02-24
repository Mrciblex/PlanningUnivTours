package com.univtime.informatique.controllers;

import com.univtime.informatique.dto.professeurDto.ProfesseurDto;
import com.univtime.informatique.services.ProfesseurService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/professeurs")
public class ProfesseurController {
    // les pages html ne sont pas existante donc je pense que cela crée des erreurs minime a voir comment faire une pop up et le relier au contrroler le style de pop up "Controller  →  envoie la page HTML
    //HTML        →  contient un modal (popup)
    //JS          →  ouvre / ferme le popup
    //Formulaire  →  envoie vers Controller"

    private final ProfesseurService professeurService;

    public ProfesseurController(ProfesseurService professeurService) {
        this.professeurService = professeurService;
    }

    @GetMapping
    public String listAllProfesseurs(Model model) {
        List<ProfesseurDto> professeurs = professeurService.findAllProfesseurs();
        model.addAttribute("professeurs", professeurs);
        return "gestionnaire_edt_promo/gestion_professeurs";
    }

    @GetMapping("/{id}")
    public String getProfesseurById(@PathVariable Integer id, Model model) {
        ProfesseurDto professeur = professeurService.findProfesseurDtoById(id);
        model.addAttribute("professeur", professeur);
        return "gestionnaire_edt_promo/professeurDetail";// change le nom si cela ne te plait pas
    }

    @GetMapping("/new")
    public String formDeCreation(Model model) {
        model.addAttribute("professeur", new ProfesseurDto());
        return "gestionnaire_edt_promo/newProfesseur";// pour la pop up de cration du prof
    }

    @PostMapping("/new")
    public String createProfesseur(@ModelAttribute ProfesseurDto professeurDto) {
        ProfesseurDto saved = professeurService.createProfesseur(professeurDto);
        return "redirect:/professeurs/" + saved.getIdProf();
    }

    @GetMapping("/{id}/edit")
    public String formDeModification(@PathVariable Integer id, Model model) {
        ProfesseurDto professeur = professeurService.findProfesseurDtoById(id);
        model.addAttribute("professeur", professeur);
        return "gestionnaire_edt_promo/editProfesseur";// et la il faut mettre la pop up en html et faire le lien en js comme j'ai montré au debut
    }

    @PostMapping("/{id}/edit")
    public String updateProfesseur(@PathVariable Integer id,
                                   @ModelAttribute ProfesseurDto professeurDto) {

        professeurDto.setIdProf(id);
        professeurService.updateProfesseur(professeurDto);

        return "redirect:/professeurs/" + id;
    }

    @GetMapping("/{id}/delete")
    public String deleteProfesseur(@PathVariable Integer id) {
        professeurService.deleteProfesseurById(id);
        return "redirect:/professeurs";
    }
}
