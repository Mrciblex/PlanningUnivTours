package com.univtime.informatique.dto.sousGroupeDto;

import com.univtime.informatique.constants.TypeCours;
import com.univtime.informatique.dto.ids.ParticipeAIdDto;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

public class ParticipeASousGroupeDto {
    private Integer idCours;
    private LocalDateTime heureDebutCours;
    private LocalDateTime heureFinCours;
    private TypeCours typeCours;
    private Integer composanteCoursId;
    private Integer professeurCoursId;
    private Integer salleCoursId;
    private Set<ParticipeAIdDto> participeACoursIds;

    public ParticipeASousGroupeDto() {

    }

    public ParticipeASousGroupeDto(Integer idCours,
                                   LocalDateTime heureDebutCours,
                                   LocalDateTime heureFinCours,
                                   TypeCours typeCours,
                                   Integer composanteCoursId,
                                   Integer professeurCoursId,
                                   Integer salleCoursId,
                                   Set<ParticipeAIdDto> participeACoursIds) {
        this.idCours = idCours;
        this.heureDebutCours = heureDebutCours;
        this.heureFinCours = heureFinCours;
        this.typeCours = typeCours;
        this.composanteCoursId = composanteCoursId;
        this.professeurCoursId = professeurCoursId;
        this.salleCoursId = salleCoursId;
        this.participeACoursIds = participeACoursIds;
    }

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

    public TypeCours getTypeCours() {
        return typeCours;
    }

    public void setTypeCours(TypeCours typeCours) {
        this.typeCours = typeCours;
    }

    public Integer getComposanteCoursId() {
        return composanteCoursId;
    }

    public void setComposanteCoursId(Integer composanteCoursId) {
        this.composanteCoursId = composanteCoursId;
    }

    public Integer getProfesseurCoursId() {
        return professeurCoursId;
    }

    public void setProfesseurCoursId(Integer professeurCoursId) {
        this.professeurCoursId = professeurCoursId;
    }

    public Integer getSalleCoursId() {
        return salleCoursId;
    }

    public void setSalleCoursId(Integer salleCoursId) {
        this.salleCoursId = salleCoursId;
    }

    public Set<ParticipeAIdDto> getParticipeACoursIds() {
        return participeACoursIds;
    }

    public void setParticipeACoursIds(Set<ParticipeAIdDto> participeACoursIds) {
        this.participeACoursIds = participeACoursIds;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ParticipeASousGroupeDto that = (ParticipeASousGroupeDto) o;
        return Objects.equals(idCours, that.idCours);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idCours);
    }
}
