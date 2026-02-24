package com.univtime.informatique.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class GestionPromosController {

    @GetMapping("/gestion-promos")
    public String gestionPromos() {
        return "gestionnaire_promos";
    }
}

