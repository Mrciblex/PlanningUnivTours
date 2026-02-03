package com.univtime.informatique.algorithme;

import com.univtime.informatique.UnivTime;

import java.util.List;

// MIN -10, 0 MAX
// Chaque placement part avec 100 pts
public class WeightConfig {
    /**
     * Si le slot créer un trou
     */
    static int placementTrou = 0;

    /**
     * Si le slot est utilisé et est tard (coef plus c'est tard = plus score fait mal)
     */
    static int placementFinTard = 0;

    /**
     * Si le slot est utilisé et n'est le matin avant 12h15
     */
    static int placementMatin = 0;

    /**
     * Si le slot est utilisé et n'est pas à 8h
     */
    static int placementDebutJour = 0;

    /**
     * Si le slot est utilisé et ne correspond pas au cours de la semaine 1
     * (attention, s'il y a 1 cours S1 puis 2 cours S2 dans le même slot, si le cours S1 est quand même là il ne faut pas de pénalité)
     */
    static int placementReference = 0;

    /**
     * Si le slot est utilisé et le cours est à une heure fixe (8h, 10h15, 13h30, 15h45, 18h)
     */
    static int placementArrondit = 0;

    /**
     * Si les slots ont le même cours mais avec une séparation entre les slots (donc 2 fois le cours dans la journée)
     */
    static int repetitionCoursDansJournee = 0;

    int calcScore(Cours cours, List<UnivTime.Slot> jour) {
        int score = 100;
        if (cours != null && !jour.isEmpty()) {
            // Retirer ou pas des points pour chaque placement
        }
        return score;
    }

    void setPlacementTrou(int placementTrou) {
        this.placementTrou = placementTrou;
    }

    void setPlacementFinTard(int placementFinTard) {
        this.placementFinTard = placementFinTard;
    }

    void setPlacementMatin(int placementMatin) {
        this.placementMatin = placementMatin;
    }

    void setPlacementDebutJour(int placementDebutJour) {
        this.placementDebutJour = placementDebutJour;
    }

    void setRepetitionCoursDansJournee(int repetitionCoursDansJournee) {
        this.repetitionCoursDansJournee = repetitionCoursDansJournee;
    }

    void setPlacementReference(int placementReference) {
        this.placementReference = placementReference;
    }

    void setPlacementArrondit(int placementArrondit) {
        this.placementArrondit = placementArrondit;
    }
}
