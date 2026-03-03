package com.univtime.informatique.controllers;

import com.univtime.informatique.algorithme.AlgorithmeResponse;
import com.univtime.informatique.algorithme.GenerationAlgorithme;
import com.univtime.informatique.algorithme.WeightConfig;
import com.univtime.informatique.dto.coursDto.CoursDto;
import com.univtime.informatique.dto.promoDto.PromoDto;
import com.univtime.informatique.services.CoursService;
import com.univtime.informatique.services.PromoService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/gestionnaire-edt/edt")
public class EdtController {
    private final CoursService coursService;

    private final PromoService promoService;

    private final GenerationAlgorithme generationAlgorithme;

    public EdtController(CoursService coursService,
                         PromoService promoService,
                         GenerationAlgorithme generationAlgorithme){
        this.coursService = coursService;

        this.promoService = promoService;

        this.generationAlgorithme = generationAlgorithme;
    }

    /**
     * URL : /gestionnaire-edt/edt
     */
    @GetMapping
    public String index(@CookieValue(value = "lastPromo") String lastPromo,
                        Model model) {
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

        PromoDto promo = promoService.findPromoDtoById(lastPromoId);
        model.addAttribute("promo", promo);

        model.addAttribute("cours", cours);
        model.addAttribute("numSemestre", numSemestre);
        return "gestionnaire_edt/edt";
    }

    @GetMapping("/{idPromo}/{numSemestre}")
    public String edtOfPromo(@PathVariable Integer idPromo,
                             @PathVariable Integer numSemestre,
                             HttpServletResponse response,
                             Model model) {
        if (!(numSemestre == 1 || numSemestre == 2)) return "redirect:/"; // Securite pour limite de semestre

        Cookie cookie = new Cookie("lastPromo", idPromo.toString() + "-" + numSemestre);
        cookie.setMaxAge(60 * 60 * 24 * 365); // 30 jours
        cookie.setPath("/");
        response.addCookie(cookie);

        PromoDto promo = promoService.findPromoDtoById(idPromo);
        List<CoursDto> cours = coursService.findAllCoursByPromoIdBySemestre(idPromo, numSemestre);

        model.addAttribute("cours", cours);
        model.addAttribute("promo", promo);
        model.addAttribute("numSemestre", numSemestre);

        return "gestionnaire_edt/edt";
    }

    public record GenerationRequest(
            Map<String, Double> weights,
            List<Integer> existingCourseIds
    ) {}

    @PostMapping(value = "/generate/{idPromo}/{numSemestre}", produces = "application/json")
    @ResponseBody // Pour renvoyer l'AlgorithmeResponse en JSON
    public AlgorithmeResponse generateEdt(@PathVariable Integer idPromo,
                                          @PathVariable Integer numSemestre,
                                          @RequestBody GenerationRequest request) {
        Map<String, Double> w = request.weights();
        WeightConfig.setRepetitionCoursDansJournee(w.get("repetition"));
        WeightConfig.setPlacementPlusTotPossible(w.get("tot"));
        WeightConfig.setPlacementMatin(w.get("matin"));
        WeightConfig.setPlacementPasFinTard(w.get("tard"));
        WeightConfig.setPlacementSansTrou(w.get("trous"));
        WeightConfig.setPlacementReference(w.get("regu"));

        // Supprimer les cours existants pour cette promo et ce semestre
        if (request.existingCourseIds() != null && !request.existingCourseIds().isEmpty()) {
            request.existingCourseIds().forEach(coursService::deleteCoursByIdWithRelations);
        }

        // Lancer l'algorithme
        AlgorithmeResponse response = generationAlgorithme.generatePlanning(idPromo, numSemestre, null);
        System.out.println(response.afficherCoursPlaces());

        // Enregistrer les cours placés
        for (CoursDto cours : response.implementedCours()) {
            coursService.createCours(cours);
        }

        return response;
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
