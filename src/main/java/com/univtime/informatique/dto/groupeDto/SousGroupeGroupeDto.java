package com.univtime.informatique.dto.groupeDto;

import com.univtime.informatique.dto.sousGroupeDto.ParticipeASousGroupeDto;
import com.univtime.informatique.dto.sousGroupeDto.TPSousGroupeDto;

import java.util.Objects;
import java.util.Set;

public class SousGroupeGroupeDto {
    private Integer idSousGroupe;
    private String nomSousGroupe;
    private Integer nbEtuSousGroupe;
    private Set<TPSousGroupeDto> tpDto;
    private Set<ParticipeASousGroupeDto> participeADto;

    public SousGroupeGroupeDto() {

    }

    public SousGroupeGroupeDto(Integer idSousGroupe, String nomSousGroupe, Integer nbEtuSousGroupe,
                         Set<TPSousGroupeDto> tpDto, Set<ParticipeASousGroupeDto> participeADto) {
        this.idSousGroupe = idSousGroupe;
        this.nomSousGroupe = nomSousGroupe;
        this.nbEtuSousGroupe = nbEtuSousGroupe;
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
        SousGroupeGroupeDto that = (SousGroupeGroupeDto) obj;
        return Objects.equals(idSousGroupe, that.idSousGroupe);
    }
}
