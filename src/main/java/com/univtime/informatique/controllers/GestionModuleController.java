package com.univtime.informatique.controllers;

import com.univtime.informatique.dto.moduleDto.ModuleDto;
import com.univtime.informatique.services.ModuleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/gestionnaire-edt/module")
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
}
