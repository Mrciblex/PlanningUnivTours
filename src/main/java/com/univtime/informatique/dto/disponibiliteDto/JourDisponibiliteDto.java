package com.univtime.informatique.dto.disponibiliteDto;

import java.util.Objects;

public class JourDisponibiliteDto {
    private Integer idJour;
    private Integer jourSemaine;

    private Integer professeurId;
    // private Set<Integer> disponibiliteIds;

    public JourDisponibiliteDto() {

    }

    public JourDisponibiliteDto(Integer idJour,
                                Integer jourSemaine,
                                Integer professeurId) {
        this.idJour = idJour;
        this.jourSemaine = jourSemaine;
        this.professeurId = professeurId;
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

    public Integer getProfesseurId() {
        return professeurId;
    }

    public void setProfesseurId(Integer professeurId) {
        this.professeurId = professeurId;
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
