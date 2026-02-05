package com.univtime.informatique.dto.tpDto;

import com.univtime.informatique.dto.sousGroupeDto.GroupeSousGroupeDto;
import com.univtime.informatique.dto.sousGroupeDto.ParticipeASousGroupeDto;

import java.util.Objects;
import java.util.Set;

public class SousGroupeTPDto {
    private Integer idSousGroupe;
    private String nomSousGroupe;
    private Integer nbEtuSousGroupe;
    private GroupeSousGroupeDto groupeDto;
    private Set<ParticipeASousGroupeDto> participeADto;

    public SousGroupeTPDto() {

    }

    public SousGroupeTPDto(Integer idSousGroupe, String nomSousGroupe, Integer nbEtuSousGroupe, GroupeSousGroupeDto groupeDto,
                         Set<ParticipeASousGroupeDto> participeADto) {
        this.idSousGroupe = idSousGroupe;
        this.nomSousGroupe = nomSousGroupe;
        this.nbEtuSousGroupe = nbEtuSousGroupe;
        this.groupeDto = groupeDto;
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
        SousGroupeTPDto that = (SousGroupeTPDto) obj;
        return Objects.equals(idSousGroupe, that.idSousGroupe);
    }
}
