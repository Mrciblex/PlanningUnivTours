package com.univtime.informatique.constants;

public enum TypeBesoin {
    SALLE_INFORMATIQUE("salle_informatique"),
    SALLE_PHYSIQUE("salle_physique"),
    SALLE_NORMALE("salle_normale");

    private String nom;

    TypeBesoin(String nom){
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }
}
