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

import com.univtime.informatique.entities.ids.PromoEstComposeeId;

import java.util.Objects;

public class PromoEstComposeeDto {
    private PromoPromoEstComposeeDto promoDto;
    private ModulePromoEstComposeeDto moduleDto;

    public PromoEstComposeeDto() {

    }

    public PromoEstComposeeDto(PromoPromoEstComposeeDto promoDto,
                               ModulePromoEstComposeeDto moduleDto) {
        this.promoDto = promoDto;
        this.moduleDto = moduleDto;
    }

    public PromoPromoEstComposeeDto getPromoDto() {
        return promoDto;
    }

    public void setPromoDto(PromoPromoEstComposeeDto promoDto) {
        this.promoDto = promoDto;
    }

    public ModulePromoEstComposeeDto getModuleDto() {
        return moduleDto;
    }

    public void setModuleDto(ModulePromoEstComposeeDto moduleDto) {
        this.moduleDto = moduleDto;
    }

    public PromoEstComposeeId getPromoEstComposeeId() {
        return new PromoEstComposeeId(
                promoDto.getIdPromo(),
                moduleDto.getIdModule()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        PromoEstComposeeDto that = (PromoEstComposeeDto) o;
        return Objects.equals(promoDto, that.promoDto)
                && Objects.equals(moduleDto, that.moduleDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(promoDto, moduleDto);
    }
}
