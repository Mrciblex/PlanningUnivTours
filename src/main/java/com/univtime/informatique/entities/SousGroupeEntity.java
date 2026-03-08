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

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sousGroupes")
public class SousGroupeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSousGroupe", nullable = false)
    private Integer idSousGroupe;

    @Column(name = "nomSousGroupe", nullable = false, length = 150)
    private String nomSousGroupe;

    @Column(name = "nbEtuSousGroupe", nullable = false)
    private Integer nbEtuSousGroupe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idGroupe", nullable = false)
    private GroupeEntity groupe;

    @OneToMany(mappedBy = "sousGroupe", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TPEntity> tpEntities = new HashSet<>();

    @OneToMany(mappedBy = "sousGroupe", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ParticipeAEntity> participeAEntities = new HashSet<>();

    public Integer getIdSousGroupe() {
        return idSousGroupe;
    }

    public void setIdSousGroupe(Integer idSousGroupe) {
        this.idSousGroupe = idSousGroupe;
    }

    public String getNomSousGroupe() {
        return nomSousGroupe;
    }

    public void setNomSousGroupe(String nomSousGroupe) {
        this.nomSousGroupe = nomSousGroupe;
    }

    public Integer getNbEtuSousGroupe() {
        return nbEtuSousGroupe;
    }

    public void setNbEtuSousGroupe(Integer nbEtuSousGroupe) {
        this.nbEtuSousGroupe = nbEtuSousGroupe;
    }

    public GroupeEntity getGroupe() {
        return groupe;
    }

    public void setGroupe(GroupeEntity groupe) {
        this.groupe = groupe;
    }

    public Set<TPEntity> getTpEntities() {
        return tpEntities;
    }

    public void setTpEntities(Set<TPEntity> tpEntities) {
        this.tpEntities = tpEntities;
    }

    public Set<ParticipeAEntity> getParticipeAEntities() {
        return participeAEntities;
    }

    public void setParticipeAEntities(Set<ParticipeAEntity> participeAEntities) {
        this.participeAEntities = participeAEntities;
    }
}
