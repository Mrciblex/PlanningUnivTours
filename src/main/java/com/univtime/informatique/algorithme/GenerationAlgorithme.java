package com.univtime.informatique.algorithme;

import com.univtime.informatique.dtos.CoursDto;
import com.univtime.informatique.dtos.ProfesseurDto;
import com.univtime.informatique.dtos.SousGroupeDto;
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
    private static Semestre semestre;
    private static List<DayOfWeek> excludedDays = new ArrayList<>();
    private static List<ProfesseurPojo> professeurPojos = new ArrayList<>();
    private static List<PlanningPeriodMinutes> planningPossiblePeriod = new ArrayList<>();
    private static List<SousGroupePojo> promo = new ArrayList<>();
    private static List<ComposantePojo> composantes = new ArrayList<>();


    public static List<ProfesseurPojo> getProfesseurPojos() {
        return professeurPojos;
    }

    public static void setProfesseurPojos(List<ProfesseurPojo> professeurPojos) {
        GenerationAlgorithme.professeurPojos = professeurPojos;
    }

    public static List<PlanningPeriodMinutes> getPlanningPossiblePeriod() {
        return planningPossiblePeriod;
    }

    public static void setPlanningPossiblePeriod(List<PlanningPeriodMinutes> planningPossiblePeriod) {
        GenerationAlgorithme.planningPossiblePeriod = planningPossiblePeriod;
    }

    public static Semestre getSemestre() {
        return semestre;
    }

    public static void setSemestre(Semestre semestre) {
        GenerationAlgorithme.semestre = semestre;
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

    public static List<CoursPojo> generatePlanning(){
        // INSERT TEST DATA
        return null;
    }

    private static List<ProfesseurPojo> testDataProfesseurs(){
        return null;
    }

    private static Semestre testDataSemestre(){
        return null;
    }

    private static List<PlanningPeriodMinutes> testDataPlanningPossiblePeriod(){
        return null;
    }




}
