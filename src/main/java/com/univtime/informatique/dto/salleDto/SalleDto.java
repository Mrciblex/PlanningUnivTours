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

package com.univtime.informatique.dto.salleDto;

import java.util.Objects;
import java.util.Set;

public class SalleDto {
    private Integer idSalle;
    private Integer nbPlace;
    private boolean salleMachine;
    private Integer nbPC;
    private Set<CoursSalleDto> coursDto;
    private Set<BesoinSalleSalleDto> besoinSalleDto;

    public SalleDto() {

    }

    public SalleDto(Integer idSalle,
                    Integer nbPlace,
                    boolean salleMachine,
                    Integer nbPC,
                    Set<CoursSalleDto> coursDto,
                    Set<BesoinSalleSalleDto> besoinSalleDto) {
        this.idSalle = idSalle;
        this.nbPlace = nbPlace;
        this.salleMachine = salleMachine;
        this.nbPC = nbPC;
        this.coursDto = coursDto;
        this.besoinSalleDto = besoinSalleDto;
    }

    public Integer getIdSalle() {
        return idSalle;
    }

    public void setIdSalle(Integer idSalle) {
        this.idSalle = idSalle;
    }

    public Integer getNbPlace() {
        return nbPlace;
    }

    public void setNbPlace(Integer nbPlace) {
        this.nbPlace = nbPlace;
    }

    public boolean isSalleMachine() {
        return salleMachine;
    }

    public void setSalleMachine(boolean salleMachine) {
        this.salleMachine = salleMachine;
    }

    public Integer getNbPC() {
        return nbPC;
    }

    public void setNbPC(Integer nbPC) {
        this.nbPC = nbPC;
    }

    public Set<CoursSalleDto> getCoursDto() {
        return coursDto;
    }

    public void setCoursDto(Set<CoursSalleDto> coursDto) {
        this.coursDto = coursDto;
    }

    public Set<BesoinSalleSalleDto> getBesoinSalleDto() {
        return besoinSalleDto;
    }

    public void setBesoinSalleDto(Set<BesoinSalleSalleDto> besoinSalleDto) {
        this.besoinSalleDto = besoinSalleDto;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idSalle);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SalleDto salleDto = (SalleDto) obj;
        return Objects.equals(idSalle, salleDto.idSalle);
    }
}
