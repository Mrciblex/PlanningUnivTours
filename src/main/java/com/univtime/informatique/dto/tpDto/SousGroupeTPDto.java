package com.univtime.informatique.dto.tpDto;

import com.univtime.informatique.dto.idsDto.ParticipeAIdDto;

import java.util.Objects;
import java.util.Set;

public class SousGroupeTPDto {
    private Integer idSousGroupe;
    private String nomSousGroupe;
    private Integer nbEtuSousGroupe;
    private Integer groupeId;
    // private Set<TPIdDto> tpIds;
    private Set<ParticipeAIdDto> participeAIds;

    public SousGroupeTPDto() {

    }

    public SousGroupeTPDto(Integer idSousGroupe) {
        this.idSousGroupe = idSousGroupe;
    }

    public SousGroupeTPDto(Integer idSousGroupe,
                           String nomSousGroupe,
                           Integer nbEtuSousGroupe,
                           Integer groupeId,
                           Set<ParticipeAIdDto> participeAIds) {
        this.idSousGroupe = idSousGroupe;
        this.nomSousGroupe = nomSousGroupe;
        this.nbEtuSousGroupe = nbEtuSousGroupe;
        this.groupeId = groupeId;
        this.participeAIds = participeAIds;
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

    public Integer getGroupeId() {
        return groupeId;
    }

    public void setGroupeId(Integer groupeId) {
        this.groupeId = groupeId;
    }

    public Set<ParticipeAIdDto> getParticipeAIds() {
        return participeAIds;
    }

    public void setParticipeAIds(Set<ParticipeAIdDto> participeAIds) {
        this.participeAIds = participeAIds;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idSousGroupe);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SousGroupeTPDto that = (SousGroupeTPDto) obj;
        return Objects.equals(idSousGroupe, that.idSousGroupe);
    }
}
