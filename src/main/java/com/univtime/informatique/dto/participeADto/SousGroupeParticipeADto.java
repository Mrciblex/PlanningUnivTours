package com.univtime.informatique.dto.participeADto;

import com.univtime.informatique.dto.ids.TPIdDto;

import java.util.Objects;
import java.util.Set;

public class SousGroupeParticipeADto {
    private Integer idSousGroupe;
    private String nomSousGroupe;
    private Integer nbEtuSousGroupe;
    private Integer groupeId;
    private Set<TPIdDto> tpIds;
    // private Set<ParticipeAIdDto> participeAIds;

    public SousGroupeParticipeADto() {

    }

    public SousGroupeParticipeADto(Integer idSousGroupe,
                                   String nomSousGroupe,
                                   Integer nbEtuSousGroupe,
                                   Integer groupeId,
                                   Set<TPIdDto> tpIds) {
        this.idSousGroupe = idSousGroupe;
        this.nomSousGroupe = nomSousGroupe;
        this.nbEtuSousGroupe = nbEtuSousGroupe;
        this.groupeId = groupeId;
        this.tpIds = tpIds;
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

    public Set<TPIdDto> getTpIds() {
        return tpIds;
    }

    public void setTpIds(Set<TPIdDto> tpIds) {
        this.tpIds = tpIds;
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
        SousGroupeParticipeADto that = (SousGroupeParticipeADto) obj;
        return Objects.equals(idSousGroupe, that.idSousGroupe);
    }
}
