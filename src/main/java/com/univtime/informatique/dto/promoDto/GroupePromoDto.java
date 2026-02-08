package com.univtime.informatique.dto.promoDto;

import com.univtime.informatique.dto.groupeDto.SousGroupeGroupeDto;
import com.univtime.informatique.dto.groupeDto.TDGroupeDto;
import com.univtime.informatique.dto.ids.TDIdDto;

import java.util.Objects;
import java.util.Set;

public class GroupePromoDto {
    private Integer idGroupe;
    private String nomGroupe;
    private Integer nbEtuGroupe;
    // private Integer promoId;
    private Set<TDIdDto> tdIds;
    private Set<Integer> sousGroupeIds;

    public GroupePromoDto() {
    }

    public GroupePromoDto(Integer idGroupe,
                          String nomGroupe,
                          Integer nbEtuGroupe,
                          Set<TDIdDto> tdIds,
                          Set<Integer> sousGroupeIds) {
        this.idGroupe = idGroupe;
        this.nomGroupe = nomGroupe;
        this.nbEtuGroupe = nbEtuGroupe;
        this.tdIds = tdIds;
        this.sousGroupeIds = sousGroupeIds;
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

    public Set<TDIdDto> getTdIds() {
        return tdIds;
    }

    public void setTdIds(Set<TDIdDto> tdIds) {
        this.tdIds = tdIds;
    }

    public Set<Integer> getSousGroupeIds() {
        return sousGroupeIds;
    }

    public void setSousGroupeIds(Set<Integer> sousGroupeIds) {
        this.sousGroupeIds = sousGroupeIds;
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
