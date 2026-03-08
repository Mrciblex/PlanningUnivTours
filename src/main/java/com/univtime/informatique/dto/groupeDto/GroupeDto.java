/*
 * Copyright (c) 2026 Ademi Musa. Tous droits réservés.
 * Projet : univTime - Logiciel de gestion d'emplois du temps.
 *
 * Ce code source et l'algorithme associé sont la propriété exclusive de l'auteur.
 * Toute reproduction, modification ou distribution non autorisée, par quelque moyen que ce soit, est strictement interdite.
 *
 * Ce fichier fait partie du projet univTime, concédé sous licence d'usage
 * restreinte à l'Université de Tours, 37000, en France.
 */

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
