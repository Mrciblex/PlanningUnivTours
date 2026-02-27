package com.univtime.informatique.controllers;

import com.univtime.informatique.dto.coursDto.CoursDto;
import com.univtime.informatique.dto.promoDto.PromoDto;
import com.univtime.informatique.services.CoursService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    @PostMapping("/new")
    public String createCours(@ModelAttribute CoursDto coursDto) {
        coursService.createCours(coursDto);

        return "redirect:/gestionnaire-edt/edt";// je sais pas quoi mettree
    }
}
