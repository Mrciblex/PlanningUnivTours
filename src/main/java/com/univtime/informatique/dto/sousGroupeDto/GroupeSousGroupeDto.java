package com.univtime.informatique.dto.sousGroupeDto;

import java.util.Objects;

public class GroupeSousGroupeDto {
    private Integer idGroupe;
    private String nomGroupe;
    private Integer nbEtuGroupe;

    public GroupeSousGroupeDto() {
    }

    public GroupeSousGroupeDto(
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
        GroupeSousGroupeDto groupeDto = (GroupeSousGroupeDto) obj;
        return Objects.equals(idGroupe, groupeDto.idGroupe);
    }
}
