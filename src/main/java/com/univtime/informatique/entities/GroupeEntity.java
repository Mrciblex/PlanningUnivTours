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
@Table(name = "groupes")
public class GroupeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idGroupe", nullable = false)
    private Integer idGroupe;

    @Column(name = "nomGroupe", nullable = false, length = 150)
    private String nomGroupe;

    @Column(name = "nbEtuGroupe", nullable = false)
    private Integer nbEtuGroupe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPromo", nullable = false)
    private PromoEntity promo;

    @OneToMany(mappedBy = "groupe", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TDEntity> tdEntities = new HashSet<>();

    @OneToMany(mappedBy = "groupe", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SousGroupeEntity> sousGroupeEntities = new HashSet<>();

    public Integer getIdGroupe() {
        return idGroupe;
    }

    public void setIdGroupe(Integer idGroupe) {
        this.idGroupe = idGroupe;
    }

    public String getNomGroupe() {
        return nomGroupe;
    }

    public void setNomGroupe(String nomGroupe) {
        this.nomGroupe = nomGroupe;
    }

    public Integer getNbEtuGroupe() {
        return nbEtuGroupe;
    }

    public void setNbEtuGroupe(Integer nbEtuGroupe) {
        this.nbEtuGroupe = nbEtuGroupe;
    }

    public PromoEntity getPromo() {
        return promo;
    }

    public void setPromo(PromoEntity promo) {
        this.promo = promo;
    }

    public Set<TDEntity> getTdEntities() {
        return tdEntities;
    }

    public void setTdEntities(Set<TDEntity> tdEntities) {
        this.tdEntities = tdEntities;
    }

    public Set<SousGroupeEntity> getSousGroupeEntities() {
        return sousGroupeEntities;
    }

    public void setSousGroupeEntities(Set<SousGroupeEntity> sousGroupeEntities) {
        this.sousGroupeEntities = sousGroupeEntities;
    }
}
