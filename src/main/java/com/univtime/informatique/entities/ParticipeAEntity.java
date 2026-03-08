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

package com.univtime.informatique.entities;

import com.univtime.informatique.entities.ids.ParticipeAId;
import jakarta.persistence.*;

@Entity
@Table(name = "participeA")
public class ParticipeAEntity {
    @EmbeddedId
    private ParticipeAId idParticipeA;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idSousGroupe")
    @JoinColumn(name = "idSousGroupe", referencedColumnName = "idSousGroupe", insertable = false, updatable = false)
    private SousGroupeEntity sousGroupe;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idCours")
    @JoinColumn(name = "idCours", referencedColumnName = "idCours", insertable = false, updatable = false)
    private CoursEntity cours;

    public ParticipeAId getIdParticipeA() {
        return idParticipeA;
    }

    public void setIdParticipeA(ParticipeAId idParticipeA) {
        this.idParticipeA = idParticipeA;
    }

    public SousGroupeEntity getSousGroupe() {
        return sousGroupe;
    }

    public void setSousGroupe(SousGroupeEntity sousGroupe) {
        this.sousGroupe = sousGroupe;
    }

    public CoursEntity getCours() {
        return cours;
    }

    public void setCours(CoursEntity cours) {
        this.cours = cours;
    }
}
