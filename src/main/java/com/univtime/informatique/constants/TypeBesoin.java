package com.univtime.informatique.constants;

public enum TypeBesoin {
    SALLE_INFORMATIQUE("salle informatique"),
    SALLE_PHYSIQUE("salle physique"),
    SALLE_NORMALE("salle normale");

    private String nom;

    TypeBesoin(String nom){
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }
}
