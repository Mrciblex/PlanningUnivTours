package com.univtime.informatique.dto.promoDto;

import com.univtime.informatique.dto.groupeDto.SousGroupeGroupeDto;
import com.univtime.informatique.dto.groupeDto.TDGroupeDto;

import java.util.Objects;
import java.util.Set;

public class GroupePromoDto {
    private Integer idGroupe;
    private String nomGroupe;
    private Integer nbEtuGroupe;

    public GroupePromoDto() {
    }

    public void GroupeDto(
            Integer idGroupe,
            String nomGroupe,
            Integer nbEtuGroupe) {
        this.idGroupe = idGroupe;
        this.nomGroupe = nomGroupe;
        this.nbEtuGroupe = nbEtuGroupe;
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
