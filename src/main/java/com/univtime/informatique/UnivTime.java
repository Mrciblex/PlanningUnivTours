package com.univtime.informatique;

import com.univtime.informatique.constants.TypeCours;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class UnivTime {
    public static void main(String[] args) {
        //SpringApplication.run(UnivTime.class, args);
        System.out.println("Application lancé..");
        System.out.println("----------------- TEST ALGO -----------------");

        int dureeSlot = 15;
        List<Slot> slotsDisponible = new ArrayList<>();
        Semestre semestre = new Semestre(
                LocalDate.of(2025, 9, 1),
                LocalDate.of(2025, 9, 7)
        );



    }

    record MomentBanalise (
            String nom,
            LocalDateTime debut,
            LocalDateTime fin
    ) {}

    record Semestre(
            LocalDate debut,
            LocalDate fin
    ) {}

    record LimitePlanning(
            /**
             * Minutes
             */
            int debut,
            /**
             * Minutes
             */
            int fin
    ) {}

    static final class SlotManager {
        static List<Slot> blocked =  new ArrayList<Slot>();

        SlotManager() {}

        static void addBlocked(Slot slotBloque) {
            blocked.add(slotBloque);
        }

        static void addBlockedList(List<Slot> slotsBloques){
            blocked.addAll(slotsBloques);
        }
    }

    static final class MomentBanalises {
        static List<MomentBanalise> pauses =  new ArrayList<MomentBanalise>();

        MomentBanalises() {}

        static void addMomentBanalise(MomentBanalise momentBanalise) {
            pauses.add(momentBanalise);
        }

        static void addMomentBanaliseList(List<MomentBanalise> momentBanalises){
            pauses.addAll(momentBanalises);
        }
    }

    class Slot {
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

        Slot (int debut, int fin) {
            this.debut = debut;
            this.fin = fin;
            isBlocked = SlotManager.blocked.contains(this);
        }

        int getDebut(){
            return debut;
        }

        int getFin(){
            return fin;
        }

        void add(Cours cours) {
            usedBy.add(cours);
        }

        void addList(List<Cours> cours){
            usedBy.addAll(cours);
        }
    }

    // MIN -10, 0 MAX
    // Chaque placement part avec 100 pts
    static final class WeightConfig {
            int placementTrou = 0;
            int placementFinTard = 0;
            int placementMatin = 0;
            int placementDebutJour = 0;
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
