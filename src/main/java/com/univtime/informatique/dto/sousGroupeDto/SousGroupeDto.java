package com.univtime.informatique.dto.sousGroupeDto;

public class SousGroupeDto {
    private Integer idSousGroupe;
    private String nomSousGroupe;
    private Integer nbEtuSousGroupe;
    private GroupeSousGroupeDto groupeDto;

    // Getters et Setters
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

    public void setGroupeDto(GroupeSousGroupeDto groupeSousGroupeDto) {
        this.groupeDto = groupeSousGroupeDto;
    }
}
