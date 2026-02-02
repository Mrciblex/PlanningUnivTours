package com.univtime.informatique.mappers;

import com.univtime.informatique.dtos.SalleDto;
import com.univtime.informatique.entities.SalleEntity;

public final class SalleMapper {

    private SalleMapper() {
        // Private constructor pour éviter l'instantiation
    }

    public static SalleDto toDto(SalleEntity entity) {
        return entity == null ? null : new SalleDto()
                .setIdSalle(entity.getIdSalle())
                .setNbPlace(entity.getNbPlace())
                .setSalleMachine(entity.isSalleMachine())
                .setNbPC(entity.getNbPC());
    }

    public static SalleEntity toEntity(SalleDto dto) {
        return dto == null ? null : new SalleEntity()
                .setIdSalle(dto.getIdSalle())
                .setNbPlace(dto.getNbPlace())
                .setSalleMachine(dto.isSalleMachine())
                .setNbPC(dto.getNbPC());
    }
}



