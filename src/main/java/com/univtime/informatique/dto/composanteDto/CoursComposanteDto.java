package com.univtime.informatique.dto.composanteDto;

import com.univtime.informatique.constants.TypeCours;
import com.univtime.informatique.dto.idsDto.ParticipeAIdDto;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

public class CoursComposanteDto {
    private Integer idCours;
    private LocalDateTime heureDebutCours;
    private LocalDateTime heureFinCours;
    private TypeCours typeCours;

    // private Integer composanteId;
    private Integer professeurId;
    private Integer salleId;
    private Set<ParticipeAIdDto> participeAIds;

    public CoursComposanteDto() {
    }

    public CoursComposanteDto(Integer idCours,
                              LocalDateTime heureDebutCours,
                              LocalDateTime heureFinCours,
                              TypeCours typeCours,
                              Integer professeurId,
                              Integer salleId,
                              Set<ParticipeAIdDto> participeAIds) {
        this.idCours = idCours;
        this.heureDebutCours = heureDebutCours;
        this.heureFinCours = heureFinCours;
        this.typeCours = typeCours;
        this.professeurId = professeurId;
        this.salleId = salleId;
        this.participeAIds = participeAIds;
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

    public Integer getProfesseurId() {
        return professeurId;
    }

    public void setProfesseurId(Integer professeurId) {
        this.professeurId = professeurId;
    }

    public Integer getSalleId() {
        return salleId;
    }

    public void setSalleId(Integer salleId) {
        this.salleId = salleId;
    }

    public Set<ParticipeAIdDto> getParticipeAIds() {
        return participeAIds;
    }

    public void setParticipeAIds(Set<ParticipeAIdDto> participeAIds) {
        this.participeAIds = participeAIds;
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

