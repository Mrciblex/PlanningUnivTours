package com.univtime.informatique.controllers;

import com.univtime.informatique.dto.composanteDto.CMComposanteDto;
import com.univtime.informatique.dto.composanteDto.ComposanteDto;
import com.univtime.informatique.dto.composanteDto.TDComposanteDto;
import com.univtime.informatique.dto.composanteDto.TPComposanteDto;
import com.univtime.informatique.dto.groupeDto.GroupeDto;
import com.univtime.informatique.dto.moduleDto.ModuleDto;
import com.univtime.informatique.dto.professeurDto.ProfesseurDto;
import com.univtime.informatique.dto.sousGroupeDto.SousGroupeDto;
import com.univtime.informatique.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

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
    private final CMService cmService;
    private final TDService tdService;
    private final TPService tpService;
    private final PromoService promoService;

    public MaquetteController(
            ModuleService moduleService,
            ComposanteService composanteService,
            ProfesseurService professeurService,
            GroupeService groupeService,
            SousGroupeService sousGroupeService,
            CMService cmService,
            TDService tdService,
            TPService tpService,
            PromoService promoService) {
        this.moduleService = moduleService;
        this.composanteService = composanteService;
        this.professeurService = professeurService;
        this.groupeService = groupeService;
        this.sousGroupeService = sousGroupeService;
        this.cmService = cmService;
        this.tdService = tdService;
        this.tpService = tpService;
        this.promoService = promoService;
    }

    @GetMapping("/{idPromo}/{numSemestre}")
    public String afficherMaquette(@PathVariable Integer idPromo,
                                   @PathVariable Integer numSemestre,
                                   Model model) {

        List<ComposanteDto> composantes = composanteService.findComposantesDtoByIdPromo(idPromo);

        for (ComposanteDto c : composantes) {

            // ===== CM =====
            c.setCmNbParSem(
                    nbParSem(
                            c.getCmDto(),
                            CMComposanteDto::getNumSemaine
                    )
            );

            // ===== TD =====
            c.setTdNbParSem(
                    nbParSem(
                            c.getTdDto(),
                            TDComposanteDto::getNumSemaine
                    )
            );

            // ===== TP =====
            c.setTpNbParSem(
                    nbParSem(
                            c.getTpDto(),
                            TPComposanteDto::getNumSemaine
                    )
            );
        }

        model.addAttribute("modules", moduleService.findModuleDtoByIdPromo(idPromo));
        model.addAttribute("composantes", composantes);
        model.addAttribute("professeurs", professeurService.findProfesseurDtoByIdPromo(idPromo));
        model.addAttribute("groupes", groupeService.findGroupeDtoByIdPromo(idPromo));
        model.addAttribute("sousGroupes", sousGroupeService.findSousGroupesDtoByIdPromo(idPromo));

        model.addAttribute("cms", cmService.findCMDtoByIdPromo(idPromo));
        model.addAttribute("tds", tdService.findTDDtoByIdPromo(idPromo));
        model.addAttribute("tps", tpService.findTPDtoByIdPromo(idPromo));

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

    // ----------- DELETE -----------

    @PostMapping("/{idPromo}/modules/{id}/delete")
    public String deleteModule(@PathVariable Integer idPromo,
                               @PathVariable Integer id) {
        moduleService.deleteModuleById(id);
        return "redirect:/gestionnaire-edt/maquette/" + idPromo;
    }

    @PostMapping("/{idPromo}/composantes/{id}/delete")
    public String deleteComposante(@PathVariable Integer idPromo,
                                   @PathVariable Integer id) {
        composanteService.deleteComposanteById(id);
        return "redirect:/gestionnaire-edt/maquette/" + idPromo;
    }

    @PostMapping("/{idPromo}/groupes/{id}/delete")
    public String deleteGroupe(@PathVariable Integer idPromo,
                               @PathVariable Integer id) {
        groupeService.deleteGroupeById(id);
        return "redirect:/gestionnaire-edt/maquette/" + idPromo;
    }

    @PostMapping("/{idPromo}/sous-groupes/{id}/delete")
    public String deleteSousGroupe(@PathVariable Integer idPromo,
                                   @PathVariable Integer id) {
        sousGroupeService.deleteSousGroupeById(id);
        return "redirect:/gestionnaire-edt/maquette/" + idPromo;
    }

    private <T> Map<Integer, Integer> nbParSem(
            Set<T> items,
            Function<T, Integer> semExtracteur) {

        Map<Integer, Integer> map = new HashMap<>();

        // Pour 12 semaines
        for (int i = 1; i <= 12; i++) {
            map.put(i, 0);
        }

        if (items == null) return map;

        for (T item : items) {
            Integer sem = semExtracteur.apply(item);
            if (sem != null) {
                map.put(sem, map.getOrDefault(sem, 0) + 1);
            }
        }

        return map;
    }
}
