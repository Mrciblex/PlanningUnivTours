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

package com.univtime.informatique.dto.participeADto;

import com.univtime.informatique.entities.ids.ParticipeAId;

import java.util.Objects;

public class ParticipeADto {
    private SousGroupeParticipeADto sousGroupeDto;
    private CoursParticipeADto coursDto;

    public ParticipeADto() {

    }

    public ParticipeADto(SousGroupeParticipeADto sousGroupeDto,
                         CoursParticipeADto coursDto) {
        this.sousGroupeDto = sousGroupeDto;
        this.coursDto = coursDto;
    }

    public SousGroupeParticipeADto getSousGroupeDto() {
        return sousGroupeDto;
    }

    public void setSousGroupeDto(SousGroupeParticipeADto sousGroupeDto) {
        this.sousGroupeDto = sousGroupeDto;
    }

    public CoursParticipeADto getCoursDto() {
        return coursDto;
    }

    public void setCoursDto(CoursParticipeADto coursDto) {
        this.coursDto = coursDto;
    }

    public ParticipeAId getParticipeAId() {
        return new ParticipeAId(
                sousGroupeDto.getGroupeId(),
                coursDto.getIdCours()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ParticipeADto that = (ParticipeADto) o;
        return Objects.equals(sousGroupeDto, that.sousGroupeDto)
                && Objects.equals(coursDto, that.coursDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sousGroupeDto, coursDto);
    }
}
