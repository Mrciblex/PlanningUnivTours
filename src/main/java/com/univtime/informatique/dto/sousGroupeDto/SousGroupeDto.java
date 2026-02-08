package com.univtime.informatique.dto.sousGroupeDto;

import java.util.Objects;
import java.util.Set;

public class SousGroupeDto {
    private Integer idSousGroupe;
    private String nomSousGroupe;
    private Integer nbEtuSousGroupe;
    private GroupeSousGroupeDto groupeDto;
    private Set<TPSousGroupeDto> tpDto;
    private Set<ParticipeASousGroupeDto> participeADto;

    public SousGroupeDto() {

    }

    public SousGroupeDto(Integer idSousGroupe,
                         String nomSousGroupe,
                         Integer nbEtuSousGroupe,
                         GroupeSousGroupeDto groupeDto,
                         Set<TPSousGroupeDto> tpDto,
                         Set<ParticipeASousGroupeDto> participeADto) {
        this.idSousGroupe = idSousGroupe;
        this.nomSousGroupe = nomSousGroupe;
        this.nbEtuSousGroupe = nbEtuSousGroupe;
        this.groupeDto = groupeDto;
        this.tpDto = tpDto;
        this.participeADto = participeADto;
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

    public GroupeSousGroupeDto getGroupeDto() {
        return groupeDto;
    }

    public void setGroupeDto(GroupeSousGroupeDto groupeDto) {
        this.groupeDto = groupeDto;
    }

    public Set<TPSousGroupeDto> getTpDto() {
        return tpDto;
    }

    public void setTpDto(Set<TPSousGroupeDto> tpDto) {
        this.tpDto = tpDto;
    }

    public Set<ParticipeASousGroupeDto> getParticipeADto() {
        return participeADto;
    }

    public void setParticipeADto(Set<ParticipeASousGroupeDto> participeADto) {
        this.participeADto = participeADto;
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
        SousGroupeDto that = (SousGroupeDto) obj;
        return Objects.equals(idSousGroupe, that.idSousGroupe);
    }
}
