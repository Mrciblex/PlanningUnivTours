package com.univtime.informatique.controllers;

import com.univtime.informatique.dto.coursDto.CoursDto;
import com.univtime.informatique.services.CoursService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/gestionnaire-edt/edt")
public class EdtController {
    private final CoursService coursService;

    public EdtController(CoursService coursService){
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

        String[] lastPromoSplit;
        int lastPromoId;
        try {
            lastPromoSplit = lastPromo.split("-");
            lastPromoId = Integer.parseInt(lastPromoSplit[0]);

        } catch (Exception e) {
            // En cas de cookie mal formé, on redirige pour éviter le crash
            return "redirect:/";
        }

        Integer numSemestre = Integer.parseInt(lastPromoSplit[1]);
        List<CoursDto> cours = coursService.findAllCoursByPromoIdBySemestre(lastPromoId, numSemestre);

        model.addAttribute("cours", cours);
        return "gestionnaire_edt/edt";
    }

    @GetMapping("/{idPromo}/{numSemestre}")
    public String edtOfPromo(@PathVariable Integer idPromo, @PathVariable Integer numSemestre, HttpServletResponse response, Model model) {
        if (!(numSemestre == 1 || numSemestre == 2)) return "redirect:/"; // Securite pour limite de semestre

        Cookie cookie = new Cookie("lastPromo", idPromo.toString() + "-" + numSemestre);
        cookie.setMaxAge(60 * 60 * 24 * 365); // 30 jours
        cookie.setPath("/");
        response.addCookie(cookie);

        List<CoursDto> cours = coursService.findAllCoursByPromoIdBySemestre(idPromo, numSemestre);
        model.addAttribute("cours", cours);

        return "gestionnaire_edt/edt";
    }

    // edt promo
    @PostMapping("/new")
    public String createCours(@ModelAttribute CoursDto coursDto) {
        coursService.createCours(coursDto);

        return "redirect:/gestionnaire-edt/edt";// je sais pas quoi mettre
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
