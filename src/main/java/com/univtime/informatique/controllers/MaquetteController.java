package com.univtime.informatique.controllers;

import com.univtime.informatique.dto.composanteDto.ComposanteDto;
import com.univtime.informatique.dto.groupeDto.GroupeDto;
import com.univtime.informatique.dto.moduleDto.ModuleDto;
import com.univtime.informatique.dto.professeurDto.ProfesseurDto;
import com.univtime.informatique.dto.sousGroupeDto.SousGroupeDto;
import com.univtime.informatique.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/gestionnaire-edt/maquette")
public class MaquetteController {
    /**
     * URL : /gestionnaire-edt/maquette
     */
    private final ModuleService moduleService;
    private final ComposanteService composanteService;
    private final ProfesseurService professeurService;
    private final GroupeService groupeService;
    private final SousGroupeService sousGroupeService;

    public MaquetteController(ModuleService moduleService,
                              ComposanteService composanteService,
                              ProfesseurService professeurService,
                              GroupeService groupeService,
                              SousGroupeService sousGroupeService) {
        this.moduleService = moduleService;
        this.composanteService = composanteService;
        this.professeurService = professeurService;
        this.groupeService = groupeService;
        this.sousGroupeService = sousGroupeService;
    }
    @GetMapping
    public String afficherMaquettes() {
        return "gestionnaire_edt/maquette";
    }
    @GetMapping("/{idPromo}")
    public String afficherMaquette(@PathVariable Integer idPromo, Model model) {

        model.addAttribute("modules", moduleService.findModuleDtoByIdPromo(idPromo));
        model.addAttribute("composantes", composanteService.findComposantesDtoByIdPromo(idPromo));
        model.addAttribute("professeurs", professeurService.findProfesseurDtoByIdPromo(idPromo));
        model.addAttribute("groupes", groupeService.findGroupeDtoByIdPromo(idPromo));
        model.addAttribute("sousGroupes", sousGroupeService.findSousGroupesDtoByIdPromo(idPromo));

        model.addAttribute("newModule", new ModuleDto());
        model.addAttribute("newComposante", new ComposanteDto());
        model.addAttribute("newProfesseur", new ProfesseurDto());
        model.addAttribute("newGroupe", new GroupeDto());
        model.addAttribute("newSousGroupe", new SousGroupeDto());

        model.addAttribute("idPromo", idPromo);

        return "gestionnaire_edt/maquette";
    }

    // ----------- CREATE -----------

    @PostMapping("/{idPromo}/modules")
    public String createModule(@PathVariable Integer idPromo,
                               @ModelAttribute ModuleDto moduleDto) {
        moduleService.createModule(moduleDto);
        return "redirect:/gestionnaire-edt/maquette/" + idPromo;
    }

    @PostMapping("/{idPromo}/composantes")
    public String createComposante(@PathVariable Integer idPromo,
                                   @ModelAttribute ComposanteDto composanteDto) {
        composanteService.createComposante(composanteDto);
        return "redirect:/gestionnaire-edt/maquette/" + idPromo;
    }

    @PostMapping("/{idPromo}/professeurs")
    public String createProfesseur(@PathVariable Integer idPromo,
                                   @ModelAttribute ProfesseurDto professeurDto) {
        professeurService.createProfesseur(professeurDto);
        return "redirect:/gestionnaire-edt/maquette/" + idPromo;
    }

    @PostMapping("/{idPromo}/groupes")
    public String createGroupe(@PathVariable Integer idPromo,
                               @ModelAttribute GroupeDto groupeDto) {
        groupeService.createGroupe(groupeDto);
        return "redirect:/gestionnaire-edt/maquette/" + idPromo;
    }

    @PostMapping("/{idPromo}/sous-groupes")
    public String createSousGroupe(@PathVariable Integer idPromo,
                                   @ModelAttribute SousGroupeDto sousGroupeDto) {
        sousGroupeService.createSousGroupe(sousGroupeDto);
        return "redirect:/gestionnaire-edt/maquette/" + idPromo;
    }


}
