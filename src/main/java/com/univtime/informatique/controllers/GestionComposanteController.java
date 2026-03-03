package com.univtime.informatique.controllers;

import com.univtime.informatique.dto.composanteDto.ComposanteDto;
import com.univtime.informatique.dto.moduleDto.ModuleDto;
import com.univtime.informatique.services.ComposanteService;
import com.univtime.informatique.services.ModuleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/gestionnaire-edt/composantes")
public class GestionComposanteController {

    private final ComposanteService composanteService;
    private final ModuleService moduleService;

    public GestionComposanteController(ComposanteService composanteService,
                                       ModuleService moduleService) {
        this.composanteService = composanteService;
        this.moduleService = moduleService;
    }

    @GetMapping
    public String listAllComposantes(Model model) {
        model.addAttribute("composantes",
                composanteService.findAllComposantes());

        ComposanteDto dto = new ComposanteDto();
        //dto.setModuleDto(new ModuleDto());
        model.addAttribute("composante", dto);

        model.addAttribute("modules",
                moduleService.findAllModules());

        return "gestionnaire_edt/gestion_composantes";
    }

    @GetMapping("/promo/{idpromo}")
    public String listByPromo(@PathVariable Integer idpromo,
                              Model model) {

        model.addAttribute("composantes",
                composanteService.findComposantesDtoByIdPromo(idpromo));

        ComposanteDto dto = new ComposanteDto();
        //dto.setModuleDto(new ModuleDto());
        model.addAttribute("composante", dto);

       /** model.addAttribute("modules",
                moduleService.findAllModules());
        */
        model.addAttribute("idpromo", idpromo);

        return "gestionnaire_edt/gestion_composantes";
    }

    @PostMapping("/new")
    public String createComposante(@ModelAttribute ComposanteDto composanteDto) {

        ComposanteDto saved =
                composanteService.createComposante(composanteDto);

        return "redirect:/gestionnaire-edt/composantes";
    }

    @PostMapping("/promo/{idpromo}/new")
    public String createComposantePromo(@PathVariable Integer idpromo,
                                        @ModelAttribute ComposanteDto composanteDto) {

        composanteService.createComposante(composanteDto);

        return "redirect:/gestionnaire-edt/composantes/promo/" + idpromo;
    }

    @PostMapping("/{idpromo}/{id}/delete")
    public String delete(@PathVariable Integer idpromo,@PathVariable Integer id) {
        composanteService.deleteComposanteById(id);
        return "redirect:/gestionnaire-edt/composantes"+idpromo;
    }
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id) {
        composanteService.deleteComposanteById(id);
        return "redirect:/gestionnaire-edt/composantes";
    }
}
