package com.univtime.informatique.controllers;

import com.univtime.informatique.dto.composanteDto.ComposanteDto;
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

    public GestionComposanteController(ComposanteService composanteService, ModuleService moduleService) {
        this.composanteService = composanteService;
        this.moduleService = moduleService;
    }
    /**
     * URL : /gestionnaire-edt/composantes
     */
    @GetMapping
    public String listAllComposantes(Model model) {
        List<ComposanteDto> composantes = composanteService.findAllComposantes();
        model.addAttribute("composantes", composantes);
        return "gestionnaire_edt/gestion_composantes";
    }

    @GetMapping("/{id}")
    public String getComposanteById(@PathVariable Integer id, Model model) {
        ComposanteDto composante = composanteService.findComposanteDtoById(id);
        model.addAttribute("composante", composante);
        return "composanteDetail";
    }

    @GetMapping("/new")
    public String formCreation(Model model) {
        model.addAttribute("composante", new ComposanteDto());
        model.addAttribute("modules", moduleService.findAllModules());
        return "newComposante";
    }

    @PostMapping("/new")
    public String createComposante(@ModelAttribute ComposanteDto composanteDto) {
        ComposanteDto saved = composanteService.createComposante(composanteDto);
        return "redirect:/composantes/" + saved.getIdComposante();
    }

    @GetMapping("/{id}/edit")
    public String formDeModification(@PathVariable Integer id, Model model) {
        ComposanteDto composante = composanteService.findComposanteDtoById(id);
        model.addAttribute("composante", composante);
        model.addAttribute("modules", moduleService.findAllModules());
        return "editComposante";
    }

    @PostMapping("/{id}/edit")
    public String updateComposante(@PathVariable Integer id, @ModelAttribute ComposanteDto composanteDto) {

        composanteDto.setIdComposante(id);
        composanteService.updateComposante(composanteDto);

        return "redirect:/composantes/" + id;
    }

    @GetMapping("/{id}/delete")
    public String deleteComposante(@PathVariable Integer id) {
        composanteService.deleteComposanteById(id);
        return "redirect:/composantes";
    }
}
