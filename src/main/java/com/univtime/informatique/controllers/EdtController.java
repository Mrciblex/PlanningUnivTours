package com.univtime.informatique.controllers;

import com.univtime.informatique.dto.coursDto.CoursDto;
import com.univtime.informatique.dto.promoDto.PromoDto;
import com.univtime.informatique.services.CoursService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/gestionnaire-edt/edt")
public class EdtController {
    private CoursService coursService;
    public EdtController(CoursService coursService){
        this.coursService = coursService;

    }
    /**
     * URL : /gestionnaire-edt/edt
     */
    @GetMapping
    public String afficheredt() {
        return "gestionnaire_edt/edt";
    }
    // edt promo
    @PostMapping("/new")
    public String createCours(@ModelAttribute CoursDto coursDto) {
        coursService.createCours(coursDto);

        return "redirect:/gestionnaire-edt/edt";// je sais pas quoi mettree
    }
    @PostMapping("/{id}/edit")
    public String updateCours(@PathVariable Integer id, @ModelAttribute CoursDto coursDto) {

        coursDto.setIdCours(id);
        coursService.updateCours(new CoursDto());

        return "redirect:/gestionnaire-edt/edt";
    }

    @PostMapping("/{id}/delete")
    public String deleteCours(@PathVariable Integer id, @ModelAttribute CoursDto coursDto) {

        coursService.deleteCoursById(id);
        return "redirect:/gestionnaire-edt/edt";
    }
}
