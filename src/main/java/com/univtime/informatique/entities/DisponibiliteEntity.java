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

@Entity
@Table(name = "disponibilites")
public class DisponibiliteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDispo", nullable = false)
    private Integer idDispo;

    @Column(name = "heureDebutDispo", nullable = false)
    private Integer heureDebutDispo;

    @Column(name = "heureFinDispo", nullable = false)
    private Integer heureFinDispo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idJour", nullable = false)
    private JourEntity jour;

    public Integer getIdDispo() {
        return idDispo;
    }

    public void setIdDispo(Integer idDispo) {
        this.idDispo = idDispo;
    }

    public Integer getHeureDebutDispo() {
        return heureDebutDispo;
    }

    public void setHeureDebutDispo(Integer heureDebutDispo) {
        this.heureDebutDispo = heureDebutDispo;
    }

    public Integer getHeureFinDispo() {
        return heureFinDispo;
    }

    public void setHeureFinDispo(Integer heureFinDispo) {
        this.heureFinDispo = heureFinDispo;
    }

    public JourEntity getJour() {
        return jour;
    }

    public void setJour(JourEntity jour) {
        this.jour = jour;
    }
}
