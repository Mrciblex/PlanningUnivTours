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
        this.score = 10.0;
    }

    Slot(Integer debut, Integer fin, List<CoursDto> usedBy, Double score) {
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
