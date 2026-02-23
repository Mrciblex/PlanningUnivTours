package com.univtime.informatique.controllers;

import com.univtime.informatique.dto.groupeDto.GroupeDto;
import com.univtime.informatique.services.GroupeService;
import com.univtime.informatique.services.PromoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/gestion-groupes")
public class GestionGroupeController {

    private final GroupeService groupeService;
    private final PromoService promoService;

    public GestionGroupeController(GroupeService groupeService, PromoService promoService) {
        this.groupeService = groupeService;
        this.promoService = promoService;
    }

    @GetMapping("/gestion_groupes")
    public String showEdt() {
        return "edt";
    }

    @GetMapping
    public String listAllGroupes(Model model) {
        List<GroupeDto> groupes = groupeService.findAllGroupe();
        model.addAttribute("groupes", groupes);
        return "gestion_groupes";
    }

    @GetMapping("/{id}")
    public String getGroupeById(@PathVariable Integer id, Model model) {
        GroupeDto groupe = groupeService.findGroupeDtoById(id);
        model.addAttribute("groupe", groupe);
        return "groupeDetail";
    }

    @GetMapping("/new")
    public String formCreation(Model model) {
        model.addAttribute("groupe", new GroupeDto());
        model.addAttribute("promos", promoService.findAllPromos());
        return "newGroupe";
    }

    @PostMapping("/new")
    public String createGroupe(@ModelAttribute GroupeDto groupeDto) {
        GroupeDto saved = groupeService.createGroupe(groupeDto);
        return "redirect:/groupes/" + saved.getIdGroupe();
    }

    @GetMapping("/{id}/edit")
    public String formDeModification(@PathVariable Integer id, Model model) {
        GroupeDto groupe = groupeService.findGroupeDtoById(id);
        model.addAttribute("groupe", groupe);
        model.addAttribute("promos", promoService.findAllPromos());
        return "editGroupe";
    }

    @PostMapping("/{id}/edit")
    public String updateGroupe(@PathVariable Integer id, @ModelAttribute GroupeDto groupeDto) {

        groupeDto.setIdGroupe(id);
        groupeService.updateGroupe(groupeDto);

        return "redirect:/groupes/" + id;
    }

    @GetMapping("/{id}/delete")
    public String deleteGroupe(@PathVariable Integer id) {
        groupeService.deleteGroupeById(id);
        return "redirect:/groupes";
    }
}
