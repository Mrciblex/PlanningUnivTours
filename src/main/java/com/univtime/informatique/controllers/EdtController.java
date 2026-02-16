package com.univtime.informatique.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gestionnaire_edt")
public class EdtController {

    @GetMapping("/edt")
    public String showEdt() {
        return "edt";
    }
}

