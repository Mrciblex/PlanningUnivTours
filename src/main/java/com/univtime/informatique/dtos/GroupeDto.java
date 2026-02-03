package com.univtime.informatique.dtos;

public class GroupeDto {
    private int idGroupe;
    private String nomGroupe;
    private int nbEtuGroupe;
    private int idPromo;

    // Getters et Setters
    public int getIdGroupe() {
        return idGroupe;
    }

    public void setIdGroupe(int idGroupe) {
        this.idGroupe = idGroupe;
    }

    public String getNomGroupe() {
        return nomGroupe;
    }

    public void setNomGroupe(String nomGroupe) {
        this.nomGroupe = nomGroupe;
    }

    public int getNbEtuGroupe() {
        return nbEtuGroupe;
    }

    public void setNbEtuGroupe(int nbEtuGroupe) {
        this.nbEtuGroupe = nbEtuGroupe;
    }

    public int getIdPromo() {
        return idPromo;
    }

    public void setIdPromo(int idPromo) {
        this.idPromo = idPromo;
    }
}
