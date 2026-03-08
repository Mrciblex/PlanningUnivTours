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
@Table(name = "jours")
public class JourEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idJour", nullable = false)
    private Integer idJour;

    @Column(name = "JourSemaine", nullable = false, length = 5)
    private Integer jourSemaine;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProf", nullable = false)
    private ProfesseurEntity professeur;

    @OneToMany(mappedBy = "jour", fetch = FetchType.LAZY)
    private Set<DisponibiliteEntity> disponibiliteEntities = new HashSet<>();

    public Integer getIdJour() {
        return idJour;
    }

    public void setIdJour(Integer idJour) {
        this.idJour = idJour;
    }

    public Integer getJourSemaine() {
        return jourSemaine;
    }

    public void setJourSemaine(Integer jourSemaine) {
        this.jourSemaine = jourSemaine;
    }

    public ProfesseurEntity getProfesseur() {
        return professeur;
    }

    public void setProfesseur(ProfesseurEntity professeur) {
        this.professeur = professeur;
    }

    public Set<DisponibiliteEntity> getDisponibiliteEntities() {
        return disponibiliteEntities;
    }

    public void setDisponibiliteEntities(Set<DisponibiliteEntity> disponibiliteEntities) {
        this.disponibiliteEntities = disponibiliteEntities;
    }
}