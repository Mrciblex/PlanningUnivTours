package com.univtime.informatique.controllers;

import com.univtime.informatique.dto.groupeDto.GroupeDto;
import com.univtime.informatique.services.GroupeService;
import com.univtime.informatique.services.PromoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("/gestionnaire-edt/groupes")
public class GestionGroupeController {

    private final GroupeService groupeService;
    private final PromoService promoService;

    public GestionGroupeController(GroupeService groupeService, PromoService promoService) {
        this.groupeService = groupeService;
        this.promoService = promoService;
    }
    /**
     * URL : /gestionnaire-edt/groupes
     */
    @GetMapping
    public String listAllGroupes(Model model) {
        List<GroupeDto> groupes = groupeService.findAllGroupe();
        model.addAttribute("groupes", groupes);
        return "gestionnaire_edt/gestion_groupes";
    }

    /**
     * URL : /gestionnaire-edt/groupes/{id}
     */
    @GetMapping("/{id}")
    public String getGroupeById(@PathVariable Integer id, Model model) {
        GroupeDto groupe = groupeService.findGroupeDtoById(id);
        model.addAttribute("groupe", groupe);
        return "gestionnaire_edt/groupeDetail";
    }

    @PostMapping("/new")
    public String createGroupe(@ModelAttribute GroupeDto groupeDto) {
        GroupeDto saved = groupeService.createGroupe(groupeDto);
        return "redirect:/gestionnaire-edt/groupes/" + saved.getIdGroupe();
    }

    @PostMapping("/{id}/edit")
    public String updateGroupe(@PathVariable Integer id, @ModelAttribute GroupeDto groupeDto) {

        groupeDto.setIdGroupe(id);
        groupeService.updateGroupe(groupeDto);

        return "redirect:/gestionnaire-edt/groupes/" + id;
    }

    @GetMapping("/{id}/delete")
    public String deleteGroupe(@PathVariable Integer id) {
        groupeService.deleteGroupeById(id);
        return "redirect:/gestionnaire-edt/groupes";
    }
}
