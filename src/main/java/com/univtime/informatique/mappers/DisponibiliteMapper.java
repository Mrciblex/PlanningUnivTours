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

package com.univtime.informatique.mappers;

import com.univtime.informatique.dto.disponibiliteDto.DisponibiliteDto;
import com.univtime.informatique.dto.disponibiliteDto.JourDisponibiliteDto;
import com.univtime.informatique.entities.DisponibiliteEntity;
import com.univtime.informatique.entities.JourEntity;

public class DisponibiliteMapper {

    private DisponibiliteMapper() {

    }

    public static DisponibiliteDto toDto(DisponibiliteEntity entity) {
        if (entity == null) {
            return null;
        }
        DisponibiliteDto dto = new DisponibiliteDto();
        dto.setIdDispo(entity.getIdDispo());
        dto.setHeureDebutDispo(entity.getHeureDebutDispo());
        dto.setHeureFinDispo(entity.getHeureFinDispo());
        dto.setJourDto(jourToDto(entity.getJour()));
        return dto;
    }

    public static DisponibiliteEntity toEntity(DisponibiliteDto dto) {
        if (dto == null) {
            return null;
        }
        DisponibiliteEntity entity = new DisponibiliteEntity();
        entity.setIdDispo(dto.getIdDispo());
        entity.setHeureDebutDispo(dto.getHeureDebutDispo());
        entity.setHeureFinDispo(dto.getHeureFinDispo());
        return entity;
    }

    private static JourDisponibiliteDto jourToDto(JourEntity entity) {
        JourDisponibiliteDto jour = new JourDisponibiliteDto();
        if (entity != null) {
            jour.setIdJour(entity.getIdJour());
            jour.setJourSemaine(entity.getJourSemaine());

            /*
                Jour
             */
            jour.setIdJour(entity.getIdJour());
        }
        return jour;
    }
}
