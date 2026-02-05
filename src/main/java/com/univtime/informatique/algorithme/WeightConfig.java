package com.univtime.informatique.algorithme;

import com.univtime.informatique.dtos.coursDto.CoursDto;

import java.util.List;

// MIN -10, 0 MAX
// Chaque placement part avec 100 pts
public class WeightConfig {
    /**
     * Si le slot créer un trou
     */
    private static int placementTrou = 0;

    /**
     * Si le slot est utilisé et est tard (coef plus c'est tard = plus score fait mal)
     */
    private static int placementFinTard = 0;

    /**
     * Si le slot est utilisé et n'est le matin avant 12h15
     */
    private static int placementMatin = 0;

    /**
     * Si le slot est utilisé et n'est pas à 8h
     */
    private static int placementDebutJour = 0;

    /**
     * Si le slot est utilisé et ne correspond pas au cours de la semaine 1
     * (attention, s'il y a 1 cours S1 puis 2 cours S2 dans le même slot, si le cours S1 est quand même là il ne faut pas de pénalité)
     */
    private static int placementReference = 0;

    /**
     * Si le slot est utilisé et le cours est à une heure fixe (8h, 10h15, 13h30, 15h45, 18h)
     */
    private static int placementArrondit = 0;

    /**
     * Si les slots ont le même cours mais avec une séparation entre les slots (donc 2 fois le cours dans la journée)
     */
    private static int repetitionCoursDansJournee = 0;

    public static int getPlacementTrou() {
        return placementTrou;
    }

    public static void setPlacementTrou(int placementTrou) {
        WeightConfig.placementTrou = placementTrou;
    }

    public static int getPlacementFinTard() {
        return placementFinTard;
    }

    public static void setPlacementFinTard(int placementFinTard) {
        WeightConfig.placementFinTard = placementFinTard;
    }

    public static int getPlacementMatin() {
        return placementMatin;
    }

    public static void setPlacementMatin(int placementMatin) {
        WeightConfig.placementMatin = placementMatin;
    }

    public static int getPlacementDebutJour() {
        return placementDebutJour;
    }

    public static void setPlacementDebutJour(int placementDebutJour) {
        WeightConfig.placementDebutJour = placementDebutJour;
    }

    public static int getPlacementReference() {
        return placementReference;
    }

    public static void setPlacementReference(int placementReference) {
        WeightConfig.placementReference = placementReference;
    }

    public static int getPlacementArrondit() {
        return placementArrondit;
    }

    public static void setPlacementArrondit(int placementArrondit) {
        WeightConfig.placementArrondit = placementArrondit;
    }

    public static int getRepetitionCoursDansJournee() {
        return repetitionCoursDansJournee;
    }

    public static void setRepetitionCoursDansJournee(int repetitionCoursDansJournee) {
        WeightConfig.repetitionCoursDansJournee = repetitionCoursDansJournee;
    }

    public static int calcScore(CoursDto cours, List<Slot> jour) {
        int score = 100;
        if (cours != null && !jour.isEmpty()) {
            // Retirer ou pas des points pour chaque placement
        }
        return score;
    }
}
