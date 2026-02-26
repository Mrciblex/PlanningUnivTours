package com.univtime.informatique.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gestionnaire-edt/edt")
public class EdtController {
    /**
     * URL : /gestionnaire-edt/edt
     */
    @GetMapping
    public String afficheredt() {
        return "gestionnaire_edt/edt";
    }
}
