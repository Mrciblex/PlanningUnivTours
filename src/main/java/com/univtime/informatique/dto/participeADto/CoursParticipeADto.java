package com.univtime.informatique.dto.participeADto;

import com.univtime.informatique.constants.TypeCours;

import java.time.LocalDateTime;
import java.util.Objects;

public class CoursParticipeADto {
    private Integer idCours;
    private LocalDateTime heureDebutCours;
    private LocalDateTime heureFinCours;
    private TypeCours typeCours;
    private Integer composanteId;
    private Integer professeurId;
    private Integer salleId;
    // private Set<ParticipeAIdDto> participeAIds;

    public CoursParticipeADto() {
    }

    public CoursParticipeADto(Integer salleId,
                              Integer professeurId,
                              Integer composanteId,
                              TypeCours typeCours,
                              LocalDateTime heureFinCours,
                              LocalDateTime heureDebutCours,
                              Integer idCours) {
        this.salleId = salleId;
        this.professeurId = professeurId;
        this.composanteId = composanteId;
        this.typeCours = typeCours;
        this.heureFinCours = heureFinCours;
        this.heureDebutCours = heureDebutCours;
        this.idCours = idCours;
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

    public Integer getSalleId() {
        return salleId;
    }

    public void setSalleId(Integer salleId) {
        this.salleId = salleId;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idCours);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        CoursParticipeADto coursDto = (CoursParticipeADto) obj;
        return Objects.equals(idCours, coursDto.idCours);
    }
}
