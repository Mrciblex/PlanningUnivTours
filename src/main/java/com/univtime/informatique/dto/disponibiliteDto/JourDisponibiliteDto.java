package com.univtime.informatique.dto.disponibiliteDto;

import com.univtime.informatique.dto.jourDto.ProfesseurJourDto;

import java.util.Objects;
import java.util.Set;

public class JourDisponibiliteDto {
    private Integer idJour;
    private Integer jourSemaine;
    private ProfesseurJourDto professeurDto;

    public JourDisponibiliteDto() {

    }

    public JourDisponibiliteDto(Integer idJour, Integer jourSemaine, ProfesseurJourDto professeurDto) {
        this.idJour = idJour;
        this.jourSemaine = jourSemaine;
        this.professeurDto = professeurDto;
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

    public ProfesseurJourDto getProfesseurDto() {
        return professeurDto;
    }

    public void setProfesseurDto(ProfesseurJourDto professeurDto) {
        this.professeurDto = professeurDto;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idJour);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        JourDisponibiliteDto jourDto = (JourDisponibiliteDto) obj;
        return Objects.equals(idJour, jourDto.idJour);
    }
}
