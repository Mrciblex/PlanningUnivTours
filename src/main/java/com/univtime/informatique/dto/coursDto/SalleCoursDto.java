package com.univtime.informatique.dto.coursDto;

import com.univtime.informatique.dto.salleDto.BesoinSalleSalleDto;

import java.util.Objects;
import java.util.Set;

public class SalleCoursDto {
    private Integer idSalle;
    private Integer nbPlace;
    private boolean salleMachine;
    private Integer nbPC;
    private Set<BesoinSalleSalleDto> besoinSalleDto;

    public SalleCoursDto() {

    }

    public SalleCoursDto(Integer idSalle, Integer nbPlace, boolean salleMachine, Integer nbPC,
                         Set<BesoinSalleSalleDto> besoinSalleDto) {
        this.idSalle = idSalle;
        this.nbPlace = nbPlace;
        this.salleMachine = salleMachine;
        this.nbPC = nbPC;
        this.besoinSalleDto = besoinSalleDto;
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
        SalleCoursDto salleDto = (SalleCoursDto) obj;
        return Objects.equals(idSalle, salleDto.idSalle);
    }
}
