package com.univtime.informatique.services;

import com.univtime.informatique.helpers.VolumeMatiereResult;
import com.univtime.informatique.dto.composanteDto.ComposanteDto;
import com.univtime.informatique.controllers.RecapitulatifController.*;
import com.univtime.informatique.repositories.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecapitulatifService {

    private final CoursRepository coursRepository;
    private final CMRepository cmRepository;
    private final TDRepository tdRepository;
    private final TPRepository tpRepository;
    private final PromoService promoService;
    private final ComposanteService composanteService;

    public RecapitulatifService(CoursRepository coursRepository, CMRepository cmRepository,
                                TDRepository tdRepository, TPRepository tpRepository,
                                PromoService promoService, ComposanteService composanteService) {
        this.coursRepository = coursRepository;
        this.cmRepository = cmRepository;
        this.tdRepository = tdRepository;
        this.tpRepository = tpRepository;
        this.promoService = promoService;
        this.composanteService = composanteService;
    }

    public Recap getRecapForPromo(Integer idPromo, Integer numSemestre) {
        var promo = promoService.findPromoDtoById(idPromo);

        // Bornes du semestre
        LocalDateTime debut = (numSemestre == 1) ? promo.getDebutS1Promo().atStartOfDay() : promo.getDebutS2Promo().atStartOfDay();
        LocalDateTime fin = (numSemestre == 1) ? promo.getFinS1Promo().atTime(23, 59) : promo.getFinS2Promo().atTime(23, 59);
        int wStart = (numSemestre == 1) ? 1 : 27; // Modifie ces bornes si la numérotation de tes semaines est différente
        int wEnd = (numSemestre == 1) ? 26 : 52;

        // 1. Récupération des cours placés
        List<Object[]> rawData = coursRepository.getVolumePlaceParMatiereSemestre(idPromo, debut, fin);

        // Sécurisation contre le null et Grouping
        Map<Integer, Map<String, Double>> reelMap = rawData.stream()
                .filter(row -> row[0] != null && row[1] != null && row[2] != null)
                .map(row -> new VolumeMatiereResult(
                        ((Number) row[0]).intValue(),
                        row[1].toString(),
                        ((Number) row[2]).doubleValue()
                ))
                .collect(Collectors.groupingBy(
                        VolumeMatiereResult::idComposante,
                        Collectors.toMap(VolumeMatiereResult::typeCours, VolumeMatiereResult::heures)
                ));

        // 2. Construction des statistiques des matières
        List<ComposanteDto> composantes = composanteService.findComposantesDtoByIdPromo(idPromo);
        List<MatiereStats> matieresStats = new ArrayList<>();

        for (ComposanteDto comp : composantes) {
            Integer idComp = comp.getIdComposante();

            double cmP = Optional.ofNullable(cmRepository.sumVolumeCMBySemestre(idPromo, idComp, wStart, wEnd)).orElse(0.0);
            double tdP = Optional.ofNullable(tdRepository.sumVolumeTDBySemestre(idPromo, idComp, wStart, wEnd)).orElse(0.0);
            double tpP = Optional.ofNullable(tpRepository.sumVolumeTPBySemestre(idPromo, idComp, wStart, wEnd)).orElse(0.0);

            double cmR = reelMap.getOrDefault(idComp, Map.of()).getOrDefault("CM", 0.0);
            double tdR = reelMap.getOrDefault(idComp, Map.of()).getOrDefault("TD", 0.0);
            double tpR = reelMap.getOrDefault(idComp, Map.of()).getOrDefault("TP", 0.0);

            // Arrondir pour éviter les affichages du type "18.3333h"
            cmR = Math.round(cmR * 10.0) / 10.0;
            tdR = Math.round(tdR * 10.0) / 10.0;
            tpR = Math.round(tpR * 10.0) / 10.0;
            cmP = Math.round(cmP * 10.0) / 10.0;
            tdP = Math.round(tdP * 10.0) / 10.0;
            tpP = Math.round(tpP * 10.0) / 10.0;

            double totalR = cmR + tdR + tpR;
            double totalP = cmP + tdP + tpP;

            matieresStats.add(new MatiereStats(
                    comp.getNomComposante(),
                    totalR, totalP,
                    new TypeStats(cmR, cmP), new TypeStats(tdR, tdP), new TypeStats(tpR, tpP)
            ));
        }

        // Pour les stats globales en haut de page
        int nbMatieres = matieresStats.size();
        List<ProfStats> profsStats = new ArrayList<>(); // Remplacer par l'algo Prof plus tard

        return new Recap(promo.getNbEtuPromo(), 3, 6, nbMatieres, 18, 12, matieresStats, profsStats);
    }
}