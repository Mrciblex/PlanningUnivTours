package com.univtime.informatique.controllers;

import com.univtime.informatique.dto.coursDto.CoursDto;
import com.univtime.informatique.services.CoursService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/gestionnaire-edt/edt")
public class EdtController {
    private final CoursService coursService;

    public EdtController(CoursService coursService) {
        this.coursService = coursService;
    }

    /**
     * URL : /gestionnaire-edt/edt
     */
    @GetMapping
    public String index(@CookieValue(value = "lastPromo", required = false) String lastPromo, Model model) {
        if (lastPromo == null || lastPromo.isEmpty()) {
            return "redirect:/";
        }

        Integer lastPromoId = null;
        try {
            String[] lastPromoSplit = lastPromo.split("-");
            lastPromoId = Integer.valueOf(lastPromoSplit[0]);

        } catch (Exception e) {
            // En cas de cookie mal formé, on redirige pour éviter le crash
            return "redirect:/";
        }

        List<CoursDto> cours = coursService.findAllCoursByPromoId(lastPromoId);

        System.out.println(cours);
        model.addAttribute("cours", cours);
        return "gestionnaire_edt/edt";
    }

    @GetMapping("/{idPromo}/{numSemestre}")
    public String edtOfPromo(@PathVariable Integer idPromo, @PathVariable Integer numSemestre, HttpServletResponse response, Model model) {
        if (!(numSemestre == 1 || numSemestre == 2)) return null; // Securite pour limite de semestre

        Cookie cookie = new Cookie("lastPromo", idPromo.toString() + "-" + numSemestre);
        cookie.setMaxAge(60 * 60 * 24 * 365); // 30 jours
        cookie.setPath("/");
        response.addCookie(cookie);

        List<CoursDto> cours = coursService.findAllCoursByPromoId(idPromo);
        return "gestionnaire_edt/edt";
    }
}
