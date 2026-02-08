package com.univtime.informatique.dto.coursDto;

import com.univtime.informatique.dto.ids.ParticipeAIdDto;
import com.univtime.informatique.dto.ids.TPIdDto;

import java.util.Objects;
import java.util.Set;

public class ParticipeACoursDto {
    private Integer idSousGroupe;
    private String nomSousGroupe;
    private Integer nbEtuSousGroupe;
    private Integer groupeSousGroupeId;
    private Set<TPIdDto> tpSousGroupeIds;
    private Set<ParticipeAIdDto> participeASousGroupeIds;

    public ParticipeACoursDto() {

    }

    public ParticipeACoursDto(Integer idSousGroupe,
                              String nomSousGroupe,
                              Integer nbEtuSousGroupe,
                              Integer groupeSousGroupeId,
                              Set<TPIdDto> tpSousGroupeIds,
                              Set<ParticipeAIdDto> participeASousGroupeIds) {
        this.idSousGroupe = idSousGroupe;
        this.nomSousGroupe = nomSousGroupe;
        this.nbEtuSousGroupe = nbEtuSousGroupe;
        this.groupeSousGroupeId = groupeSousGroupeId;
        this.tpSousGroupeIds = tpSousGroupeIds;
        this.participeASousGroupeIds = participeASousGroupeIds;
    }

    public Integer getIdSousGroupe() {
        return idSousGroupe;
    }

    public void setIdSousGroupe(Integer idSousGroupe) {
        this.idSousGroupe = idSousGroupe;
    }

    public String getNomSousGroupe() {
        return nomSousGroupe;
    }

    public void setNomSousGroupe(String nomSousGroupe) {
        this.nomSousGroupe = nomSousGroupe;
    }

    public Integer getNbEtuSousGroupe() {
        return nbEtuSousGroupe;
    }

    public void setNbEtuSousGroupe(Integer nbEtuSousGroupe) {
        this.nbEtuSousGroupe = nbEtuSousGroupe;
    }

    public Integer getGroupeSousGroupeId() {
        return groupeSousGroupeId;
    }

    public void setGroupeSousGroupeId(Integer groupeSousGroupeId) {
        this.groupeSousGroupeId = groupeSousGroupeId;
    }

    public Set<TPIdDto> getTpSousGroupeIds() {
        return tpSousGroupeIds;
    }

    public void setTpSousGroupeIds(Set<TPIdDto> tpSousGroupeIds) {
        this.tpSousGroupeIds = tpSousGroupeIds;
    }

    public Set<ParticipeAIdDto> getParticipeASousGroupeIds() {
        return participeASousGroupeIds;
    }

    public void setParticipeASousGroupeIds(Set<ParticipeAIdDto> participeASousGroupeIds) {
        this.participeASousGroupeIds = participeASousGroupeIds;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ParticipeACoursDto that = (ParticipeACoursDto) o;
        return Objects.equals(idSousGroupe, that.idSousGroupe);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idSousGroupe);
    }
}
