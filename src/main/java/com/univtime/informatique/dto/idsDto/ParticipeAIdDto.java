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

package com.univtime.informatique.dto.idsDto;

import java.util.Objects;

public class ParticipeAIdDto {

    private Integer idSousGroupe;
    private Integer idCours;

    // Constructeur par défaut
    public ParticipeAIdDto() {
    }

    // Constructeur complet
    public ParticipeAIdDto(Integer idSousGroupe,
                           Integer idCours) {
        this.idSousGroupe = idSousGroupe;
        this.idCours = idCours;
    }

    // Getters et Setters
    public Integer getIdSousGroupe() { return idSousGroupe; }
    public void setIdSousGroupe(Integer idSousGroupe) { this.idSousGroupe = idSousGroupe; }

    public Integer getIdCours() { return idCours; }
    public void setIdCours(Integer idCours) { this.idCours = idCours; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParticipeAIdDto that = (ParticipeAIdDto) o;
        return Objects.equals(idSousGroupe, that.idSousGroupe) &&
                Objects.equals(idCours, that.idCours);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSousGroupe, idCours);
    }
}