package com.univtime.informatique.dto.salleDto;

import com.univtime.informatique.constants.TypeCours;
import com.univtime.informatique.dto.idsDto.ParticipeAIdDto;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

public class CoursSalleDto {
    private Integer idCours;
    private LocalDateTime heureDebutCours;
    private LocalDateTime heureFinCours;
    private TypeCours typeCours;
    private Integer composanteId;
    private Integer professeurId;
    // private Integer salleId;
    private Set<ParticipeAIdDto> participeAIds;

    public CoursSalleDto() {
    }

    public CoursSalleDto(Integer idCours,
                         LocalDateTime heureDebutCours,
                         LocalDateTime heureFinCours,
                         TypeCours typeCours,
                         Integer composanteId,
                         Integer professeurId,
                         Set<ParticipeAIdDto> participeAIds) {
        this.idCours = idCours;
        this.heureDebutCours = heureDebutCours;
        this.heureFinCours = heureFinCours;
        this.typeCours = typeCours;
        this.composanteId = composanteId;
        this.professeurId = professeurId;
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

    public Integer getProfesseurId() {
        return professeurId;
    }

    public void setProfesseurId(Integer professeurId) {
        this.professeurId = professeurId;
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
        CoursSalleDto coursDto = (CoursSalleDto) obj;
        return Objects.equals(idCours, coursDto.idCours);
    }
}
