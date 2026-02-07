package com.univtime.informatique.dto.salleDto;

import java.util.Objects;

public class SalleDto {
    private Integer idSalle;
    private Integer nbPlace;
    private boolean salleMachine;
    private Integer nbPC;

    public SalleDto() {

    }

    public SalleDto(
            Integer idSalle,
            Integer nbPlace,
            boolean salleMachine,
            Integer nbPC) {
        this.idSalle = idSalle;
        this.nbPlace = nbPlace;
        this.salleMachine = salleMachine;
        this.nbPC = nbPC;
    }

    // Getters et Setters
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
