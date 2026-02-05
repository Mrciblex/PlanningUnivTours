package com.univtime.informatique.dto.participeADto;

import com.univtime.informatique.dto.sousGroupeDto.GroupeSousGroupeDto;
import com.univtime.informatique.dto.sousGroupeDto.SousGroupeDto;
import com.univtime.informatique.dto.sousGroupeDto.TPSousGroupeDto;

import java.util.Objects;
import java.util.Set;

public class SousGroupeParticipeADto {
    private Integer idSousGroupe;
    private String nomSousGroupe;
    private Integer nbEtuSousGroupe;
    private GroupeSousGroupeDto groupeDto;
    private Set<TPSousGroupeDto> tpDto;

    public SousGroupeParticipeADto() {

    }

    public SousGroupeParticipeADto(Integer idSousGroupe, String nomSousGroupe, Integer nbEtuSousGroupe, GroupeSousGroupeDto groupeDto,
                         Set<TPSousGroupeDto> tpDto) {
        this.idSousGroupe = idSousGroupe;
        this.nomSousGroupe = nomSousGroupe;
        this.nbEtuSousGroupe = nbEtuSousGroupe;
        this.groupeDto = groupeDto;
        this.tpDto = tpDto;
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
