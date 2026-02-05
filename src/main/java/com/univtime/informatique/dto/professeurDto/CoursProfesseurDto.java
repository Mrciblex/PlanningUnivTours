package com.univtime.informatique.dto.professeurDto;

import com.univtime.informatique.constants.TypeCours;
import com.univtime.informatique.dto.coursDto.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

public class CoursProfesseurDto {
    private Integer idCours;
    private LocalDateTime heureDebutCours;
    private LocalDateTime heureFinCours;
    private TypeCours typeCoursEnum;
    private ComposanteCoursDto composanteDto;
    private SalleCoursDto salleDto;
    private Set<ParticipeACoursDto> participeADto;

    public CoursProfesseurDto() {

    }

    public CoursProfesseurDto(
            Integer idCours,
            LocalDateTime heureDebutCours,
            LocalDateTime heureFinCours,
            TypeCours typeCoursEnum,
            ComposanteCoursDto composanteDto,
            SalleCoursDto salleDto,
            Set<ParticipeACoursDto> participeADto) {
        this.idCours = idCours;
        this.heureDebutCours = heureDebutCours;
        this.heureFinCours = heureFinCours;
        this.typeCoursEnum = typeCoursEnum;
        this.composanteDto = composanteDto;
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

    public ComposanteCoursDto getComposanteDto() {
        return composanteDto;
    }

    public void setComposanteDto(ComposanteCoursDto composanteCoursDto) {
        this.composanteDto = composanteCoursDto;
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
        CoursProfesseurDto coursDto = (CoursProfesseurDto) obj;
        return Objects.equals(idCours, coursDto.idCours);
    }
}
