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

    public GroupeTDDto(Integer idGroupe) {
        this.idGroupe = idGroupe;
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
