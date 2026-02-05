package com.univtime.informatique.dtos.groupeDto;

public class GroupeDto {
    private Integer idGroupe;
    private String nomGroupe;
    private Integer nbEtuGroupe;
    private PromoGroupeDto promoDto;

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

    public void setPromoDto(PromoGroupeDto promoGroupeDto) {
        this.promoDto = promoGroupeDto;
    }
}
