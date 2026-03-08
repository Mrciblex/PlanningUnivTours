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

package com.univtime.informatique.dto.composanteDto;

import com.univtime.informatique.constants.TypeBesoin;
import com.univtime.informatique.dto.idsDto.BesoinSalleIdDto;

import java.util.Objects;
import java.util.Set;

public class BesoinSalleComposanteDto {
    private Integer idSalle;
    private Integer nbPlace;
    private boolean salleMachine;
    private Integer nbPC;
    private Set<Integer> coursSalleIds;
    private Set<BesoinSalleIdDto> besoinSalleSalleIds;

    private TypeBesoin typeBesoin;

    public BesoinSalleComposanteDto() {
    }

    public BesoinSalleComposanteDto(Integer idSalle,
                                    Integer nbPlace,
                                    boolean salleMachine,
                                    Integer nbPC,
                                    Set<Integer> coursSalleIds,
                                    Set<BesoinSalleIdDto> besoinSalleSalleIds,
                                    TypeBesoin typeBesoin) {
        this.idSalle = idSalle;
        this.nbPlace = nbPlace;
        this.salleMachine = salleMachine;
        this.nbPC = nbPC;
        this.coursSalleIds = coursSalleIds;
        this.besoinSalleSalleIds = besoinSalleSalleIds;
        this.typeBesoin = typeBesoin;
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

    public Set<Integer> getCoursSalleIds() {
        return coursSalleIds;
    }

    public void setCoursSalleIds(Set<Integer> coursSalleIds) {
        this.coursSalleIds = coursSalleIds;
    }

    public Set<BesoinSalleIdDto> getBesoinSalleSalleIds() {
        return besoinSalleSalleIds;
    }

    public void setBesoinSalleSalleIds(Set<BesoinSalleIdDto> besoinSalleSalleIds) {
        this.besoinSalleSalleIds = besoinSalleSalleIds;
    }

    public TypeBesoin getTypeBesoin() {
        return typeBesoin;
    }

    public void setTypeBesoin(TypeBesoin typeBesoin) {
        this.typeBesoin = typeBesoin;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BesoinSalleComposanteDto that = (BesoinSalleComposanteDto) o;
        return Objects.equals(idSalle, that.idSalle);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idSalle);
    }
}
