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

package com.univtime.informatique.dto.disponibiliteDto;

import java.util.Objects;

public class DisponibiliteDto {
    private Integer idDispo;
    private Integer heureDebutDispo;
    private Integer heureFinDispo;
    private JourDisponibiliteDto jourDto;

    public DisponibiliteDto(){
    }

    public DisponibiliteDto(Integer idDispo, Integer heureDebutDispo, Integer heureFinDispo, JourDisponibiliteDto jourDto) {
        this.idDispo = idDispo;
        this.heureDebutDispo = heureDebutDispo;
        this.heureFinDispo = heureFinDispo;
        this.jourDto = jourDto;
    }

    // Getters et Setters
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

    public JourDisponibiliteDto getJourDto() {
        return jourDto;
    }

    public void setJourDto(JourDisponibiliteDto jourDto) {
        this.jourDto = jourDto;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idDispo);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        DisponibiliteDto that = (DisponibiliteDto) obj;
        return Objects.equals(idDispo, that.idDispo);
    }
}

