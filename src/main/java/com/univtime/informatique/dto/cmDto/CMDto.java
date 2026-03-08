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

package com.univtime.informatique.dto.cmDto;

import com.univtime.informatique.entities.ids.CMId;

import java.util.Objects;

public class CMDto {
    private ProfesseurCMDto professeurDto;
    private PromoCMDto promoDto;
    private ComposanteCMDto composanteDto;
    private RepartitionSemaineCMDto repartitionSemaineDto;

    public CMDto() {

    }

    public CMDto(ProfesseurCMDto professeurDto, PromoCMDto promoDto, ComposanteCMDto composanteDto, RepartitionSemaineCMDto repartitionSemaineDto) {
        this.professeurDto = professeurDto;
        this.promoDto = promoDto;
        this.composanteDto = composanteDto;
        this.repartitionSemaineDto = repartitionSemaineDto;
    }

    public ProfesseurCMDto getProfesseurDto() {
        return professeurDto;
    }

    public void setProfesseurDto(ProfesseurCMDto professeurDto) {
        this.professeurDto = professeurDto;
    }

    public PromoCMDto getPromoDto() {
        return promoDto;
    }

    public void setPromoDto(PromoCMDto promoDto) {
        this.promoDto = promoDto;
    }

    public ComposanteCMDto getComposanteDto() {
        return composanteDto;
    }

    public void setComposanteDto(ComposanteCMDto composanteDto) {
        this.composanteDto = composanteDto;
    }

    public RepartitionSemaineCMDto getRepartitionSemaineDto() {
        return repartitionSemaineDto;
    }

    public void setRepartitionSemaineDto(RepartitionSemaineCMDto repartitionSemaineDto) {
        this.repartitionSemaineDto = repartitionSemaineDto;
    }

    public CMId getCMId() {
        return new CMId(
                professeurDto.getIdProf(),
                promoDto.getIdPromo(),
                composanteDto.getIdComposante(),
                repartitionSemaineDto.getIdRepartitionSemaine()
        );
    }


    @Override
    public int hashCode() {
        return Objects.hash(professeurDto, promoDto, composanteDto, repartitionSemaineDto);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        CMDto cmDto = (CMDto) obj;
        return Objects.equals(professeurDto, cmDto.professeurDto)
                && Objects.equals(promoDto, cmDto.promoDto)
                && Objects.equals(composanteDto, cmDto.composanteDto)
                && Objects.equals(repartitionSemaineDto, cmDto.repartitionSemaineDto);
    }
}
