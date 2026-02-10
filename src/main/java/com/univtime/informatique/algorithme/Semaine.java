package com.univtime.informatique.algorithme;

import java.util.ArrayList;
import java.util.List;

public class Semaine {
    private Integer numSemaine;
    private List<Jour> jours;
    private Double score;

    public Semaine(
            Integer numSemaine
            ){
        this.numSemaine = numSemaine;
        this.jours = new ArrayList<Jour>();
        this.score = 0.0;
    }

    public Semaine(
            Integer numSemaine,
            List<Jour> jours){
        this.numSemaine = numSemaine;
        this.jours = jours;
        this.score = jours.stream().mapToDouble(Jour::getScore).average().orElse(0.0);
    }

    public Semaine(Integer numSemaine,
                   List<Jour> jours,
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

    public List<Jour> getJours() {
        return jours;
    }

    public void setJours(List<Jour> jours) {
        this.score = jours.stream().mapToDouble(Jour::getScore).average().orElse(0.0);
        this.jours = jours;
    }

    public Double getScore() {
        return score;
    }
}
