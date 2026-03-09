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

import com.univtime.informatique.dto.groupeDto.GroupeDto;
import com.univtime.informatique.dto.groupeDto.PromoGroupeDto;
import com.univtime.informatique.dto.groupeDto.SousGroupeGroupeDto;
import com.univtime.informatique.dto.promoDto.PromoDto;
import com.univtime.informatique.dto.sousGroupeDto.GroupeSousGroupeDto;
import com.univtime.informatique.dto.sousGroupeDto.SousGroupeDto;
import com.univtime.informatique.services.GroupeService;
import com.univtime.informatique.services.PromoService;
import com.univtime.informatique.services.SousGroupeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/gestionnaire-edt/groupes")
public class GestionGroupeController {

    private final GroupeService groupeService;
    private final SousGroupeService sousGroupeService;
    private final PromoService promoService;

    public GestionGroupeController(GroupeService groupeService,
                                   SousGroupeService sousGroupeService,
                                   PromoService promoService) {
        this.groupeService = groupeService;
        this.sousGroupeService = sousGroupeService;
        this.promoService = promoService;
    }

    @GetMapping
    public String listAllGroupes(Model model) {
        List<GroupeDto> groupes = groupeService.findAllGroupe();
        model.addAttribute("groupes", groupes);
        return "gestionnaire_edt/gestion_groupes";
    }

    @GetMapping("/{id}")
    public String getGroupeById(@PathVariable Integer id, Model model) {
        GroupeDto groupe = groupeService.findGroupeDtoById(id);
        model.addAttribute("groupe", groupe);
        return "gestionnaire_edt/groupeDetail";
    }

    @GetMapping("/promo/{idPromo}")
    public String listGroupesByPromo(@PathVariable Integer idPromo, Model model) {
        PromoDto promo = promoService.findPromoDtoById(idPromo);
        List<GroupeDto> groupes = groupeService.findGroupeDtoByIdPromo(idPromo);
        List<SousGroupeGroupeDto> sg = groupes.stream().flatMap(g -> g.getSousGroupeDto().stream()).toList();

        model.addAttribute("groupes", groupes);
        model.addAttribute("promo", promo);
        model.addAttribute("sg", sg);

        return "gestionnaire_edt/gestion_groupes";
    }

    @PostMapping("/new")
    public String createGroupe(@ModelAttribute GroupeDto groupeDto, @RequestParam Integer idPromo) {
        PromoGroupeDto promo = new PromoGroupeDto();
        promo.setIdPromo(idPromo);
        groupeDto.setPromoDto(promo);
        groupeService.createGroupe(groupeDto);
        return "redirect:/gestionnaire-edt/groupes/promo/" + idPromo;
    }

    @PutMapping("/edit")
    public String updateGroupe(@ModelAttribute GroupeDto groupeDto, @RequestParam Integer idPromo) {
        PromoGroupeDto promo = new PromoGroupeDto();
        promo.setIdPromo(idPromo);
        groupeDto.setPromoDto(promo);
        groupeService.updateGroupe(groupeDto);
        return "redirect:/gestionnaire-edt/groupes/promo/" + idPromo;
    }

    @DeleteMapping("/delete")
    public String deleteGroupe(@RequestParam("idGroupe") Integer idGroupe, @RequestParam("idPromo") Integer idPromo) {
        groupeService.deleteGroupeById(idGroupe);
        return "redirect:/gestionnaire-edt/groupes/promo/" + idPromo;
    }

    @PostMapping("/sg/new")
    public String createSousGroupe(@ModelAttribute SousGroupeDto sousGroupeDto, @RequestParam Integer idGroupe, @RequestParam Integer idPromo) {
        GroupeSousGroupeDto groupe = new GroupeSousGroupeDto();
        groupe.setIdGroupe(idGroupe);
        sousGroupeDto.setGroupeDto(groupe);
        sousGroupeService.createSousGroupe(sousGroupeDto);
        return "redirect:/gestionnaire-edt/groupes/promo/" + idPromo;
    }

    @PutMapping("/sg/edit")
    public String updateSousGroupe(@ModelAttribute SousGroupeDto sousGroupeDto, @RequestParam Integer idGroupe, @RequestParam Integer idPromo) {
        GroupeSousGroupeDto groupe = new GroupeSousGroupeDto();
        groupe.setIdGroupe(idGroupe);
        sousGroupeDto.setGroupeDto(groupe);
        sousGroupeService.updateSousGroupe(sousGroupeDto);
        return "redirect:/gestionnaire-edt/groupes/promo/" + idPromo;
    }

    @DeleteMapping("/sg/delete")
    public String deleteSousGroupe(@RequestParam("idSousGroupe") Integer idSousGroupe, @RequestParam("idPromo") Integer idPromo) {
        sousGroupeService.deleteSousGroupeById(idSousGroupe);
        return "redirect:/gestionnaire-edt/groupes/promo/" + idPromo;
    }
}