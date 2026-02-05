package com.univtime.informatique.algorithme;

import com.univtime.informatique.dtos.professeurDto.ProfesseurDto;
import com.univtime.informatique.helpers.MomentBanalise;
import com.univtime.informatique.helpers.PlanningPeriodMinutes;
import com.univtime.informatique.helpers.Semestre;
import com.univtime.informatique.pojos.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GenerationAlgorithme {
    private static int slotStep = 15;
    private static List<DayOfWeek> excludedDays = new ArrayList<>();
    private static List<PlanningPeriodMinutes> planningPossiblePeriod = new ArrayList<>();

    public static List<PlanningPeriodMinutes> getPlanningPossiblePeriod() {
        return planningPossiblePeriod;
    }

    public static void setPlanningPossiblePeriod(List<PlanningPeriodMinutes> planningPossiblePeriod) {
        GenerationAlgorithme.planningPossiblePeriod = planningPossiblePeriod;
    }

    public static List<DayOfWeek> getExcludedDays() {
        return excludedDays;
    }

    public static void setExcludedDays(List<DayOfWeek> excludedDays) {
        GenerationAlgorithme.excludedDays = excludedDays;
    }

    public static int getSlotStep() {
        return slotStep;
    }

    public static void setSlotStep(int slotStep) {
        GenerationAlgorithme.slotStep = slotStep;
    }

    private static boolean isExcludeDay(LocalDate actualDate, List<DayOfWeek> excludeDays) {
        return excludeDays.contains(actualDate.getDayOfWeek());
    }

    private static boolean isDateAvailable(LocalDateTime jour, List<MomentBanalise> momentBanalises) {
        return false;
    }

    private static int getChargeTotaleMinutes(int weekOffset, ProfesseurDto p, List<CM> cms, List<TD> tds, List<TP> tps) {
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

    private static int getDisponibiliteMinutes(ProfesseurPojo p) {
        return p.jours().stream()
                .flatMap(jour -> jour.disponibilites().stream())
                .mapToInt(dispo -> dispo.heureFinDispo() - dispo.heureDebutDispo())
                .sum();
    }

    private static double getProfTension(int weekOffset, ProfesseurPojo p, List<CM> cms, List<TD> tds, List<TP> tps){
        int charge = getChargeTotaleMinutes(weekOffset, p, cms, tds, tps);
        int dispo = getDisponibiliteMinutes(p);

        if (dispo == 0) return Double.MAX_VALUE; // Si aucune disponibilité, priorité maximale (juste pour éviter les bugs, ça n'a pas d'impact)
        return (double) charge / dispo;
    }

    private static List<Slot> generateEmptySlotOfDay(){
        return null;
    }

    private static List<PlanningPeriodMinutes> generatePlanningPossiblePeriod(){
        List<PlanningPeriodMinutes> planningPossiblePeriod = new ArrayList<PlanningPeriodMinutes>();
        planningPossiblePeriod.add(new PlanningPeriodMinutes(8 * 60, 10 * 60)); // 8h00 à 10h00
        planningPossiblePeriod.add(new PlanningPeriodMinutes(10 * 60 + 15, 12 * 60 + 15)); // 10h15 à 12h15
        planningPossiblePeriod.add(new PlanningPeriodMinutes(13 * 60 + 30, 15 * 60 + 30)); // 13h30 à 15h30
        planningPossiblePeriod.add(new PlanningPeriodMinutes(15 * 60 + 45, 17 * 60 + 45)); // 15h45 à 17h45
        planningPossiblePeriod.add(new PlanningPeriodMinutes(18 * 60, 20 * 60)); // 18h00 à 20h00

        return planningPossiblePeriod;
    }

    private static List<DayOfWeek> generateExcludedDays(){
        List<DayOfWeek> excludedDays = new ArrayList<DayOfWeek>();
        excludedDays.add(DayOfWeek.SATURDAY); // Samedi
        excludedDays.add(DayOfWeek.SUNDAY); // Dimanche

        return excludedDays;
    }

    public static List<CoursPojo> generatePlanning(LocalDate debutSemestre, LocalDate finSemestre, Integer idPromo) {
        // INSERT TEST DATA
        /**
         * Paramètres globaux
         */
        // Hard coded (15 min)
        int slotStep = getSlotStep();

        // Dynamique (non lié à la BD -> saisie lors de la génération)
        Semestre semestre = new Semestre(debutSemestre, finSemestre);

        // Hard coded
        List<DayOfWeek> excludedDays = generateExcludedDays();

        // Hard coded
        List<PlanningPeriodMinutes> planningPossiblePeriod = generatePlanningPossiblePeriod();

        GenerationAlgorithme.setExcludedDays(excludedDays);
        GenerationAlgorithme.setPlanningPossiblePeriod(planningPossiblePeriod);

        /**
         * Paramètres dynamiques de la base de données
         */
        // Tout les sous groupes de la promo actuelle selectionner (paramètre idPromo)
        List<SousGroupePojo> promo = GenerationAlgorithme.testDataPromo();

        // Toutes les composantes concerné par cette promo
        List<ComposantePojo> composantes = testDataComposantes();

        // Tout les professeurs avec leurs jours et leurs disponibilités concerné par cette promo
        List<ProfesseurPojo> professeurs = GenerationAlgorithme.testDataProfesseurs();

        return null;
    }



    /**
     * Fonction pour générer les données issues de la base données de test
     */

    private static List<ProfesseurPojo> testDataProfesseurs(){
        List<ProfesseurPojo> profs = new ArrayList<>();
        // DISPO QUE LE MATIN (8h-12h15)
        profs.add(new ProfesseurPojo(
                        "Brouard",
                        "Thierry",
                        false,
                        List.of(
                                new JourPojo(1, List.of(new DisponibilitePojo(8 * 60, 12 * 60 + 15))),
                                new JourPojo(2, List.of(new DisponibilitePojo(8 * 60, 12 * 60 + 15))),
                                new JourPojo(3, List.of(new DisponibilitePojo(8 * 60, 12 * 60 + 15))),
                                new JourPojo(4, List.of(new DisponibilitePojo(8 * 60, 12 * 60 + 15))),
                                new JourPojo(5, List.of(new DisponibilitePojo(8 * 60, 12 * 60 + 15)))
                        )
                )
        );
        // DISPO TOUT LE TEMPS
        profs.add(new ProfesseurPojo(
                        "Brouard",
                        "Helene",
                        false,
                        List.of(
                                new JourPojo(1, List.of(new DisponibilitePojo(8 * 60, 20 * 60))),
                                new JourPojo(2, List.of(new DisponibilitePojo(8 * 60, 20 * 60))),
                                new JourPojo(3, List.of(new DisponibilitePojo(8 * 60, 20 * 60))),
                                new JourPojo(4, List.of(new DisponibilitePojo(8 * 60, 20 * 60))),
                                new JourPojo(5, List.of(new DisponibilitePojo(8 * 60, 20 * 60)))
                        )
                )
        );
        // DISPO QUE LE MATIN (10h30-12h15) DONC MOINS DE 2H
        profs.add(new ProfesseurPojo(
                        "Desport",
                        "Pierre",
                        false,
                        List.of(
                                new JourPojo(1, List.of(new DisponibilitePojo(10 * 60 + 30, 12 * 60 + 15))),
                                new JourPojo(2, List.of(new DisponibilitePojo(10 * 60 + 30, 12 * 60 + 15))),
                                new JourPojo(3, List.of(new DisponibilitePojo(10 * 60 + 30, 12 * 60 + 15))),
                                new JourPojo(4, List.of(new DisponibilitePojo(10 * 60 + 30, 12 * 60 + 15))),
                                new JourPojo(5, List.of(new DisponibilitePojo(10 * 60 + 30, 12 * 60 + 15)))
                        )
                )
        );
        // DISPO DECOUPER
        profs.add(new ProfesseurPojo(
                        "Cabet",
                        "Aurore",
                        false,
                        List.of(
                                new JourPojo(1, List.of(new DisponibilitePojo(8 * 60, 10 * 60 + 15), new DisponibilitePojo(15 * 60 + 45, 17 * 60 + 45))),
                                new JourPojo(2, List.of(new DisponibilitePojo(8 * 60, 10 * 60 + 15), new DisponibilitePojo(15 * 60 + 45, 17 * 60 + 45))),
                                new JourPojo(3, List.of(new DisponibilitePojo(8 * 60, 10 * 60 + 15), new DisponibilitePojo(15 * 60 + 45, 17 * 60 + 45))),
                                new JourPojo(4, List.of(new DisponibilitePojo(8 * 60, 10 * 60 + 15), new DisponibilitePojo(15 * 60 + 45, 17 * 60 + 45))),
                                new JourPojo(5, List.of(new DisponibilitePojo(8 * 60, 10 * 60 + 15), new DisponibilitePojo(15 * 60 + 45, 17 * 60 + 45)))
                        )
                )
        );
        return profs;
    }

    public static Semestre testDataSemestre(){
        return new Semestre(
                LocalDate.of(2025, 9, 1),
                LocalDate.of(2025, 9, 7)
        );
    }

    private static List<ComposantePojo> testDataComposantes(){
        List<ComposantePojo> composantes = new ArrayList<>();
        composantes.add(new ComposantePojo("Maths", 2 * 60, 2 * 60, 2 * 60));
        composantes.add(new ComposantePojo("Admin BD", 2 * 60, 2 * 60, 2 * 60));
        composantes.add(new ComposantePojo("Securite Logicielle", 2 * 60, 2 * 60, 2 * 60));

        return composantes;
    }

    private static List<SousGroupePojo> testDataPromo(){
        List<SousGroupePojo> promo = new ArrayList<>();
        promo.add(new SousGroupePojo("G1A", 10));
        promo.add(new SousGroupePojo("G1B", 10));
        promo.add(new SousGroupePojo("G2A", 10));
        promo.add(new SousGroupePojo("G2B", 10));

        return promo;
    }


}
