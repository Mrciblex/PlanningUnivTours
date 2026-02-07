package com.univtime.informatique.dto.jourDto;

import java.util.Objects;

public class JourDto {
    private Integer idJour;
    private Integer jourSemaine;

    public JourDto() {

    }

    public JourDto(
            Integer idJour,
            Integer jourSemaine) {
        this.idJour = idJour;
        this.jourSemaine = jourSemaine;
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
