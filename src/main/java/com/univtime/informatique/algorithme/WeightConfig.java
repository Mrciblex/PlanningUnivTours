package com.univtime.informatique.algorithme;

import com.univtime.informatique.dto.coursDto.CoursDto;

import java.util.List;

// MIN 0, 1 MAX
public class WeightConfig {
    /**
     * Critère 1 : Un cours à 8h (donc premier slot du jour)
     */
    private static Double placementPlusTotPossible = 1.;

    /**
     * Si le slot créer un trou
     */
    private static Double placementTrou = 0.;

    /**
     * Si le slot est utilisé et est tard (coef plus c'est tard = plus score fait mal)
     */
    private static Double placementFinTard = 0.;

    /**
     * Si le slot est utilisé et n'est le matin avant 12h15
     */
    private static Double placementMatin = 0.;

    /**
     * Si le slot est utilisé et ne correspond pas au cours de la semaine 1
     * (attention, s'il y a 1 cours S1 puis 2 cours S2 dans le même slot, si le cours S1 est quand même là il ne faut pas de pénalité)
     */
    private static Double placementReference = 0.;

    /**
     * Si le slot est utilisé et le cours est à une heure fixe (8h, 10h15, 13h30, 15h45, 18h)
     */
    private static Double placementArrondit = 0.;

    /**
     * Si les slots ont le même cours mais avec une séparation entre les slots (donc 2 fois le cours dans la journée)
     */
    private static Double repetitionCoursDansJournee = 0.;

    public static Double getPlacementTrou() {
        return placementTrou;
    }

    public static void setPlacementTrou(Double placementTrou) {
        WeightConfig.placementTrou = placementTrou;
    }

    public static Double getPlacementFinTard() {
        return placementFinTard;
    }

    public static void setPlacementFinTard(Double placementFinTard) {
        WeightConfig.placementFinTard = placementFinTard;
    }

    public static Double getPlacementMatin() {
        return placementMatin;
    }

    public static void setPlacementMatin(Double placementMatin) {
        WeightConfig.placementMatin = placementMatin;
    }

    public static Double getPlacementPlusTotPossible() {
        return placementPlusTotPossible;
    }

    public static void setPlacementPlusTotPossible(Double placementPlusTotPossible) {
        WeightConfig.placementPlusTotPossible = placementPlusTotPossible;
    }

    public static Double getPlacementReference() {
        return placementReference;
    }

    public static void setPlacementReference(Double placementReference) {
        WeightConfig.placementReference = placementReference;
    }

    public static Double getPlacementArrondit() {
        return placementArrondit;
    }

    public static void setPlacementArrondit(Double placementArrondit) {
        WeightConfig.placementArrondit = placementArrondit;
    }

    public static Double getRepetitionCoursDansJournee() {
        return repetitionCoursDansJournee;
    }

    public static void setRepetitionCoursDansJournee(Double repetitionCoursDansJournee) {
        WeightConfig.repetitionCoursDansJournee = repetitionCoursDansJournee;
    }

    /**
     * Calcule le score d'un critère selon s'il est respecté ou non.
     */
    private static double calculerScore(boolean evenementSeProduit, double poids) {
        if (evenementSeProduit) {
            return poids;
        } else {
            return 1.0 - poids;
        }
    }

    private static double calculerMoyenne(double... valeurs){
        double somme = 0.0;
        for (double valeur : valeurs) {
            somme += valeur;
        }
        return somme / valeurs.length;
    }

    public static void evaluationPlacements(Jour jour) {
        if (jour != null) {
            jour.getSlots().forEach(slot -> {
                Double scoreActuel = slot.getScore();

                // ---------------------------------------------------------
                // Critère 1 : Un cours à 8h (donc premier slot du jour)
                // ---------------------------------------------------------
                boolean isDebutJour = slot.getDebut().equals(60 * 8) && !slot.getUsedBy().isEmpty();
                Double score1 = WeightConfig.calculerScore(isDebutJour, WeightConfig.getPlacementPlusTotPossible());

                // ---------------------------------------------------------
                // Critère 2 :
                // ---------------------------------------------------------


                // Calcul moyenne après tout les scores calculer :
                slot.setScore(calculerMoyenne(scoreActuel, score1));
            });
        }
    }
}
