package com.univtime.informatique.dto.besoinSalleDto;

import com.univtime.informatique.dto.salleDto.CoursSalleDto;

import java.util.Objects;
import java.util.Set;

public class SalleBesoinSalleDto {
    private Integer idSalle;
    private Integer nbPlace;
    private boolean salleMachine;
    private Integer nbPC;
    private Set<CoursSalleDto> coursDto;

    public SalleBesoinSalleDto() {

    }

    public SalleBesoinSalleDto(Integer idSalle, Integer nbPlace, boolean salleMachine, Integer nbPC,
                    Set<CoursSalleDto> coursDto) {
        this.idSalle = idSalle;
        this.nbPlace = nbPlace;
        this.salleMachine = salleMachine;
        this.nbPC = nbPC;
        this.coursDto = coursDto;
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

    public Set<CoursSalleDto> getCoursDto() {
        return coursDto;
    }

    public void setCoursDto(Set<CoursSalleDto> coursDto) {
        this.coursDto = coursDto;
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
        SalleBesoinSalleDto salleDto = (SalleBesoinSalleDto) obj;
        return Objects.equals(idSalle, salleDto.idSalle);
    }
}
