package com.univtime.informatique.dto.sousGroupeDto;

import com.univtime.informatique.dto.ids.TDIdDto;

import java.util.Objects;
import java.util.Set;

public class GroupeSousGroupeDto {
    private Integer idGroupe;
    private String nomGroupe;
    private Integer nbEtuGroupe;
    private Integer promoId;
    private Set<TDIdDto> tdIds;
    // private Set<Integer> sousGroupeIds;

    public GroupeSousGroupeDto() {
    }

    public GroupeSousGroupeDto(Integer idGroupe,
                               String nomGroupe,
                               Integer nbEtuGroupe,
                               Integer promoId,
                               Set<TDIdDto> tdIds) {
        this.idGroupe = idGroupe;
        this.nomGroupe = nomGroupe;
        this.nbEtuGroupe = nbEtuGroupe;
        this.promoId = promoId;
        this.tdIds = tdIds;
    }

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

    public Integer getPromoId() {
        return promoId;
    }

    public void setPromoId(Integer promoId) {
        this.promoId = promoId;
    }

    public Set<TDIdDto> getTdIds() {
        return tdIds;
    }

    public void setTdIds(Set<TDIdDto> tdIds) {
        this.tdIds = tdIds;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idGroupe);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        GroupeSousGroupeDto groupeDto = (GroupeSousGroupeDto) obj;
        return Objects.equals(idGroupe, groupeDto.idGroupe);
    }
}
