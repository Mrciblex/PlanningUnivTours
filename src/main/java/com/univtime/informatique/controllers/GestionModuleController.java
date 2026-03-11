/*
 * Copyright (c) 2026 Ademi Musa. Tous droits réservés.
 * Projet : univTime - Logiciel de gestion d'emplois du temps.
 *
 * Ce code source et l'algorithme associé sont la propriété exclusive de l'auteur.
 * Toute reproduction, modification ou distribution non autorisée, par quelque moyen que ce soit, est strictement interdite.
 *
 * Ce fichier fait partie du projet univTime, concédé sous licence d'usage
 * restreinte à l'Université de Tours, 37000, en France.
 */

package com.univtime.informatique.controllers;

import com.univtime.informatique.dto.idsDto.PromoEstComposeeIdDto;
import com.univtime.informatique.dto.moduleDto.ModuleDto;
import com.univtime.informatique.dto.composanteDto.ComposanteDto;
import com.univtime.informatique.dto.composanteDto.ModuleComposanteDto;
import com.univtime.informatique.dto.promoDto.PromoDto;
import com.univtime.informatique.dto.promoEstComposeeDto.ModulePromoEstComposeeDto;
import com.univtime.informatique.dto.promoEstComposeeDto.PromoEstComposeeDto;
import com.univtime.informatique.dto.promoEstComposeeDto.PromoPromoEstComposeeDto;
import com.univtime.informatique.entities.ids.PromoEstComposeeId;
import com.univtime.informatique.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/gestionnaire-edt/modules")
public class GestionModuleController {

    private final ModuleService moduleService;
    private final ComposanteService composanteService;
    private final PromoEstComposeeService promoEstComposeeService;
    private final BesoinSalleService besoinSalleService;
    private final PromoService promoService;

    public GestionModuleController(ModuleService moduleService, ComposanteService composanteService, PromoEstComposeeService promoEstComposeeService,
                                   BesoinSalleService besoinSalleService,
                                   PromoService promoService){
        this.moduleService = moduleService;
        this.composanteService = composanteService;
        this.promoEstComposeeService = promoEstComposeeService;
        this.besoinSalleService = besoinSalleService;
        this.promoService = promoService;
    }

    @GetMapping("/promo/{idPromo}")
    public String listModulesByPromo(@PathVariable Integer idPromo, Model model) {
        List<ModuleDto> allModules = moduleService.findAllModules();
        List<ModuleDto> promoModules = moduleService.findModuleDtoByIdPromo(idPromo);

        List<Integer> linkedModuleIds = promoModules.stream()
                .map(ModuleDto::getIdModule)
                .toList();

        PromoDto promo = promoService.findPromoDtoById(idPromo);

        model.addAttribute("modules", allModules);
        model.addAttribute("linkedModuleIds", linkedModuleIds);
        model.addAttribute("promo", promo);

        return "gestionnaire_edt/gestion_modules";
    }

    @PostMapping("/new")
    public String createModule(@ModelAttribute ModuleDto moduleDto, @RequestParam Integer idPromo) {
        ModuleDto created = moduleService.createModule(moduleDto);

        PromoPromoEstComposeeDto promo = new PromoPromoEstComposeeDto();
        promo.setIdPromo(idPromo);

        ModulePromoEstComposeeDto module = new ModulePromoEstComposeeDto();
        module.setIdModule(created.getIdModule());

        promoEstComposeeService.createPromoEstComposee(new PromoEstComposeeDto(promo, module));

        return "redirect:/gestionnaire-edt/modules/promo/" + idPromo;
    }

    @PostMapping("/link")
    public String linkModuleToPromo(@RequestParam Integer idModule, @RequestParam Integer idPromo) {
        PromoPromoEstComposeeDto promo = new PromoPromoEstComposeeDto();
        promo.setIdPromo(idPromo);

        ModulePromoEstComposeeDto module = new ModulePromoEstComposeeDto();
        module.setIdModule(idModule);

        promoEstComposeeService.createPromoEstComposee(new PromoEstComposeeDto(promo, module));
        return "redirect:/gestionnaire-edt/modules/promo/" + idPromo;
    }

    @DeleteMapping("/unlink")
    public String unlinkModuleFromPromo(@RequestParam Integer idModule, @RequestParam Integer idPromo) {
        promoEstComposeeService.deletePromoEstComposee(idPromo, idModule);
        return "redirect:/gestionnaire-edt/modules/promo/" + idPromo;
    }

    @PutMapping("/edit")
    public String updateModule(@ModelAttribute ModuleDto moduleDto, @RequestParam Integer idPromo) {
        moduleService.updateModule(moduleDto);
        return "redirect:/gestionnaire-edt/modules/promo/" + idPromo;
    }

    @DeleteMapping("/delete")
    public String deleteModule(@RequestParam("idModule") Integer idModule,
                               @RequestParam("idPromo") Integer idPromo,
                               RedirectAttributes redirectAttributes) {
        try{
            composanteService.deleteComposanteByModuleId(idModule);
            promoEstComposeeService.deletePromoEstComposeeByModuleId(idModule);
            moduleService.deleteModuleById(idModule);
            return "redirect:/gestionnaire-edt/modules/promo/" + idPromo;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Impossible de supprimer ce module car il possède des cours planifiés.");
            return "redirect:/gestionnaire-edt/modules/promo/" + idPromo;
        }
    }

    @PostMapping("/composante/new")
    public String createComposante(@ModelAttribute ComposanteDto composanteDto,
                                   @RequestParam Double vhTotal,
                                   @RequestParam Double vhCM,
                                   @RequestParam Double vhTD,
                                   @RequestParam Double vhTP,
                                   @RequestParam Double blCM,
                                   @RequestParam Double blTD,
                                   @RequestParam Double blTP,
                                   @RequestParam Integer idModule,
                                   @RequestParam Integer idPromo) {

        composanteDto.setVolumeHoraireTotal((int) Math.round(vhTotal * 60));
        composanteDto.setVolumeHoraireCM((int) Math.round(vhCM * 60));
        composanteDto.setVolumeHoraireTD((int) Math.round(vhTD * 60));
        composanteDto.setVolumeHoraireTP((int) Math.round(vhTP * 60));
        composanteDto.setBlocHoraireCM((int) Math.round(blCM * 60));
        composanteDto.setBlocHoraireTD((int) Math.round(blTD * 60));
        composanteDto.setBlocHoraireTP((int) Math.round(blTP * 60));

        ModuleComposanteDto module = new ModuleComposanteDto();
        module.setIdModule(idModule);
        composanteDto.setModuleDto(module);
        composanteService.createComposante(composanteDto);
        return "redirect:/gestionnaire-edt/modules/promo/" + idPromo;
    }

    @PutMapping("/composante/edit")
    public String updateComposante(@ModelAttribute ComposanteDto composanteDto,
                                   @RequestParam Double vhTotal,
                                   @RequestParam Double vhCM,
                                   @RequestParam Double vhTD,
                                   @RequestParam Double vhTP,
                                   @RequestParam Double blCM,
                                   @RequestParam Double blTD,
                                   @RequestParam Double blTP,
                                   @RequestParam Integer idModule,
                                   @RequestParam Integer idPromo) {

        composanteDto.setVolumeHoraireTotal((int) Math.round(vhTotal * 60));
        composanteDto.setVolumeHoraireCM((int) Math.round(vhCM * 60));
        composanteDto.setVolumeHoraireTD((int) Math.round(vhTD * 60));
        composanteDto.setVolumeHoraireTP((int) Math.round(vhTP * 60));
        composanteDto.setBlocHoraireCM((int) Math.round(blCM * 60));
        composanteDto.setBlocHoraireTD((int) Math.round(blTD * 60));
        composanteDto.setBlocHoraireTP((int) Math.round(blTP * 60));

        ModuleComposanteDto module = new ModuleComposanteDto();
        module.setIdModule(idModule);
        composanteDto.setModuleDto(module);
        composanteService.updateComposante(composanteDto);
        return "redirect:/gestionnaire-edt/modules/promo/" + idPromo;
    }

    @DeleteMapping("/composante/delete")
    public String deleteComposante(@RequestParam("idComposante") Integer idComposante,
                                   @RequestParam("idPromo") Integer idPromo,
                                   RedirectAttributes redirectAttributes) {
        try{
            besoinSalleService.deleteBesoinSalleByComposanteId(idComposante);
            composanteService.deleteComposanteById(idComposante);
            return "redirect:/gestionnaire-edt/modules/promo/" + idPromo;
        }catch(Exception e){
            redirectAttributes.addFlashAttribute("errorMessage", "Impossible de supprimer cette composante.");
            return "redirect:/gestionnaire-edt/modules/promo/" + idPromo;
        }
    }
}
