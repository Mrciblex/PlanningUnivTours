package com.univtime.informatique.algorithme;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
