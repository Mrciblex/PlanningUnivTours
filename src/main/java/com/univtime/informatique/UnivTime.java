package com.univtime.informatique;

import com.univtime.informatique.constants.TypeCours;
import org.hibernate.query.spi.Limit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.core.Local;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class UnivTime {
    public static void main(String[] args) {
        //SpringApplication.run(UnivTime.class, args);
        System.out.println("Application lancé..");
        System.out.println("----------------- TEST ALGO -----------------");

        int slotStep = 15;
        List<Slot> slotsAvailable = new ArrayList<>();
        Semestre semestre = new Semestre(
                LocalDate.of(2025, 9, 1),
                LocalDate.of(2025, 9, 7)
        );

        // On calcule les semaines du lundi au dimanche pas que 7 + 7 + 7...
        // Donc si c'est pas par défaut setup comme ça, on corrige pour avoir un bon calcul du numSemaine
        LocalDate firstWeek = semestre.debut().with(DayOfWeek.MONDAY);
        LocalDate lastWeek = semestre.fin().with(DayOfWeek.MONDAY);

        List<DayOfWeek> excludedDays = new ArrayList<>();
        excludedDays.add(DayOfWeek.SATURDAY); // Samedi
        excludedDays.add(DayOfWeek.SUNDAY); // Dimanche

        List<PlanningPeriodMinutes> planningPossiblePeriod =  new ArrayList<PlanningPeriodMinutes>();
        planningPossiblePeriod.add(new PlanningPeriodMinutes(8 * 60, 10 * 60)); // 8h00 à 10h00
        planningPossiblePeriod.add(new PlanningPeriodMinutes(10 * 60 + 15, 12 * 60 + 15)); // 10h15 à 12h15
        planningPossiblePeriod.add(new PlanningPeriodMinutes(13 * 60 + 30, 15 * 60 + 30)); // 13h30 à 15h30
        planningPossiblePeriod.add(new PlanningPeriodMinutes(15 * 60 + 45, 17 * 60 + 45)); // 15h45 à 17h45
        planningPossiblePeriod.add(new PlanningPeriodMinutes(18 * 60, 20 * 60)); // 18h00 à 20h00

        HashMap<Integer, HashMap<Integer, List<Slot>>> slotsPerDayPerWeek = new HashMap<>();


        /**
         * Boucle sur tout les jours du semestre
         */
        long nbSemaine = ChronoUnit.WEEKS.between(firstWeek, lastWeek) + 1;
        Map<Integer, Map<Integer, List<Slot>>> slotsStorage = new HashMap<>();
        for (int weekOffset = 0; weekOffset < nbSemaine; weekOffset++){
            // Début et fin de la semaine du calendrier
            LocalDate weekStart = firstWeek.plusWeeks(weekOffset);
            LocalDate weekEnd = weekStart.plusDays(6);

            // Ajuster pour la première semaine partielle
            if (weekStart.isBefore(semestre.debut())){
                weekStart = semestre.debut();
            }

            // Ajuster pour la dernière semaine partielle
            if (weekEnd.isAfter(semestre.fin())){
                weekEnd = semestre.fin();
            }
            Map<Integer, List<Slot>> daySlots = new HashMap<>();
            for (int dayOffset = weekStart.getDayOfWeek().getValue() - 1; dayOffset < weekEnd.getDayOfWeek().getValue(); dayOffset++){
                LocalDate actualDay = semestre.debut().plusWeeks(weekOffset).plusDays(dayOffset);
                boolean isExclude = isExcludeDay(actualDay, excludedDays);

                if (!isExclude){
                    /**
                     * Création des slots de la semaine
                     */
                    List<Slot> slotOfDay = new ArrayList<>();
                    for(PlanningPeriodMinutes period : planningPossiblePeriod){
                        for (int start = period.debut(); start < period.fin(); start = start + slotStep){
                            boolean isBlocked = false;
                            Slot slot = new Slot(start, start + slotStep, isBlocked);
                            slotOfDay.add(slot);
                        }
                    }
                    daySlots.put(dayOffset + 1, slotOfDay);
                }
            }
            slotsStorage.put(weekOffset + 1, daySlots);

            // ------------ Placement des cours dans les slots ------------
            // On a tous les slots de la semaine actuel qui viennent d'être créés
            // Il faut boucler avec les profs (ordered) puis les cours à placer (ordered)
            // Boucler sur les slots de la semaine qui sont possible (verif prof et groupe)
            // Vérifier si le slot est pas dans un moment banalisé aussi, puis rank tous les placements de cours possible
            // Choisir aléatoire parmi x pourcent des meilleurs un placement
            // Une fois les slots de la semaine remplis, on améliore l'organisation
            // Il faut pour chaque slots essayer de replacer ses cours autre part dans la semaine et voir si le score de la semaine augmente
            // (Si un slot ou le score augmente est déjà pris, alors on essaie de déplacer celui là aussi mais on dit que le slot est désormais figé
            // C'est à dire que la prochaine itération d'amélioration de week, ne pourra pas changer ce slot
            // L'amélioration devrait être ordered avec les Slots allant de score le moins bon au meilleur (pour éviter de bouger les bons scores)

            // PB : Dans ce cas de relocalisation, si on remplace un slot occupé :
            // Est ce que c'est possible de re-placer le second slot (disponibilités) ? Oui, Est ce que c'est un meilleur score de semaine ? Oui
            // Alors on remplace le slot (ou les slots si plusieurs ont dû bouger) et on le(s) fige
            // Timeout si impossible ou score de semaine moins bien, ce après quoi : re-tentative d'up le score grâce au mouvement du slot (autre part parmi les meilleurs choix) x fois
            // Si aucune amélioration possible pour ce slot (score week inférieur ou impossibles disponibilités) ou score de nouveau placement inférieur,
            // Alors on ne fige que le slot qu'on peut pas améliorer, le rest des lots testés ne doivent pas être figé si impossible ou score week inférieur.

            // Dans le cas ou le slot nouveau meilleur slot est libre, alors on déplace et on fige le nouveau slot

            // Le calcul du Weight doit être optimisé car il sera souvent appelé pour tester l'amélioration du planning !
            // Les poids se calcul via la semaine

        }



        // DEBUG
        slotsStorage.forEach((week, daySlots) -> {
            System.out.print("Semaine " + week);
            daySlots.forEach((day, slot) -> {
                System.out.print(" Jour " + day + " : \n");
                System.out.println(slot);
            });
        });
    }

    static boolean isDateAvailable(
            LocalDateTime jour,
            List<MomentBanalise> momentBanalises){
        return false;
    }

    static boolean isExcludeDay(
            LocalDate actualDate,
            List<DayOfWeek> excludeDays
            ){
        return excludeDays.contains(actualDate.getDayOfWeek());
    }

    record Semestre(
            LocalDate debut,
            LocalDate fin,
            long dureeJours
    ) {
        Semestre (LocalDate debut, LocalDate fin) {
            this(debut, fin, ChronoUnit.DAYS.between(debut, fin) + 1);
        }
    }

    record PlanningPeriodMinutes(
            /**
             * Minutes
             */
            int debut,
            /**
             * Minutes
             */
            int fin
    ) {}

    record MomentBanalise (
            String nom,
            LocalDateTime debut,
            LocalDateTime fin
    ) {}

    static class Slot {
        /**
         * Minutes
         */
        int debut;
        /**
         * Minutes
         */
        int fin;
        List<Cours> usedBy = new ArrayList<Cours>();
        boolean isBlocked = false;

        Slot (int debut, int fin, boolean isBlocked) {
            this.debut = debut;
            this.fin = fin;
            this.isBlocked = isBlocked;
        }

        int getDebut(){
            return debut;
        }

        int getFin(){
            return fin;
        }

        boolean isBlocked(){
            return isBlocked;
        }

        void add(Cours cours) {
            usedBy.add(cours);
        }

        void addList(List<Cours> cours){
            usedBy.addAll(cours);
        }

        void setIsBlocked(boolean isBlocked) {
            this.isBlocked = isBlocked;
        }

        @Override
        public String toString() {
            // Re-écrire cette méthode
            return String.format(
                    "Slot [%dh%02d - %dh%02d] isBlocked = " + isBlocked + " | \n" + "---Cours : " + usedBy + "\n",
                    (int) (this.getDebut() / 60),
                    (int) (this.getDebut() % 60),
                    (int) (this.getFin() / 60),
                    (int) (this.getFin() % 60)
            );
        }
    }

    // MIN -10, 0 MAX
    // Chaque placement part avec 100 pts
    class WeightConfig {
            int placementTrou = 0;
            int placementFinTard = 0;
            int placementMatin = 0;
            int placementDebutJour = 0;
            int placementReference = 0;
            int repetitionCoursDansJournee = 0;

            int calcScore(Cours cours, List<Slot> jour){
                int score = 100;
                if (cours != null && !jour.isEmpty()){
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

            void setPlacementReference(int placementReference){
                this.placementReference = placementReference;
            }

    }

    record Cours(
            LocalDateTime heureDebutCours,
            LocalDateTime heureFinCours,
            TypeCours typeCours,
            int idComposante,
            int idProf,
            List<SousGroupe> participants
    ){}

    record SousGroupe (
            String nomSousGroupe,
            int nbEtuSousGroupe
    ){}

    record Professeurs (
            String nomProf,
            String prenomProf,
            boolean intervenantExterieur,
            List<Jours> jours
    ){}

    record Jours (
        int jourSemaine,
        List<Disponibilite> disponibilites
    ){}

    record Disponibilite (
            int heureDebutDispo,
            int heureFinDispo
    ){}

}
