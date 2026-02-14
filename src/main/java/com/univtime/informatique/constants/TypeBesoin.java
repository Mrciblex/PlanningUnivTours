package com.univtime.informatique.constants;

public enum TypeBesoin {
    SALLE_INFORMATIQUE("SALLE_INFORMATIQUE"),
    SALLE_PHYSIQUE("SALLE_PHYSIQUE"),
    SALLE_NORMALE("SALLE_NORMALE");

    private String nom;

    TypeBesoin(String nom){
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }
}
