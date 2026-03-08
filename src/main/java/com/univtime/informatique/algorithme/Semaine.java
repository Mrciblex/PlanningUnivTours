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

import java.util.HashMap;

public class Semaine {
    private Integer numSemaine;
    private HashMap<Integer, Jour> jours;
    private Double score;

    public Semaine(
            Integer numSemaine
            ){
        this.numSemaine = numSemaine;
        this.jours = new HashMap<>();
        this.score = 1.0;
    }

    public Semaine(
            Integer numSemaine,
            HashMap<Integer, Jour> jours){
        this.numSemaine = numSemaine;
        this.jours = jours;
        this.score = jours.values().stream().mapToDouble(Jour::getScore).average().orElse(0.0);
    }

    public Semaine(Integer numSemaine,
                   HashMap<Integer, Jour> jours,
                   Double score) {
        this.numSemaine = numSemaine;
        this.jours = jours;
        this.score = score;
    }

    public Integer getNumSemaine() {
        return numSemaine;
    }

    public void setNumSemaine(Integer numSemaine) {
        this.numSemaine = numSemaine;
    }

    public HashMap<Integer, Jour> getJours() {
        return jours;
    }

    public void setJours(HashMap<Integer, Jour> jours) {
        this.score = jours.values().stream().mapToDouble(Jour::getScore).average().orElse(0.0);
        this.jours = jours;
    }

    public Double getScore() {
        return score;
    }
}
