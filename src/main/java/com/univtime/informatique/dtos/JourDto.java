package com.univtime.informatique.dtos;

public class JourDto {
    private int idJour;
    private int jourSemaine;
    private int idProf;

    // Getters et Setters
    public int getIdJour() {
        return idJour;
    }

    public void setIdJour(int idJour) {
        this.idJour = idJour;
    }

    public int getJourSemaine() {
        return jourSemaine;
    }

    public void setJourSemaine(int jourSemaine) {
        this.jourSemaine = jourSemaine;
    }

    public int getIdProf() {
        return idProf;
    }

    public void setIdProf(int idProf) {
        this.idProf = idProf;
    }
}
