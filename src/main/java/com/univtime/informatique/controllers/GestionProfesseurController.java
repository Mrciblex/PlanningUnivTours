package com.univtime.informatique.controllers;

import com.univtime.informatique.dto.professeurDto.ProfesseurDto;
import com.univtime.informatique.services.ProfesseurService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/gestionnaire-edt/professeurs")
public class GestionProfesseurController {

    private final ProfesseurService professeurService;

    public GestionProfesseurController(ProfesseurService professeurService) {
        this.professeurService = professeurService;
    }


    // GET : liste les professeurs d'une promo
    @GetMapping("/promo/{idPromo}")
    public String listProfesseursByPromo(@PathVariable Integer idPromo, Model model) {
        List<ProfesseurDto> professeurs = professeurService.findProfesseurDtoByIdPromo(idPromo);
        model.addAttribute("professeurs", professeurs);
        model.addAttribute("idPromo", idPromo);
        return "gestionnaire_edt/gestion_professeurs";
    }


    //POST : créer un prof lié à une promo
    @PostMapping("/{idPromo}/new")
    public String createProfesseurByPromo(@PathVariable Integer idPromo,
                                          @ModelAttribute ProfesseurDto professeurDto) {
        professeurService.createProfesseur(professeurDto);
        return "redirect:/gestionnaire-edt/professeurs/promo/" + idPromo;
    }

    // POST : modifier un prof lié à une promo
    @PostMapping("/{idPromo}/{id}/edit")
    public String updateProfesseurByPromo(@PathVariable Integer idPromo,
                                          @PathVariable Integer id,
                                          @ModelAttribute ProfesseurDto professeurDto) {
        professeurDto.setIdProf(id);
        professeurService.updateProfesseur(professeurDto);
        return "redirect:/gestionnaire-edt/professeurs/promo/" + idPromo;
    }

    //  POST : supprimer un prof lié à une promo
    @PostMapping("/{idPromo}/{id}/delete")
    public String deleteProfesseurByPromo(@PathVariable Integer idPromo,
                                          @PathVariable Integer id) {
        professeurService.deleteProfesseurById(id);
        return "redirect:/gestionnaire-edt/professeurs/promo/" + idPromo;
    }
}
