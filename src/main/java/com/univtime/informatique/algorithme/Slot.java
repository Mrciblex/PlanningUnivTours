package com.univtime.informatique.algorithme;

import com.univtime.informatique.dtos.coursDto.CoursDto;

import java.util.ArrayList;
import java.util.List;

public class Slot {
    /**
     * Minutes
     */
    private int debut;
    /**
     * Minutes
     */
    private int fin;
    private List<CoursDto> usedBy = new ArrayList<CoursDto>();
    private boolean isBlocked = false;

    Slot(int debut, int fin, boolean isBlocked) {
        this.debut = debut;
        this.fin = fin;
        this.isBlocked = isBlocked;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
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

    public int getFin() {
        return fin;
    }

    public void setFin(int fin) {
        this.fin = fin;
    }

    public int getDebut() {
        return debut;
    }

    public void setDebut(int debut) {
        this.debut = debut;
    }

    // DEBUG
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
