package com.univtime.informatique.dtos;

public class SousGroupeDto {
    private int idSousGroupe;
    private String nomSousGroupe;
    private int nbEtuSousGroupe;
    private int idGroupe;

    // Getters et Setters
    public int getIdSousGroupe() {
        return idSousGroupe;
    }

    public void setIdSousGroupe(int idSousGroupe) {
        this.idSousGroupe = idSousGroupe;
    }

    public String getNomSousGroupe() {
        return nomSousGroupe;
    }

    public void setNomSousGroupe(String nomSousGroupe) {
        this.nomSousGroupe = nomSousGroupe;
    }

    public int getNbEtuSousGroupe() {
        return nbEtuSousGroupe;
    }

    public void setNbEtuSousGroupe(int nbEtuSousGroupe) {
        this.nbEtuSousGroupe = nbEtuSousGroupe;
    }

    public int getIdGroupe() {
        return idGroupe;
    }

    public void setIdGroupe(int idGroupe) {
        this.idGroupe = idGroupe;
    }
}
