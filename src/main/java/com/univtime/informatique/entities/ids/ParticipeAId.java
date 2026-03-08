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

package com.univtime.informatique.entities.ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ParticipeAId implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "idSousGroupe", nullable = false)
    private Integer idSousGroupe;

    @Column(name = "idCours", nullable = false)
    private Integer idCours;

    public ParticipeAId() {

    }

    public ParticipeAId(Integer idSousGroupe, Integer idCours) {
        this.idSousGroupe = idSousGroupe;
        this.idCours = idCours;
    }

    public Integer getIdSousGroupe() {
        return idSousGroupe;
    }

    public void setIdSousGroupe(Integer idSousGroupe) {
        this.idSousGroupe = idSousGroupe;
    }

    public Integer getIdCours() {
        return idCours;
    }

    public void setIdCours(Integer idCours) {
        this.idCours = idCours;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSousGroupe, idCours);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ParticipeAId other = (ParticipeAId) obj;
        return Objects.equals(this.idSousGroupe, other.idSousGroupe) && Objects.equals(this.idCours, other.idCours);
    }
}
