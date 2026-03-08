/*
 * Copyright (c) 2026 Ademi Musa. Tous droits réservés.
 * Projet : univTime - Logiciel de gestion d'emplois du temps.
 *
 * Ce code source et l'algorithme associé sont la propriété exclusive de l'auteur.
 * Toute reproduction, modification ou distribution non autorisée, par quelque moyen que ce soit, est strictement interdite.
 *
 * Ce fichier fait partie du projet univTime, concédé sous licence d'usage
 * restreinte à l'Université de Tours, 37000, en France.
 */

package com.univtime.informatique.algorithme;

import com.univtime.informatique.dto.coursDto.CoursDto;

import java.util.ArrayList;
import java.util.List;

public class Slot {
    /**
     * Minutes
     */
    private Integer debut;
    /**
     * Minutes
     */
    private Integer fin;
    private List<CoursDto> usedBy = new ArrayList<CoursDto>();
    private Double score;

    Slot(Integer debut, Integer fin) {
        this.debut = debut;
        this.fin = fin;
        this.score = 1.0;
    }

    public Slot(Integer debut,
                Integer fin,
                List<CoursDto> usedBy) {
        this.debut = debut;
        this.fin = fin;
        this.usedBy = usedBy;
        this.score = 1.0;
    }

    Slot(Integer debut,
         Integer fin,
         List<CoursDto> usedBy,
         Double score) {
        this.debut = debut;
        this.fin = fin;
        this.usedBy = usedBy;
        this.score = score;
    }

    public List<CoursDto> getUsedBy() {
        return usedBy;
    }

    public void setUsedBy(List<CoursDto> usedBy) {
        this.usedBy = usedBy;
    }

    public void addUsedBy(CoursDto cours){
        this.usedBy.add(cours);
    }

    public void removeUsedBy(CoursDto cours){
        this.usedBy.remove(cours);
    }

    public Integer getFin() {
        return fin;
    }

    public void setFin(Integer fin) {
        this.fin = fin;
    }

    public Integer getDebut() {
        return debut;
    }

    public void setDebut(Integer debut) {
        this.debut = debut;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    // DEBUG
    @Override
    public String toString() {
        // Re-écrire cette méthode
        return String.format(
                "Slot [%dh%02d - %dh%02d] | \n" + "---Cours : " + usedBy + "\n",
                this.getDebut() / 60,
                this.getDebut() % 60,
                this.getFin() / 60,
                this.getFin() % 60
        );
    }
}
