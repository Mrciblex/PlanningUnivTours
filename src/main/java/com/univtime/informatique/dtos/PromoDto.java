package com.univtime.informatique.dtos;

public class PromoDto {
    private int idPromo;
    private String nomPromo;
    private int anneePromo;
    private int nbEtuPromo;

    // Getters et Setters
    public int getIdPromo() {
        return idPromo;
    }

    public void setIdPromo(int idPromo) {
        this.idPromo = idPromo;
    }

    public String getNomPromo() {
        return nomPromo;
    }

    public void setNomPromo(String nomPromo) {
        this.nomPromo = nomPromo;
    }

    public int getAnneePromo() {
        return anneePromo;
    }

    public void setAnneePromo(int anneePromo) {
        this.anneePromo = anneePromo;
    }

    public int getNbEtuPromo() {
        return nbEtuPromo;
    }

    public void setNbEtuPromo(int nbEtuPromo) {
        this.nbEtuPromo = nbEtuPromo;
    }
}
