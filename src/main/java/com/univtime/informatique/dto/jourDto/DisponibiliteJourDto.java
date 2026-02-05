package com.univtime.informatique.dto.jourDto;

import java.util.Objects;

public class DisponibiliteJourDto {
    private Integer idDispo;
    private Integer heureDebutDispo;
    private Integer heureFinDispo;

    public DisponibiliteJourDto(){
    }

    public DisponibiliteJourDto(Integer idDispo, Integer heureDebutDispo, Integer heureFinDispo) {
        this.idDispo = idDispo;
        this.heureDebutDispo = heureDebutDispo;
        this.heureFinDispo = heureFinDispo;
    }

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

    @Override
    public int hashCode() {
        return Objects.hashCode(idDispo);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        DisponibiliteJourDto that = (DisponibiliteJourDto) obj;
        return Objects.equals(idDispo, that.idDispo);
    }
}

