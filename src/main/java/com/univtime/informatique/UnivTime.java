package com.univtime.informatique;

import com.univtime.informatique.algorithme.GenerationAlgorithme;
import com.univtime.informatique.constants.TypeCours;
import com.univtime.informatique.helpers.PlanningPeriodMinutes;
import com.univtime.informatique.helpers.Semestre;
import com.univtime.informatique.pojos.ComposantePojo;
import com.univtime.informatique.pojos.ProfesseurPojo;
import com.univtime.informatique.pojos.SousGroupePojo;
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
    static void main(String[] args) {
        //SpringApplication.run(UnivTime.class, args);
        System.out.println("Application lancé..");
        System.out.println("----------------- TEST ALGO -----------------");





        List<CM> cm = new ArrayList<>();
        HashMap<Integer, Integer> repartitionSemaineCM = new HashMap<>();
        repartitionSemaineCM.put(1, 2); // Première semaine contient 2 fois x composition(prof, composante, promo)
        cm.add(new CM(profs.get(1), composantes.get(1), promo, repartitionSemaineCM)); // CM - HELENE BROUARD - ADMIN BD - G1A,G1B,G2A,G2B - S1 x2

        List<TP> td = new ArrayList<>();

        List<TP> tp = new ArrayList<>();
        HashMap<Integer, Integer> repartitionSemaineTP = new HashMap<>();
        repartitionSemaineTP.put(1, 1); // Première semaine contient 2 fois x composition(prof, composante, promo)
        tp.add(new TP(profs.get(1), composantes.get(1), List.of(promo.get(0)), repartitionSemaineTP)); // TP - HELENE BROUARD - ADMIN BD - G1A - S1 x1
        tp.add(new TP(profs.get(1), composantes.get(1), List.of(promo.get(2)), repartitionSemaineTP)); // TP - HELENE BROUARD - ADMIN BD - G2A - S1 x1

        //System.out.println(cm);
        //System.out.println(td);
        //System.out.println(tp);

        /**
         * Boucle sur tout les jours du semestre
         */
        long nbSemaine = ChronoUnit.WEEKS.between(firstWeek, lastWeek) + 1;
        Map<Integer, Map<Integer, List<Slot>>> slotsStorage = new HashMap<>();

        for (int weekOffset = 0; weekOffset < nbSemaine; weekOffset++) {
            // Début et fin de la semaine du calendrier
            LocalDate weekStart = firstWeek.plusWeeks(weekOffset);
            LocalDate weekEnd = weekStart.plusDays(6);

            // Ajuster pour la première semaine partielle
            if (weekStart.isBefore(semestre.debut())) {
                weekStart = semestre.debut();
            }

            // Ajuster pour la dernière semaine partielle
            if (weekEnd.isAfter(semestre.fin())) {
                weekEnd = semestre.fin();
            }

            int currentWeek = weekOffset + 1;

            Map<Integer, List<Slot>> daySlots = new HashMap<>();
            for (int dayOffset = weekStart.getDayOfWeek().getValue() - 1; dayOffset < weekEnd.getDayOfWeek().getValue(); dayOffset++) {
                LocalDate actualDay = semestre.debut().plusWeeks(weekOffset).plusDays(dayOffset);
                boolean isExclude = isExcludeDay(actualDay, excludedDays);

                if (!isExclude) {
                    /**
                     * Création des slots de la semaine
                     */
                    List<Slot> slotOfDay = new ArrayList<>();
                    for (PlanningPeriodMinutes period : planningPossiblePeriod) {
                        for (int start = period.debut(); start < period.fin(); start = start + slotStep) {
                            boolean isBlocked = false;
                            Slot slot = new Slot(start, start + slotStep, isBlocked);
                            slotOfDay.add(slot);
                        }
                    }
                    daySlots.put(dayOffset + 1, slotOfDay);
                }
            }
            slotsStorage.put(currentWeek, daySlots);

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

            /**
             * Professeurs ordered pour affiner les résultats.
             * Ratio volumeHoraire pour la semaine / disponibilité
             * Si le ratio est > 1 alors trop de cours pour pas assez de dispo (Que faire en cas d'impossibilité -> warning des placements impossible + différence d'heures voulu à heures réel par composante + ne pas placer le cours)
             * Si le ratio est <= 1 alors placement possible mais plus c'est proche de 1, plus il y a une tension pour les placements
             */
            // Calcul de la tension (ratio volume/dispo) pour ordonner les professeurs

            profs.sort((p1, p2) -> {
                double ratio1 = getProfTension(currentWeek, p1, cm, List.of(), tp);
                double ratio2 = getProfTension(currentWeek, p2, cm, List.of(), tp);
                return Double.compare(ratio2, ratio1); // Plus le ratio est élevé, plus il est prioritaire
            });

            System.out.println("COURS QUI DOIVENT ÊTRE PLACER ------------");
            System.out.println("SEMAINE " + currentWeek + " : ");
            for (Professeurs prof : profs) {
                cm.stream()
                        .filter(cours -> cours.prof().equals(prof))
                        .filter(cours -> cours.repartitionSemaine().getOrDefault(currentWeek, 0) > 0)
                        .forEach(cours -> {
                            StringBuilder cmGroupes = new StringBuilder();
                            for(int sg = 0; sg < cours.participants.size(); sg++){
                                if (sg == cours.participants.size() - 1){
                                    cmGroupes.append(cours.participants.get(sg).nomSousGroupe());
                                }else{
                                    cmGroupes.append(cours.participants.get(sg).nomSousGroupe()).append(",");
                                }
                            }
                            System.out.println("CM " + cmGroupes + " | " + cours.comp().nom() + " x" + cours.repartitionSemaine().get(currentWeek) + " | " + cours.prof().prenomProf + " " + cours.prof().nomProf);

                            // Créer une fonction qui prend en paramètre : la list actuelle du planning de la semaine (slots)
                            // Le cours à ajouter,
                            // Optimisation à faire : on ne recalcul réellement que le score du jour qui change à chaque fois ! Et non de la semaine entière tout le temps
                            // Après on re-fait juste la moyenne

                            calcBestPlacement(slotsStorage.get(currentWeek), cours);
                        });

                td.stream()
                        .filter(cours -> cours.prof().equals(prof))
                        .filter(cours -> cours.repartitionSemaine().getOrDefault(currentWeek, 0) > 0)
                        .forEach(cours -> {
                            StringBuilder tdGroupes = new StringBuilder();
                            for(int sg = 0; sg < cours.participants.size(); sg++){
                                if (sg == cours.participants.size() - 1){
                                    tdGroupes.append(cours.participants.get(sg).nomSousGroupe());
                                }else{
                                    tdGroupes.append(cours.participants.get(sg).nomSousGroupe()).append(",");
                                }
                            }
                            System.out.println("TD " + tdGroupes + " | " + cours.comp().nom() + " x" + cours.repartitionSemaine().get(currentWeek) + " | " + cours.prof().prenomProf + " " + cours.prof().nomProf);
                        });

                tp.stream()
                        .filter(cours -> cours.prof().equals(prof))
                        .filter(cours -> cours.repartitionSemaine().getOrDefault(currentWeek, 0) > 0)
                        .forEach(cours -> {
                            StringBuilder tpGroupes = new StringBuilder();
                            for(int sg = 0; sg < cours.participants.size(); sg++){
                                if (sg == cours.participants.size() - 1){
                                    tpGroupes.append(cours.participants.get(sg).nomSousGroupe());
                                }else{
                                    tpGroupes.append(cours.participants.get(sg).nomSousGroupe()).append(",");
                                }
                            }
                            System.out.println("TP " + tpGroupes + " | " + cours.comp().nom() + " x" + cours.repartitionSemaine().get(currentWeek) + " | " + cours.prof().prenomProf + " " + cours.prof().nomProf);
                        });
            }
        }


        // DEBUG
        /*
        slotsStorage.forEach((week, daySlots) -> {
            System.out.print("Semaine " + week);
            daySlots.forEach((day, slot) -> {
                System.out.print(" Jour " + day + " : \n");
                System.out.println(slot);
            });
        });
        */
    }

    static List<Slot> calcBestPlacement(Map<Integer, List<Slot>> slotsStorageWeek, CM cours){
        List<Slot> bestPlacement = new ArrayList<>();
        for (int i = 0; i < slotsStorageWeek.size(); i++){
            List<Slot> slots = slotsStorageWeek.get(i); // Slot of the day

        }
        return null;
    }









}
