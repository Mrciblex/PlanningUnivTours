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

package com.univtime.informatique.dto.promoDto;

import com.univtime.informatique.dto.idsDto.PromoEstComposeeIdDto;

import java.util.Objects;
import java.util.Set;

public class PromoEstComposeePromoDto {
    private Integer idModule;
    private String nomModule;
    private Set<PromoEstComposeeIdDto> promoEstComposeeModuleIds;
    private Set<Integer> composanteModuleIds;

    public PromoEstComposeePromoDto() {

    }

    public PromoEstComposeePromoDto(Integer idModule,
                                    String nomModule,
                                    Set<PromoEstComposeeIdDto> promoEstComposeeModuleIds,
                                    Set<Integer> composanteModuleIds) {
        this.idModule = idModule;
        this.nomModule = nomModule;
        this.promoEstComposeeModuleIds = promoEstComposeeModuleIds;
        this.composanteModuleIds = composanteModuleIds;
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

    public Set<PromoEstComposeeIdDto> getPromoEstComposeeModuleIds() {
        return promoEstComposeeModuleIds;
    }

    public void setPromoEstComposeeModuleIds(Set<PromoEstComposeeIdDto> promoEstComposeeModuleIds) {
        this.promoEstComposeeModuleIds = promoEstComposeeModuleIds;
    }

    public Set<Integer> getComposanteModuleIds() {
        return composanteModuleIds;
    }

    public void setComposanteModuleIds(Set<Integer> composanteModuleIds) {
        this.composanteModuleIds = composanteModuleIds;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PromoEstComposeePromoDto that = (PromoEstComposeePromoDto) o;
        return Objects.equals(idModule, that.idModule);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idModule);
    }
}
