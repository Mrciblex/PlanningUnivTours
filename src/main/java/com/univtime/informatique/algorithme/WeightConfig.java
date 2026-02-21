package com.univtime.informatique.algorithme;

// MIN 0, 1 MAX
public class WeightConfig {
    /**
     * Critère 1 : Un cours à 8h (donc premier slot du jour)
     */
    private static Double placementPlusTotPossible = 1.;

    /**
     * Critère 4 : Un cours est dans la matinée avant 12h15
     */
    private static Double placementMatin = 0.5;

    /**
     * Critère 5 : Si le slot est utilisé et n'est pas tard donc ça concerne le dernier cours (coef plus c'est tard = plus score fait mal)
     * Ce critère est spécial car il a un impact que s'il est > 0. Et cette impact est de plus en plus important de >0 à 1.
     */
    private static Double placementPasFinTard = 0.;

    /**
     * Si le slot créer un trou
     */
    private static Double placementTrou = 0.;

    /**
     * Si le slot est utilisé et ne correspond pas au cours de la semaine 1
     * (attention, s'il y a 1 cours S1 puis 2 cours S2 dans le même slot, si le cours S1 est quand même là il ne faut pas de pénalité)
     * Première occurence d'un cours
     */
    private static Double placementReference = 0.;

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

    public static Double getPlacementPasFinTard() {
        return placementPasFinTard;
    }

    public static void setPlacementPasFinTard(Double placementPasFinTard) {
        WeightConfig.placementPasFinTard = placementPasFinTard;
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

    public static Double getRepetitionCoursDansJournee() {
        return repetitionCoursDansJournee;
    }

    public static void setRepetitionCoursDansJournee(Double repetitionCoursDansJournee) {
        WeightConfig.repetitionCoursDansJournee = repetitionCoursDansJournee;
    }

    /**
     * Calcule le score d'un critère selon s'il est respecté ou non.
     */
    private static double calculerScore(boolean evenementSeProduit,
                                        double poids) {
        if (evenementSeProduit) {
            return poids;
        } else {
            return 1.0 - poids;
        }
    }

    private static double calculerMoyenne(double... valeurs) {
        double somme = 0.0;
        for (double valeur : valeurs) {
            somme += valeur;
        }
        return somme / valeurs.length;
    }

    public static void evaluationPlacements(Jour jour) {
        if (jour != null) {
            Slot lastSlot = jour.getSlots().getFirst();
            for (int index = 0; index < jour.getSlots().size(); index++) {
                Slot slot = jour.getSlots().get(index);

                Slot precedentSlot = null;
                if (index > 0) {
                    precedentSlot = jour.getSlots().get(index - 1);
                }

                Double scoreActuel = slot.getScore();

                // ---------------------------------------------------------
                // Critère 1 : Un cours à 8h (donc premier slot du jour)
                // ---------------------------------------------------------
                boolean isDebutJour = slot.getDebut().equals(60 * 8) && !slot.getUsedBy().isEmpty();
                Double score1 = WeightConfig.calculerScore(isDebutJour, WeightConfig.getPlacementPlusTotPossible());

                // ---------------------------------------------------------
                // Critère 4 : Un cours est dans la matinée avant 12h15
                // ---------------------------------------------------------
                double score4 = WeightConfig.calculerScore(
                        !slot.getUsedBy().isEmpty() && slot.getFin() <= 60 * 12 + 15,
                        WeightConfig.getPlacementMatin()
                );

                // Critère 5 :
                if (!slot.getUsedBy().isEmpty()) {
                    lastSlot = slot;
                }


                // Calcul moyenne après tout les scores calculer :
                slot.setScore(calculerMoyenne(scoreActuel, score1, score4));
            }

            // ---------------------------------------------------------
            // Critère 5 : Si le slot est utilisé et n'est pas tard donc ça concerne le dernier cours (coef plus c'est tard = plus score fait mal)
            // ---------------------------------------------------------
            double heurePivot = jour.getSlots().getFirst().getDebut(); // Dès le début
            double heureLimite = jour.getSlots().getLast().getFin();
            double finCours = lastSlot.getFin();

            double coefHoraire;

            if (finCours <= heurePivot) {
                coefHoraire = 1.0; // Aucune pénalité avant l'heure de pivot
            } else {
                coefHoraire = 1.0 - ((finCours - heurePivot) / (heureLimite - heurePivot));

                // coef ne descend pas en dessous de 0
                coefHoraire = Math.max(0, coefHoraire);
            }

            double score5 = WeightConfig.calculerScore(
                    true,
                    WeightConfig.getPlacementPasFinTard() * coefHoraire
            );

            lastSlot.setScore(calculerMoyenne(lastSlot.getScore(), score5));
        }
    }
}
