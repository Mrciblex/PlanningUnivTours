package com.univtime.informatique.dto.professeurDto;

import com.univtime.informatique.dto.jourDto.DisponibiliteJourDto;

import java.util.Objects;
import java.util.Set;

public class JourProfesseurDto {
    private Integer idJour;
    private Integer jourSemaine;
    private Set<DisponibiliteJourDto> disponibiliteDto;

    public JourProfesseurDto() {

    }

    public JourProfesseurDto(Integer idJour, Integer jourSemaine, Set<DisponibiliteJourDto> disponibiliteDto) {
        this.idJour = idJour;
        this.jourSemaine = jourSemaine;
        this.disponibiliteDto = disponibiliteDto;
    }

    // Getters et Setters
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
        if (obj == null || getClass() != obj.getClass()) return false;
        JourProfesseurDto jourDto = (JourProfesseurDto) obj;
        return Objects.equals(idJour, jourDto.idJour);
    }
}
