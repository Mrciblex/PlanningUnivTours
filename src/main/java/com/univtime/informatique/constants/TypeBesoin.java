/*
 * Copyright (c) 2026 Ademi Musa. Tous droits réservés.
 * Projet : univTime - Logiciel de gestion d'emplois du temps.
 *
 * Ce code source et l'algorithme associé sont la propriété exclusive de l'auteur.
 * Toute reproduction, modification ou distribution non autorisée, par quelque moyen que ce soit, est strictement interdite.
 *
 * Ce fichier fait partie du projet univTime, concédé sous licence d'usage
 * restreinte à l'Université de Tours, 37000, en France.
 */

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
