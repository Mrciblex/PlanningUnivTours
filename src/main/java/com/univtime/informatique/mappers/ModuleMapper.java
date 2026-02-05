package com.univtime.informatique.mappers;

import com.univtime.informatique.dtos.moduleDto.ModuleDto;
import com.univtime.informatique.entities.ModuleEntity;

public final class ModuleMapper {

    private ModuleMapper() {
        // Private constructor pour éviter l'instantiation
    }

    public static ModuleDto toDto(ModuleEntity entity) {
        return entity == null ? null : new ModuleDto()
                .setIdModule(entity.getIdModule())
                .setNomModule(entity.getNomModule());
    }

    public static ModuleEntity toEntity(ModuleDto dto) {
        return dto == null ? null : new ModuleEntity()
                .setIdModule(dto.getIdModule())
                .setNomModule(dto.getNomModule());
    }
}
