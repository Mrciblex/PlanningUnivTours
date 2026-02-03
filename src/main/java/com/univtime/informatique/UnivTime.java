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
    static void main(String[] args) {
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

        List<PlanningPeriodMinutes> planningPossiblePeriod = new ArrayList<PlanningPeriodMinutes>();
        planningPossiblePeriod.add(new PlanningPeriodMinutes(8 * 60, 10 * 60)); // 8h00 à 10h00
        planningPossiblePeriod.add(new PlanningPeriodMinutes(10 * 60 + 15, 12 * 60 + 15)); // 10h15 à 12h15
        planningPossiblePeriod.add(new PlanningPeriodMinutes(13 * 60 + 30, 15 * 60 + 30)); // 13h30 à 15h30
        planningPossiblePeriod.add(new PlanningPeriodMinutes(15 * 60 + 45, 17 * 60 + 45)); // 15h45 à 17h45
        planningPossiblePeriod.add(new PlanningPeriodMinutes(18 * 60, 20 * 60)); // 18h00 à 20h00

        HashMap<Integer, HashMap<Integer, List<Slot>>> slotsPerDayPerWeek = new HashMap<>();

        List<Professeurs> profs = new ArrayList<>();
        // DISPO QUE LE MATIN (8h-12h15)
        profs.add(new Professeurs(
                        "Brouard",
                        "Thierry",
                        false,
                        List.of(
                                new Jours(1, List.of(new Disponibilite(8 * 60, 12 * 60 + 15))),
                                new Jours(2, List.of(new Disponibilite(8 * 60, 12 * 60 + 15))),
                                new Jours(3, List.of(new Disponibilite(8 * 60, 12 * 60 + 15))),
                                new Jours(4, List.of(new Disponibilite(8 * 60, 12 * 60 + 15))),
                                new Jours(5, List.of(new Disponibilite(8 * 60, 12 * 60 + 15)))
                        )
                )
        );
        // DISPO TOUT LE TEMPS
        profs.add(new Professeurs(
                        "Brouard",
                        "Helene",
                        false,
                        List.of(
                                new Jours(1, List.of(new Disponibilite(8 * 60, 20 * 60))),
                                new Jours(2, List.of(new Disponibilite(8 * 60, 20 * 60))),
                                new Jours(3, List.of(new Disponibilite(8 * 60, 20 * 60))),
                                new Jours(4, List.of(new Disponibilite(8 * 60, 20 * 60))),
                                new Jours(5, List.of(new Disponibilite(8 * 60, 20 * 60)))
                        )
                )
        );
        // DISPO QUE LE MATIN (10h30-12h15) DONC MOINS DE 2H
        profs.add(new Professeurs(
                        "Desport",
                        "Pierre",
                        false,
                        List.of(
                                new Jours(1, List.of(new Disponibilite(10 * 60 + 30, 12 * 60 + 15))),
                                new Jours(2, List.of(new Disponibilite(10 * 60 + 30, 12 * 60 + 15))),
                                new Jours(3, List.of(new Disponibilite(10 * 60 + 30, 12 * 60 + 15))),
                                new Jours(4, List.of(new Disponibilite(10 * 60 + 30, 12 * 60 + 15))),
                                new Jours(5, List.of(new Disponibilite(10 * 60 + 30, 12 * 60 + 15)))
                        )
                )
        );
        // DISPO DECOUPER
        profs.add(new Professeurs(
                        "Cabet",
                        "Aurore",
                        false,
                        List.of(
                                new Jours(1, List.of(new Disponibilite(8 * 60, 10 * 60 + 15), new Disponibilite(15 * 60 + 45, 17 * 60 + 45))),
                                new Jours(2, List.of(new Disponibilite(8 * 60, 10 * 60 + 15), new Disponibilite(15 * 60 + 45, 17 * 60 + 45))),
                                new Jours(3, List.of(new Disponibilite(8 * 60, 10 * 60 + 15), new Disponibilite(15 * 60 + 45, 17 * 60 + 45))),
                                new Jours(4, List.of(new Disponibilite(8 * 60, 10 * 60 + 15), new Disponibilite(15 * 60 + 45, 17 * 60 + 45))),
                                new Jours(5, List.of(new Disponibilite(8 * 60, 10 * 60 + 15), new Disponibilite(15 * 60 + 45, 17 * 60 + 45)))
                        )
                )
        );

        List<SousGroupe> promo = new ArrayList<>();
        promo.add(new SousGroupe("G1A", 10));
        promo.add(new SousGroupe("G1B", 10));
        promo.add(new SousGroupe("G2A", 10));
        promo.add(new SousGroupe("G2B", 10));

        List<Composante> composantes = new ArrayList<>();
        composantes.add(new Composante("Maths", 2 * 60, 2 * 60, 2 * 60));
        composantes.add(new Composante("Admin BD", 2 * 60, 2 * 60, 2 * 60));
        composantes.add(new Composante("Securite Logicielle", 2 * 60, 2 * 60, 2 * 60));

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

    static int getDisponibiliteMinutes(Professeurs p) {
        return p.jours().stream()
                .flatMap(jour -> jour.disponibilites().stream())
                .mapToInt(dispo -> dispo.heureFinDispo() - dispo.heureDebutDispo())
                .sum();
    }

    static int getChargeTotaleMinutes(int weekOffset, Professeurs p, List<CM> cms, List<TD> tds, List<TP> tps) {
        // Somme du nombre total de cours à placer pour un prof * la durée d'un bloc (en minutes), pour la semaine donnée
        int chargeCM = cms.stream()
                .filter(c -> c.prof().equals(p))
                .mapToInt(c -> c.repartitionSemaine().getOrDefault(weekOffset, 0) * c.comp().blocCM())
                .sum();

        int chargeTD = tds.stream()
                .filter(c -> c.prof().equals(p))
                .mapToInt(c -> c.repartitionSemaine().getOrDefault(weekOffset, 0) * c.comp().blocTD())
                .sum();

        int chargeTP = tps.stream()
                .filter(c -> c.prof().equals(p))
                .mapToInt(c -> c.repartitionSemaine().getOrDefault(weekOffset, 0) * c.comp().blocTP())
                .sum();

        return chargeCM + chargeTD + chargeTP;
    }

    static double getProfTension(int weekOffset, Professeurs p, List<CM> cms, List<TD> tds, List<TP> tps){
        int charge = getChargeTotaleMinutes(weekOffset, p, cms, tds, tps);
        int dispo = getDisponibiliteMinutes(p);

        if (dispo == 0) return Double.MAX_VALUE; // Si aucune disponibilité, priorité maximale (juste pour éviter les bugs, ça n'a pas d'impact)
        return (double) charge / dispo;
    }

    static boolean isDateAvailable(
            LocalDateTime jour,
            List<MomentBanalise> momentBanalises) {
        return false;
    }

    static boolean isExcludeDay(
            LocalDate actualDate,
            List<DayOfWeek> excludeDays
    ) {
        return excludeDays.contains(actualDate.getDayOfWeek());
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
    ) {
    }

    record MomentBanalise(
            String nom,
            LocalDateTime debut,
            LocalDateTime fin
    ) {
    }

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

        Slot(int debut, int fin, boolean isBlocked) {
            this.debut = debut;
            this.fin = fin;
            this.isBlocked = isBlocked;
        }

        int getDebut() {
            return debut;
        }

        int getFin() {
            return fin;
        }

        boolean isBlocked() {
            return isBlocked;
        }

        void add(Cours cours) {
            usedBy.add(cours);
        }

        void addList(List<Cours> cours) {
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
                    this.getDebut() / 60,
                    this.getDebut() % 60,
                    this.getFin() / 60,
                    this.getFin() % 60
            );
        }
    }

    // MIN -10, 0 MAX
    // Chaque placement part avec 100 pts
    static class WeightConfig {
        /**
         * Si le slot créer un trou
         */
        int placementTrou = 0;

        /**
         * Si le slot est utilisé et est tard (coef plus c'est tard = plus score fait mal)
         */
        int placementFinTard = 0;

        /**
         * Si le slot est utilisé et n'est le matin avant 12h15
         */
        int placementMatin = 0;

        /**
         * Si le slot est utilisé et n'est pas à 8h
         */
        int placementDebutJour = 0;

        /**
         * Si le slot est utilisé et ne correspond pas au cours de la semaine 1
         * (attention, s'il y a 1 cours S1 puis 2 cours S2 dans le même slot, si le cours S1 est quand même là il ne faut pas de pénalité)
         */
        int placementReference = 0;

        /**
         * Si le slot est utilisé et le cours est à une heure fixe (8h, 10h15, 13h30, 15h45, 18h)
         */
        int placementArrondit = 0;

        /**
         * Si les slots ont le même cours mais avec une séparation entre les slots (donc 2 fois le cours dans la journée)
         */
        int repetitionCoursDansJournee = 0;

        int calcScore(Cours cours, List<Slot> jour) {
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

    record Cours(
            LocalDateTime heureDebutCours,
            LocalDateTime heureFinCours,
            TypeCours typeCours,
            Composante composante,
            Professeurs prof,
            List<SousGroupe> participants,
            int Score
    ) {
    }

    record SousGroupe(
            String nomSousGroupe,
            int nbEtuSousGroupe
    ) {
    }

    record Professeurs(
            String nomProf,
            String prenomProf,
            boolean intervenantExterieur,
            List<Jours> jours
    ) {
    }

    record Jours(
            int jourSemaine,
            List<Disponibilite> disponibilites
    ) {
    }

    record Disponibilite(
            int heureDebutDispo,
            int heureFinDispo
    ) {
    }

    record Composante(
            String nom,
            int blocCM,
            int blocTD,
            int blocTP
    ){}

    record TD(
            Professeurs prof,
            Composante comp,
            List<SousGroupe> participants,
            HashMap<Integer, Integer> repartitionSemaine
    ) {}

    record TP(
            Professeurs prof,
            Composante comp,
            List<SousGroupe> participants,
            HashMap<Integer, Integer> repartitionSemaine
    ) {}

    record CM(
            Professeurs prof,
            Composante comp,
            List<SousGroupe> participants,
            HashMap<Integer, Integer> repartitionSemaine
    ) {}

}
