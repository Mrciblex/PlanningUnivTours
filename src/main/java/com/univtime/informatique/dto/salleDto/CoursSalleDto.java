package com.univtime.informatique.dto.salleDto;

import com.univtime.informatique.constants.TypeCours;
import com.univtime.informatique.dto.coursDto.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

public class CoursSalleDto {
    private Integer idCours;
    private LocalDateTime heureDebutCours;
    private LocalDateTime heureFinCours;
    private TypeCours typeCoursEnum;

    public CoursSalleDto() {
    }

    public CoursSalleDto(
            Integer idCours,
            LocalDateTime heureDebutCours,
            LocalDateTime heureFinCours,
            TypeCours typeCoursEnum,
            ComposanteCoursDto composanteDto,
            ProfesseurCoursDto professeurDto,
            SalleCoursDto salleDto,
            Set<ParticipeACoursDto> participeADto) {
        this.idCours = idCours;
        this.heureDebutCours = heureDebutCours;
        this.heureFinCours = heureFinCours;
        this.typeCoursEnum = typeCoursEnum;
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

    @Override
    public int hashCode() {
        return Objects.hashCode(idCours);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CoursSalleDto coursDto = (CoursSalleDto) obj;
        return Objects.equals(idCours, coursDto.idCours);
    }
}
