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

package com.univtime.informatique.dto.jourDto;

import java.util.Objects;
import java.util.Set;

public class JourDto {
    private Integer idJour;
    private Integer jourSemaine;
    private ProfesseurJourDto professeurDto;
    private Set<DisponibiliteJourDto> disponibiliteDto;

    public JourDto() {

    }

    public JourDto(Integer idJour,
                   Integer jourSemaine,
                   ProfesseurJourDto professeurDto,
                   Set<DisponibiliteJourDto> disponibiliteDto) {
        this.idJour = idJour;
        this.jourSemaine = jourSemaine;
        this.professeurDto = professeurDto;
        this.disponibiliteDto = disponibiliteDto;
    }

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

    public ProfesseurJourDto getProfesseurDto() {
        return professeurDto;
    }

    public void setProfesseurDto(ProfesseurJourDto professeurDto) {
        this.professeurDto = professeurDto;
    }

    public Set<DisponibiliteJourDto> getDisponibiliteDto() {
        return disponibiliteDto;
    }

    public void setDisponibiliteDto(Set<DisponibiliteJourDto> disponibiliteDto) {
        this.disponibiliteDto = disponibiliteDto;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idJour);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        JourDto jourDto = (JourDto) obj;
        return Objects.equals(idJour, jourDto.idJour);
    }
}
