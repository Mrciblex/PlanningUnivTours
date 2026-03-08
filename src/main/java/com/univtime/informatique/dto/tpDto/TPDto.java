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

package com.univtime.informatique.dto.tpDto;

import com.univtime.informatique.entities.ids.TPId;

import java.util.Objects;

public class TPDto {
    private ProfesseurTPDto professeurDto;
    private SousGroupeTPDto sousGroupeDto;
    private ComposanteTPDto composanteDto;
    private RepartitionSemaineTPDto repartitionSemaineDto;

    public TPDto() {

    }

    public TPDto(ProfesseurTPDto professeurDto,
                 SousGroupeTPDto sousGroupeDto,
                 ComposanteTPDto composanteDto,
                 RepartitionSemaineTPDto repartitionSemaineDto) {
        this.professeurDto = professeurDto;
        this.sousGroupeDto = sousGroupeDto;
        this.composanteDto = composanteDto;
        this.repartitionSemaineDto = repartitionSemaineDto;
    }

    public ProfesseurTPDto getProfesseurDto() {
        return professeurDto;
    }

    public void setProfesseurDto(ProfesseurTPDto professeurDto) {
        this.professeurDto = professeurDto;
    }

    public SousGroupeTPDto getSousGroupeDto() {
        return sousGroupeDto;
    }

    public void setSousGroupeDto(SousGroupeTPDto sousGroupeDto) {
        this.sousGroupeDto = sousGroupeDto;
    }

    public ComposanteTPDto getComposanteDto() {
        return composanteDto;
    }

    public void setComposanteDto(ComposanteTPDto composanteDto) {
        this.composanteDto = composanteDto;
    }

    public RepartitionSemaineTPDto getRepartitionSemaineDto() {
        return repartitionSemaineDto;
    }

    public void setRepartitionSemaineDto(RepartitionSemaineTPDto repartitionSemaineDto) {
        this.repartitionSemaineDto = repartitionSemaineDto;
    }

    public TPId getTPId() {
        return new TPId(
            professeurDto.getIdProf(),
            sousGroupeDto.getIdSousGroupe(),
            composanteDto.getIdComposante(),
            repartitionSemaineDto.getIdRepartitionSemaine()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TPDto tpDto = (TPDto) o;
        return Objects.equals(professeurDto, tpDto.professeurDto) && Objects.equals(sousGroupeDto, tpDto.sousGroupeDto) && Objects.equals(composanteDto, tpDto.composanteDto) && Objects.equals(repartitionSemaineDto, tpDto.repartitionSemaineDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(professeurDto, sousGroupeDto, composanteDto, repartitionSemaineDto);
    }
}
