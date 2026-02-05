package com.univtime.informatique.dtos.jourDto;

public class JourDto {
    private Integer idJour;
    private Integer jourSemaine;
    private ProfesseurJourDto professeurDto;

    // Getters et Setters
    public Integer getIdJour() {
        return idJour;
    }

    public void setIdJour(int idJour) {
        this.idJour = idJour;
    }

    public Integer getJourSemaine() {
        return jourSemaine;
    }

    public void setJourSemaine(int jourSemaine) {
        this.jourSemaine = jourSemaine;
    }

    public ProfesseurJourDto getProfesseurDto() {
        return professeurDto;
    }

    public void setProfesseurDto(ProfesseurJourDto professeurJourDto) {
        this.professeurDto = professeurJourDto;
    }
}
