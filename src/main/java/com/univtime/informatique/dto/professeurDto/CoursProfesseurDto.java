package com.univtime.informatique.dto.professeurDto;

import com.univtime.informatique.constants.TypeCours;
import com.univtime.informatique.dto.coursDto.*;
import com.univtime.informatique.dto.ids.ParticipeAIdDto;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

public class CoursProfesseurDto {
    private Integer idCours;
    private LocalDateTime heureDebutCours;
    private LocalDateTime heureFinCours;
    private TypeCours typeCours;

    private Integer composanteId;
    // private Integer professeurId;
    private Integer salleId;
    private Set<ParticipeAIdDto> participeAIds;

    public CoursProfesseurDto() {

    }

    public CoursProfesseurDto(Integer idCours,
                              LocalDateTime heureDebutCours,
                              LocalDateTime heureFinCours,
                              TypeCours typeCours,
                              Integer composanteId,
                              Integer salleId,
                              Set<ParticipeAIdDto> participeAIds) {
        this.idCours = idCours;
        this.heureDebutCours = heureDebutCours;
        this.heureFinCours = heureFinCours;
        this.typeCours = typeCours;
        this.composanteId = composanteId;
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

    public Integer getComposanteId() {
        return composanteId;
    }

    public void setComposanteId(Integer composanteId) {
        this.composanteId = composanteId;
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
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CoursProfesseurDto coursDto = (CoursProfesseurDto) obj;
        return Objects.equals(idCours, coursDto.idCours);
    }
}
