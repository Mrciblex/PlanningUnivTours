package com.univtime.informatique.dtos;

public class DisponibiliteDto {
    private int idDispo;
    private int heureDebutDispo;
    private int heureFinDispo;
    private int idJour;

    // Getters et Setters
    public int getIdDispo() {
        return idDispo;
    }

    public void setIdDispo(int idDispo) {
        this.idDispo = idDispo;
    }

    public int getHeureDebutDispo() {
        return heureDebutDispo;
    }

    public void setHeureDebutDispo(int heureDebutDispo) {
        this.heureDebutDispo = heureDebutDispo;
    }

    public int getHeureFinDispo() {
        return heureFinDispo;
    }

    public void setHeureFinDispo(int heureFinDispo) {
        this.heureFinDispo = heureFinDispo;
    }

    public int getIdJour() {
        return idJour;
    }

    public void setIdJour(int idJour) {
        this.idJour = idJour;
    }
}

