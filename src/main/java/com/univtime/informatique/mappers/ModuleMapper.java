package com.univtime.informatique.mappers;

import com.univtime.informatique.dto.moduleDto.ModuleDto;
import com.univtime.informatique.entities.ModuleEntity;

public final class ModuleMapper {

    private ModuleMapper() {
        // Private constructor pour éviter l'instantiation
    }

    public static ModuleDto toDto(ModuleEntity entity) {
        if (entity == null) return null;
        ModuleDto dto = new ModuleDto();
        dto.setIdModule(entity.getIdModule());
        dto.setNomModule(entity.getNomModule());
        return dto;
    }

    public static ModuleEntity toEntity(ModuleDto dto) {
        if (dto == null) return null;
        ModuleEntity entity = new ModuleEntity();
        entity.setIdModule(dto.getIdModule());
        entity.setNomModule(dto.getNomModule());
        return entity;
    }
}
