package com.univtime.informatique.dto.tdDto;

import java.util.Objects;
import java.util.Set;

public class GroupeTDDto {
    private Integer idGroupe;
    private String nomGroupe;
    private Integer nbEtuGroupe;
    private Integer promoId;
    // private Set<TDIdDto> tdIds;
    private Set<Integer> sousGroupeIds;

    public GroupeTDDto() {
    }

    public GroupeTDDto(Integer idGroupe,
                       String nomGroupe,
                       Integer nbEtuGroupe,
                       Integer promoId,
                       Set<Integer> sousGroupeIds) {
        this.idGroupe = idGroupe;
        this.nomGroupe = nomGroupe;
        this.nbEtuGroupe = nbEtuGroupe;
        this.promoId = promoId;
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

    public Integer getPromoId() {
        return promoId;
    }

    public void setPromoId(Integer promoId) {
        this.promoId = promoId;
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
        GroupeTDDto groupeDto = (GroupeTDDto) obj;
        return Objects.equals(idGroupe, groupeDto.idGroupe);
    }
}
