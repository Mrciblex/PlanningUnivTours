package com.univtime.informatique.dto.groupeDto;

import com.univtime.informatique.dto.ids.ParticipeAIdDto;
import com.univtime.informatique.dto.ids.TPIdDto;

import java.util.Objects;
import java.util.Set;

public class SousGroupeGroupeDto {
    private Integer idSousGroupe;
    private String nomSousGroupe;
    private Integer nbEtuSousGroupe;

    // private Integer groupeId;
    private Set<TPIdDto> tpIds;
    private Set<ParticipeAIdDto> participeAIds;

    public SousGroupeGroupeDto() {

    }

    public SousGroupeGroupeDto(Set<ParticipeAIdDto> participeAIds,
                               Set<TPIdDto> tpIds,
                               Integer nbEtuSousGroupe,
                               String nomSousGroupe,
                               Integer idSousGroupe) {
        this.participeAIds = participeAIds;
        this.tpIds = tpIds;
        this.nbEtuSousGroupe = nbEtuSousGroupe;
        this.nomSousGroupe = nomSousGroupe;
        this.idSousGroupe = idSousGroupe;
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

    public Set<TPIdDto> getTpIds() {
        return tpIds;
    }

    public void setTpIds(Set<TPIdDto> tpIds) {
        this.tpIds = tpIds;
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
        SousGroupeGroupeDto that = (SousGroupeGroupeDto) obj;
        return Objects.equals(idSousGroupe, that.idSousGroupe);
    }
}
