package com.univtime.informatique.controllers;

import com.univtime.informatique.dto.professeurDto.ProfesseurDto;
import com.univtime.informatique.services.ProfesseurService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("gestionnaire-edt/professeurs")
public class GestionProfesseurController {
    private final ProfesseurService professeurService;

    public GestionProfesseurController(ProfesseurService professeurService) {
        this.professeurService = professeurService;
    }

    @GetMapping
    public String listAllProfesseurs(Model model) {
        List<ProfesseurDto> professeurs = professeurService.findAllProfesseurs();
        model.addAttribute("professeurs", professeurs);
        return "gestionnaire_edt/gestion_professeurs";
    }

    @GetMapping("/{id}")
    public String getProfesseurById(@PathVariable Integer id, Model model) {
        ProfesseurDto professeur = professeurService.findProfesseurDtoById(id);
        model.addAttribute("professeur", professeur);
        return "gestionnaire_edt/editProfesseurs";
    }

    @GetMapping("/new")
    public String formDeCreation(Model model) {
        model.addAttribute("professeur", new ProfesseurDto());
        return "gestionnaire_edt/newProfesseur";// pour la pop up de creation du prof
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
        return "gestionnaire_edt/editProfesseur";// et la il faut mettre la pop up en html et faire le lien en js comme j'ai montré au debut
    }

    @PostMapping("/{id}/edit")
    public String updateProfesseur(@PathVariable Integer id,
                                   @ModelAttribute ProfesseurDto professeurDto) {

        professeurDto.setIdProf(id);
        professeurService.updateProfesseur(professeurDto);

        return "redirect:/gestion_professeurs/" + id;
    }

    @GetMapping("/{id}/delete")
    public String deleteProfesseur(@PathVariable Integer id) {
        professeurService.deleteProfesseurById(id);
        return "redirect:/gestion_professeurs";
    }
}
