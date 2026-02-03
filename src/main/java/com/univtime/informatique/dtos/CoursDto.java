package com.univtime.informatique.dtos;

import java.sql.Timestamp;

public class CoursDto {
    private int idCours;
    private Timestamp heureDebutCours;
    private Timestamp heureFinCours;
    private String typeCoursEnum;
    private int idComposante;
    private int idProf;
    private int idSalle;

    // Getters et Setters
    public int getIdCours() {
        return idCours;
    }

    public void setIdCours(int idCours) {
        this.idCours = idCours;
    }

    public Timestamp getHeureDebutCours() {
        return heureDebutCours;
    }

    public void setHeureDebutCours(Timestamp heureDebutCours) {
        this.heureDebutCours = heureDebutCours;
    }

    public Timestamp getHeureFinCours() {
        return heureFinCours;
    }

    public void setHeureFinCours(Timestamp heureFinCours) {
        this.heureFinCours = heureFinCours;
    }

    public String getTypeCoursEnum() {
        return typeCoursEnum;
    }

    public void setTypeCoursEnum(String typeCoursEnum) {
        this.typeCoursEnum = typeCoursEnum;
    }

    public int getIdComposante() {
        return idComposante;
    }

    public void setIdComposante(int idComposante) {
        this.idComposante = idComposante;
    }

    public int getIdProf() {
        return idProf;
    }

    public void setIdProf(int idProf) {
        this.idProf = idProf;
    }

    public int getIdSalle() {
        return idSalle;
    }

    public void setIdSalle(int idSalle) {
        this.idSalle = idSalle;
    }
}

