package com.univtime.informatique.dto.disponibiliteDto;

public class DisponibiliteDto {
    private Integer idDispo;
    private Integer heureDebutDispo;
    private Integer heureFinDispo;
    private JourDisponibiliteDto jourDto;

    // Getters et Setters
    public Integer getIdDispo() {
        return idDispo;
    }

    public void setIdDispo(Integer idDispo) {
        this.idDispo = idDispo;
    }

    public Integer getHeureDebutDispo() {
        return heureDebutDispo;
    }

    public void setHeureDebutDispo(Integer heureDebutDispo) {
        this.heureDebutDispo = heureDebutDispo;
    }

    public Integer getHeureFinDispo() {
        return heureFinDispo;
    }

    public void setHeureFinDispo(Integer heureFinDispo) {
        this.heureFinDispo = heureFinDispo;
    }

    public JourDisponibiliteDto getJourDto() {
        return jourDto;
    }

    public void setJourDto(JourDisponibiliteDto jourDisponibiliteDto) {
        this.jourDto = jourDisponibiliteDto;
    }
}

