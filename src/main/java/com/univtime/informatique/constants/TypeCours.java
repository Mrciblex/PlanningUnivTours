package com.univtime.informatique.constants;

public enum TypeCours {
    TP("TP"),
    CM("CM"),
    TD("TD");

    private String nom;

    TypeCours(String nom){
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }
}
