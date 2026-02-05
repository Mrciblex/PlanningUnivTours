package com.univtime.informatique.dtos.coursDto;

import com.univtime.informatique.constants.TypeCours;

import java.sql.Timestamp;

public class CoursDto {
    private Integer idCours;
    private Timestamp heureDebutCours;
    private Timestamp heureFinCours;
    private TypeCours typeCoursEnum;
    private ComposanteCoursDto composanteDto;
    private ProfesseurCoursDto professeurDto;
    private SalleCoursDto salleDto;

    // Getters et Setters
    public Integer getIdCours() {
        return idCours;
    }

    public void setIdCours(Integer idCours) {
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

    public TypeCours getTypeCoursEnum() {
        return typeCoursEnum;
    }

    public void setTypeCoursEnum(TypeCours typeCoursEnum) {
        this.typeCoursEnum = typeCoursEnum;
    }

    public ComposanteCoursDto getComposanteDto() {
        return composanteDto;
    }

    public void setComposanteDto(ComposanteCoursDto composanteCoursDto) {
        this.composanteDto = composanteCoursDto;
    }

    public ProfesseurCoursDto getProfesseurDto() {
        return professeurDto;
    }

    public void setProfesseurDto(ProfesseurCoursDto professeurCoursDto) {
        this.professeurDto = professeurCoursDto;
    }

    public SalleCoursDto getSalleDto() {
        return salleDto;
    }

    public void setSalleDto(SalleCoursDto salleCoursDto) {
        this.salleDto = salleCoursDto;
    }
}

