package com.univtime.informatique.mappers;

import com.univtime.informatique.dto.salleDto.SalleDto;
import com.univtime.informatique.entities.SalleEntity;

public final class SalleMapper {

    private SalleMapper() {
        // Private constructor pour éviter l'instantiation
    }

    public static SalleDto toDto(SalleEntity entity) {
        if (entity == null) return null;
        SalleDto dto = new SalleDto();
        dto.setIdSalle(entity.getIdSalle());
        dto.setNbPlace(entity.getNbPlace());
        dto.setSalleMachine(entity.isSalleMachine());
        dto.setNbPC(entity.getNbPC());
        return dto;
    }

    public static SalleEntity toEntity(SalleDto dto) {
        if (dto == null) return null;
        SalleEntity entity = new SalleEntity();
        entity.setIdSalle(dto.getIdSalle());
        entity.setNbPlace(dto.getNbPlace());
        entity.setSalleMachine(dto.isSalleMachine());
        entity.setNbPC(dto.getNbPC());
        return entity;
    }
}
