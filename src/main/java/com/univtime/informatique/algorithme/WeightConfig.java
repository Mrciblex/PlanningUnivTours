package com.univtime.informatique.algorithme;

import com.univtime.informatique.dto.coursDto.CoursDto;
import com.univtime.informatique.dto.coursDto.ParticipeACoursDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// MIN 0, 1 MAX
public class WeightConfig {
    /**
     * Critère 1 : Si un même cours et même type se répète plusieur fois dans la même journée
     */
    private static Double repetitionCoursDansJournee = 1.;

    /**
     * Critère 2 : Un cours à 8h (donc premier slot du jour)
     */
    private static Double placementPlusTotPossible = 1.;

    /**
     * Critère 3 : Un cours est dans la matinée avant 12h15
     */
    private static Double placementMatin = 1.;

    /**
     * Critère 4 : Si le slot est utilisé et n'est pas tard donc ça concerne le dernier cours (coef plus c'est tard = plus score fait mal)
     * Ce critère est spécial car il a un impact que s'il est > 0. Et cette impact est de plus en plus important de >0 à 1.
     */
    private static Double placementPasFinTard = 0.;

    /**
     * Critère 5 : Un cours ne créer pas de trou
     */
    private static Double placementSansTrou = 1.;

    /**
     * Si le slot est utilisé et ne correspond pas au cours de la semaine 1
     * (attention, s'il y a 1 cours S1 puis 2 cours S2 dans le même slot, si le cours S1 est quand même là il ne faut pas de pénalité)
     * Première occurence d'un cours
     */
    private static Double placementReference = 0.;

    public static Double getPlacementSansTrou() {
        return placementSansTrou;
    }

    public static void setPlacementSansTrou(Double placementSansTrou) {
        WeightConfig.placementSansTrou = placementSansTrou;
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
            HashMap<String, Integer> instancesOfCoursInDay = new  HashMap<>();
            HashMap<ParticipeACoursDto, Integer> lastInstanceOfSousGroupeInDay = new  HashMap<>();
            Slot lastSlot = jour.getSlots().getFirst();
            for (int index = 0; index < jour.getSlots().size(); index++) {
                Slot slot = jour.getSlots().get(index);

                // ---------------------------------------------------------
                // Critère 1 : Si un même cours et même type se répète plusieur fois dans la même journée
                // ---------------------------------------------------------
                // ---------------------------------------------------------
                // Critère 5 : Un cours ne créer pas de trou
                // ---------------------------------------------------------
                double score1Sum = 0.;
                double score5sum = 0.;
                for(int usedByIndex = 0; usedByIndex < slot.getUsedBy().size(); usedByIndex++) {
                    CoursDto courOfSlot = slot.getUsedBy().get(usedByIndex);

                    Slot precedentSlot = null;
                    if (index > 0) {
                        precedentSlot = jour.getSlots().get(index - 1);
                    }

                    for(int participantIndex = 0; participantIndex < courOfSlot.getParticipeADto().size(); participantIndex++) {
                        List<ParticipeACoursDto> sgList = new ArrayList<>(courOfSlot.getParticipeADto());
                        ParticipeACoursDto sg = sgList.get(participantIndex);

                        if(lastInstanceOfSousGroupeInDay.containsKey(sg)){
                            Integer lastInstance = lastInstanceOfSousGroupeInDay.get(sg);

                            // Le groupe a déjà eu cours mais c'était le slot précédent donc pas de trou ici
                            score5sum += WeightConfig.calculerScore(precedentSlot == null || precedentSlot.getFin().equals(lastInstance), WeightConfig.getPlacementSansTrou());
                        }else{
                            score5sum += WeightConfig.calculerScore(true, WeightConfig.getPlacementSansTrou());
                        }

                        lastInstanceOfSousGroupeInDay.put(sg, slot.getFin());
                    }

                    score5sum = !courOfSlot.getParticipeADto().isEmpty() ? score5sum / courOfSlot.getParticipeADto().size() : score5sum;

                    // On crée une clé unique basée sur la matière et le type, pas sur l'instance Java
                    // car en Java on crée un nouveau cours à chaque itération de la boucle de l'algorithme dans les cours à placer (CM par exemple)
                    // donc ils ont tous une adresse mémoire différente
                    String courseKey = courOfSlot.getComposanteDto().getIdComposante() + "-" + courOfSlot.getTypeCoursEnum().name();
                    int instanceNb = instancesOfCoursInDay.merge(courseKey, 1, Integer::sum);

                    boolean isMultipleCour = instanceNb > courOfSlot.getComposanteDto().getBlocHoraire(courOfSlot.getTypeCoursEnum()) / 15;
                    score1Sum += WeightConfig.calculerScore(isMultipleCour, WeightConfig.getRepetitionCoursDansJournee());
                }

                Double score5 = !slot.getUsedBy().isEmpty() ? score5sum / slot.getUsedBy().size() : score5sum;

                Double score1 = !slot.getUsedBy().isEmpty() ? score1Sum / slot.getUsedBy().size() : score1Sum;

                Double scoreActuel = slot.getScore();

                // ---------------------------------------------------------
                // Critère 2 : Un cours à 8h (donc premier slot du jour)
                // ---------------------------------------------------------
                boolean isDebutJour = slot.getDebut().equals(60 * 8) && !slot.getUsedBy().isEmpty();
                Double score2 = WeightConfig.calculerScore(isDebutJour, WeightConfig.getPlacementPlusTotPossible());

                // ---------------------------------------------------------
                // Critère 3 : Un cours est dans la matinée avant 12h15
                // ---------------------------------------------------------
                double score3 = WeightConfig.calculerScore(
                        !slot.getUsedBy().isEmpty() && slot.getFin() <= 60 * 12 + 15,
                        WeightConfig.getPlacementMatin()
                );

                // Critère 4 :
                if (!slot.getUsedBy().isEmpty()) {
                    lastSlot = slot;
                }


                // Calcul moyenne après tout les scores calculer :
                slot.setScore(calculerMoyenne(score1, score2, score3, score5));
            }

            // ---------------------------------------------------------
            // Critère 4 : Si le slot est utilisé et n'est pas tard donc ça concerne le dernier cours (coef plus c'est tard = plus score fait mal)
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

            double score4 = WeightConfig.calculerScore(
                    true,
                    WeightConfig.getPlacementPasFinTard() * coefHoraire
            );

            lastSlot.setScore(calculerMoyenne(lastSlot.getScore(), score4));
        }
    }
}
