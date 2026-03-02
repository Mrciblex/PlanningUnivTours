package com.univtime.informatique.controllers;

import com.univtime.informatique.dto.composanteDto.CMComposanteDto;
import com.univtime.informatique.dto.composanteDto.ComposanteDto;
import com.univtime.informatique.dto.composanteDto.TDComposanteDto;
import com.univtime.informatique.dto.composanteDto.TPComposanteDto;
import com.univtime.informatique.services.ComposanteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/gestionnaire-edt/maquette")
public class MaquetteController {
    private final ComposanteService composanteService;

    public MaquetteController(ComposanteService composanteService) {
        this.composanteService = composanteService;
    }

    /**
     * URL : /gestionnaire-edt/maquette
     */
    @GetMapping
    public String afficherMaquettes(Model model) {
        model.addAttribute("composantes", composanteService.findAllComposantes());
        List<ComposanteDto> composantes_weeksem = composanteService.findAllComposantes();

        for (ComposanteDto c : composantes_weeksem) {

            // ===== CM =====
            c.setCmNbParSem(
                    nbBySem(
                            c.getCmDto(),
                            CMComposanteDto::getNumSemaine
                    )
            );

            // ===== TD =====
            c.setTdNbParSem(
                    nbBySem(
                            c.getTdDto(),
                            TDComposanteDto::getNumSemaine
                    )
            );

            // ===== TP =====
            c.setTpNbParSem(
                    nbBySem(
                            c.getTpDto(),
                            TPComposanteDto::getNumSemaine
                    )
            );
        }

        model.addAttribute("composantes", composantes_weeksem);
        return "gestionnaire_edt/maquette";
    }

    private <T> Map<Integer, Integer> nbBySem(
            Set<T> items,
            Function<T, Integer> semExtracteur) {

        Map<Integer, Integer> map = new HashMap<>();

        // Pour 12 semaines
        for (int i = 1; i <= 12; i++) {
            map.put(i, 0);
        }

        if (items == null) return map;

        for (T item : items) {
            Integer sem = semExtracteur.apply(item);
            if (sem != null) {
                map.put(sem, map.getOrDefault(sem, 0) + 1);
            }
        }

        return map;
    }
}
