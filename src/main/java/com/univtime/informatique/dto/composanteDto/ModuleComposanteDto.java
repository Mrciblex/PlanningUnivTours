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

package com.univtime.informatique.dto.composanteDto;

import com.univtime.informatique.dto.idsDto.PromoEstComposeeIdDto;

import java.util.Objects;
import java.util.Set;

public class ModuleComposanteDto {
    private Integer idModule;
    private String nomModule;

    private Set<PromoEstComposeeIdDto> promoEstComposeeIds;
    // private Set<Integer> composanteIds;


    public ModuleComposanteDto() {

    }

    public ModuleComposanteDto(Integer idModule,
                               String nomModule,
                               Set<PromoEstComposeeIdDto> promoEstComposeeIds) {
        this.idModule = idModule;
        this.nomModule = nomModule;
        this.promoEstComposeeIds = promoEstComposeeIds;
    }

    public Integer getIdModule() {
        return idModule;
    }

    public void setIdModule(Integer idModule) {
        this.idModule = idModule;
    }

    public String getNomModule() {
        return nomModule;
    }

    public void setNomModule(String nomModule) {
        this.nomModule = nomModule;
    }

    public Set<PromoEstComposeeIdDto> getPromoEstComposeeIds() {
        return promoEstComposeeIds;
    }

    public void setPromoEstComposeeIds(Set<PromoEstComposeeIdDto> promoEstComposeeIds) {
        this.promoEstComposeeIds = promoEstComposeeIds;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idModule);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        ModuleComposanteDto moduleDto = (ModuleComposanteDto) obj;
        return Objects.equals(idModule, moduleDto.idModule);
    }
}

