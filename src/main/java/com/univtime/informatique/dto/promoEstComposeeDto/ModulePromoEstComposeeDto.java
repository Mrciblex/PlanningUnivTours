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

package com.univtime.informatique.dto.promoEstComposeeDto;

import java.util.Objects;
import java.util.Set;

public class ModulePromoEstComposeeDto {
    private Integer idModule;
    private String nomModule;
    // private Set<PromoEstComposeeIdDto> promoEstComposeeIds;
    private Set<Integer> composanteIds;


    public ModulePromoEstComposeeDto() {

    }

    public ModulePromoEstComposeeDto(Integer idModule,
                                     String nomModule,
                                     Set<Integer> composanteIds) {
        this.idModule = idModule;
        this.nomModule = nomModule;
        this.composanteIds = composanteIds;
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

    public Set<Integer> getComposanteIds() {
        return composanteIds;
    }

    public void setComposanteIds(Set<Integer> composanteIds) {
        this.composanteIds = composanteIds;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idModule);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ModulePromoEstComposeeDto moduleDto = (ModulePromoEstComposeeDto) obj;
        return Objects.equals(idModule, moduleDto.idModule);
    }
}
