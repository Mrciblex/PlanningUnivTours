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

package com.univtime.informatique.dto.professeurDto;

import java.util.Objects;
import java.util.Set;

public class JourProfesseurDto {
    private Integer idJour;
    private Integer jourSemaine;
    // private Integer professeurId;
    private Set<Integer> disponibiliteIds;


    public JourProfesseurDto() {

    }

    public JourProfesseurDto(Integer idJour,
                             Integer jourSemaine,
                             Set<Integer> disponibiliteIds) {
        this.idJour = idJour;
        this.jourSemaine = jourSemaine;
        this.disponibiliteIds = disponibiliteIds;
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

    public Set<Integer> getDisponibiliteIds() {
        return disponibiliteIds;
    }

    public void setDisponibiliteIds(Set<Integer> disponibiliteIds) {
        this.disponibiliteIds = disponibiliteIds;
    }

    public int hashCode() {
        return Objects.hashCode(idJour);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        JourProfesseurDto jourDto = (JourProfesseurDto) obj;
        return Objects.equals(idJour, jourDto.idJour);
    }
}
