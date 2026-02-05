package com.univtime.informatique.dto.composanteDto;

import com.univtime.informatique.constants.TypeCours;
import com.univtime.informatique.dto.coursDto.ParticipeACoursDto;
import com.univtime.informatique.dto.coursDto.ProfesseurCoursDto;
import com.univtime.informatique.dto.coursDto.SalleCoursDto;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

public class CoursComposanteDto {
    private Integer idCours;
    private LocalDateTime heureDebutCours;
    private LocalDateTime heureFinCours;
    private TypeCours typeCoursEnum;
    private ProfesseurCoursDto professeurDto;
    private SalleCoursDto salleDto;
    private Set<ParticipeACoursDto> participeADto;

    public CoursComposanteDto() {
    }

    public CoursComposanteDto(
            Integer idCours,
            LocalDateTime heureDebutCours,
            LocalDateTime heureFinCours,
            TypeCours typeCoursEnum,
            ProfesseurCoursDto professeurDto,
            SalleCoursDto salleDto,
            Set<ParticipeACoursDto> participeADto) {
        this.idCours = idCours;
        this.heureDebutCours = heureDebutCours;
        this.heureFinCours = heureFinCours;
        this.typeCoursEnum = typeCoursEnum;
        this.professeurDto = professeurDto;
        this.salleDto = salleDto;
        this.participeADto = participeADto;
    }

    // Getters et Setters
    public Integer getIdCours() {
        return idCours;
    }

    public void setIdCours(Integer idCours) {
        this.idCours = idCours;
    }

    public LocalDateTime getHeureDebutCours() {
        return heureDebutCours;
    }

    public void setHeureDebutCours(LocalDateTime heureDebutCours) {
        this.heureDebutCours = heureDebutCours;
    }

    public LocalDateTime getHeureFinCours() {
        return heureFinCours;
    }

    public void setHeureFinCours(LocalDateTime heureFinCours) {
        this.heureFinCours = heureFinCours;
    }

    public TypeCours getTypeCoursEnum() {
        return typeCoursEnum;
    }

    public void setTypeCoursEnum(TypeCours typeCoursEnum) {
        this.typeCoursEnum = typeCoursEnum;
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

    public Set<ParticipeACoursDto> getParticipeADto() {
        return participeADto;
    }

    public void setParticipeADto(Set<ParticipeACoursDto> participeADto) {
        this.participeADto = participeADto;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idCours);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        CoursComposanteDto coursDto = (CoursComposanteDto) obj;
        return Objects.equals(idCours, coursDto.idCours);
    }
}

