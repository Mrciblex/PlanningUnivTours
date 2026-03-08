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

package com.univtime.informatique.dto.moduleDto;

import java.util.Objects;
import java.util.Set;

public class ModuleDto {
    private Integer idModule;
    private String nomModule;
    private Set<PromoEstComposeeModuleDto> promoEstComposeeDto;
    private Set<ComposanteModuleDto> composanteDto;

    public ModuleDto() {

    }

    public ModuleDto(Integer idModule,
                     String nomModule,
                     Set<PromoEstComposeeModuleDto> promoEstComposeeDto,
                     Set<ComposanteModuleDto> composanteDto) {
        this.idModule = idModule;
        this.nomModule = nomModule;
        this.promoEstComposeeDto = promoEstComposeeDto;
        this.composanteDto = composanteDto;
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

    public Set<PromoEstComposeeModuleDto> getPromoEstComposeeDto() {
        return promoEstComposeeDto;
    }

    public void setPromoEstComposeeDto(Set<PromoEstComposeeModuleDto> promoEstComposeeDto) {
        this.promoEstComposeeDto = promoEstComposeeDto;
    }

    public Set<ComposanteModuleDto> getComposanteDto() {
        return composanteDto;
    }

    public void setComposanteDto(Set<ComposanteModuleDto> composanteDto) {
        this.composanteDto = composanteDto;
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
        ModuleDto moduleDto = (ModuleDto) obj;
        return Objects.equals(idModule, moduleDto.idModule);
    }
}

