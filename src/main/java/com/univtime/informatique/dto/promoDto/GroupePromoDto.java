package com.univtime.informatique.dto.promoDto;

import com.univtime.informatique.dto.groupeDto.SousGroupeGroupeDto;
import com.univtime.informatique.dto.groupeDto.TDGroupeDto;

import java.util.Objects;
import java.util.Set;

public class GroupePromoDto {
    private Integer idGroupe;
    private String nomGroupe;
    private Integer nbEtuGroupe;
    private Set<TDGroupeDto> tdDto;
    private Set<SousGroupeGroupeDto> sousGroupeDto;

    public GroupePromoDto() {
    }

    public void GroupeDto(Integer idGroupe, String nomGroupe, Integer nbEtuGroupe, Set<TDGroupeDto> tdDto, Set<SousGroupeGroupeDto> sousGroupeDto) {
        this.idGroupe = idGroupe;
        this.nomGroupe = nomGroupe;
        this.nbEtuGroupe = nbEtuGroupe;
        this.tdDto = tdDto;
        this.sousGroupeDto = sousGroupeDto;
    }

    // Getters et Setters
    public Integer getIdGroupe() {
        return idGroupe;
    }

    public void setIdGroupe(Integer idGroupe) {
        this.idGroupe = idGroupe;
    }

    public String getNomGroupe() {
        return nomGroupe;
    }

    public void setNomGroupe(String nomGroupe) {
        this.nomGroupe = nomGroupe;
    }

    public Integer getNbEtuGroupe() {
        return nbEtuGroupe;
    }

    public void setNbEtuGroupe(Integer nbEtuGroupe) {
        this.nbEtuGroupe = nbEtuGroupe;
    }

    public Set<TDGroupeDto> getTdDto() {
        return tdDto;
    }

    public void setTdDto(Set<TDGroupeDto> tdDto) {
        this.tdDto = tdDto;
    }

    public Set<SousGroupeGroupeDto> getSousGroupeDto() {
        return sousGroupeDto;
    }

    public void setSousGroupeDto(Set<SousGroupeGroupeDto> sousGroupeDto) {
        this.sousGroupeDto = sousGroupeDto;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idGroupe);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        GroupePromoDto groupeDto = (GroupePromoDto) obj;
        return Objects.equals(idGroupe, groupeDto.idGroupe);
    }
}
