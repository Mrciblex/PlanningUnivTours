package com.univtime.informatique.controllers;

import com.univtime.informatique.dto.moduleDto.ModuleDto;
import com.univtime.informatique.services.ModuleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/gestionnaire-edt/modules")
public class GestionModuleController {
    private ModuleService moduleService ;

    public GestionModuleController(ModuleService moduleService){
        this.moduleService = moduleService;
    }
    @GetMapping
    public String listAllModules(Model model) {
        List<ModuleDto> modules = moduleService.findAllModules();
        model.addAttribute("modules", modules);
        return "gestionnaire_edt/gestionnaire_modules";
    }
    @GetMapping("/promo/{idPromo}")
    public String listModulesByPromo(@PathVariable Integer idPromo, Model model) {

        List<ModuleDto> modules;
        modules = moduleService.findModuleDtoByIdPromo(idPromo);

        model.addAttribute("modules", modules);
        model.addAttribute("idPromo", idPromo);

        return "gestionnaire_edt/gestion_modules";
    }

    /**
     * URL : /gestionnaire-edt/modules/{id}
     */
    @GetMapping("/{id}")
    public String getModuleById(@PathVariable Integer id, Model model) {
        ModuleDto module = moduleService.findModuleDtoById(id);
        model.addAttribute("module", module);
        return "gestionnaire_edt/moduleDetail";
    }

    @PostMapping("/new")
    public String createModule(@ModelAttribute ModuleDto moduleDto) {
        ModuleDto saved = moduleService.createModule(moduleDto);
        return "redirect:/gestionnaire-edt/modules/" + saved.getIdModule();
    }

    @PostMapping("/{id}/edit")
    public String updateModule(@PathVariable Integer id, @ModelAttribute ModuleDto moduleDto) {

        moduleDto.setIdModule(id);
        moduleService.updateModule(moduleDto);

        return "redirect:/gestionnaire-edt/modules/" + id;
    }

    @GetMapping("/{id}/delete")
    public String deleteModule(@PathVariable Integer id) {
        moduleService.deleteModuleById(id);
        return "redirect:/gestionnaire-edt/modules";
    }
}
