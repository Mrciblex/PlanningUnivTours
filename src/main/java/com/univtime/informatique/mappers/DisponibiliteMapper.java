package com.univtime.informatique.mappers;

import com.univtime.informatique.dto.disponibiliteDto.DisponibiliteDto;
import com.univtime.informatique.dto.disponibiliteDto.JourDisponibiliteDto;
import com.univtime.informatique.entities.DisponibiliteEntity;
import com.univtime.informatique.entities.JourEntity;

public final class DisponibiliteMapper {

    private DisponibiliteMapper() {
        // Private constructor pour éviter l'instantiation
    }

    public static DisponibiliteDto toDto(DisponibiliteEntity entity) {
        if (entity == null) return null;
        DisponibiliteDto dto = new DisponibiliteDto();
        dto.setIdDispo(entity.getIdDispo());
        dto.setHeureDebutDispo(entity.getHeureDebutDispo());
        dto.setHeureFinDispo(entity.getHeureFinDispo());
        if (entity.getJour() != null) {
            JourDisponibiliteDto j = new JourDisponibiliteDto();
            j.setIdJour(entity.getJour().getIdJour());
            j.setJourSemaine(entity.getJour().getJourSemaine());
            dto.setJourDto(j);
        }
        return dto;
    }

    public static DisponibiliteEntity toEntity(DisponibiliteDto dto) {
        if (dto == null) return null;
        DisponibiliteEntity entity = new DisponibiliteEntity();
        entity.setIdDispo(dto.getIdDispo());
        entity.setHeureDebutDispo(dto.getHeureDebutDispo());
        entity.setHeureFinDispo(dto.getHeureFinDispo());
        if (dto.getJourDto() != null) {
            JourEntity j = new JourEntity();
            j.setIdJour(dto.getJourDto().getIdJour());
            entity.setJour(j);
        }
        return entity;
    }
}
