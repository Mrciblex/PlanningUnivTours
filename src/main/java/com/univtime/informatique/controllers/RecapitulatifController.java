package com.univtime.informatique.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gestionnaire-edt/recapitulaltif")
public class RecapitulatifController {
    /**
     * URL : /gestionnaire-edt/recapitulaltif
     */
    @GetMapping
    public String afficherRecapitulatif() {
        return "gestionnaire_edt/recapitulatif";
    }
}
