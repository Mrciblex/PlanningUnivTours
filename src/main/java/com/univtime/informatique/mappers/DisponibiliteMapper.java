package com.univtime.informatique.mappers;

import com.univtime.informatique.dtos.disponibiliteDto.DisponibiliteDto;
import com.univtime.informatique.entities.DisponibiliteEntity;

public final class DisponibiliteMapper {

    private DisponibiliteMapper() {
        // Private constructor pour éviter l'instantiation
    }

    public static DisponibiliteDto toDto(DisponibiliteEntity entity) {
        return entity == null ? null : new DisponibiliteDto()
                .setIdDispo(entity.getIdDispo())
                .setHeureDebutDispo(entity.getHeureDebutDispo())
                .setHeureFinDispo(entity.getHeureFinDispo())
                .setIdJour(entity.getIdJour());
    }

    public static DisponibiliteEntity toEntity(DisponibiliteDto dto) {
        return dto == null ? null : new DisponibiliteEntity()
                .setIdDispo(dto.getIdDispo())
                .setHeureDebutDispo(dto.getHeureDebutDispo())
                .setHeureFinDispo(dto.getHeureFinDispo())
                .setIdJour(dto.getIdJour());
    }
}
