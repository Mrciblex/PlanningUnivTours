package com.univtime.informatique.dto.groupeDto;

import java.util.Objects;
import java.util.Set;

public class GroupeDto {
    private Integer idGroupe;
    private String nomGroupe;
    private Integer nbEtuGroupe;
    private PromoGroupeDto promoDto;
    private Set<TDGroupeDto> tdDto;
    private Set<SousGroupeGroupeDto> sousGroupeDto;

    public GroupeDto() {
    }

    public GroupeDto(Integer idGroupe, String nomGroupe, Integer nbEtuGroupe, PromoGroupeDto promoDto, Set<TDGroupeDto> tdDto, Set<SousGroupeGroupeDto> sousGroupeDto) {
        this.idGroupe = idGroupe;
        this.nomGroupe = nomGroupe;
        this.nbEtuGroupe = nbEtuGroupe;
        this.promoDto = promoDto;
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

    public PromoGroupeDto getPromoDto() {
        return promoDto;
    }

    public void setPromoDto(PromoGroupeDto promoDto) {
        this.promoDto = promoDto;
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
        GroupeDto groupeDto = (GroupeDto) obj;
        return Objects.equals(idGroupe, groupeDto.idGroupe);
    }

}
