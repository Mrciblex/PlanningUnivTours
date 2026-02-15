package com.univtime.informatique.algorithme;

import java.time.DayOfWeek;
import java.util.List;

public class Jour {
    private DayOfWeek numJour;
    private List<Slot> slots;
    private double score;

    public Jour(DayOfWeek numJour,
                List<Slot> slots) {
        this.numJour = numJour;
        this.slots = slots;
        this.score = slots.stream().mapToDouble(Slot::getScore).average().orElse(0.0);
    }

    public Jour(Integer numJour,
                List<Slot> slots) {
        this.numJour = DayOfWeek.of(numJour);
        this.slots = slots;
        this.score = slots.stream().mapToDouble(Slot::getScore).average().orElse(0.0);
    }

    public double getScore(){
        this.score = slots.stream().mapToDouble(Slot::getScore).average().orElse(0.0);
        return score;
    }

    public DayOfWeek getNumJour() {
        return numJour;
    }

    public void setNumJour(DayOfWeek numJour) {
        this.numJour = numJour;
    }

    public List<Slot> getSlots() {
        return slots;
    }

    public void setSlots(List<Slot> slots) {
        this.slots = slots;
    }
}
