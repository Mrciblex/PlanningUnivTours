package com.univtime.informatique.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/gestionnaire-edt")
public class GestionnaireEdtController {

    @GetMapping("/edt")
    public String edt() {
        return "gestionnaire_edt/edt";
    }

    @GetMapping("/composantes")
    public String composantes() {
        return "gestionnaire_edt/gestion_composantes";
    }

    @GetMapping("/groupes")
    public String groupes() {
        return "gestionnaire_edt/gestion_groupes";
    }

    @GetMapping("/professeurs")
    public String professeurs() {
        return "gestionnaire_edt/gestion_professeurs";
    }
}
