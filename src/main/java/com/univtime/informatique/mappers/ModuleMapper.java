package com.univtime.informatique.mappers;

import com.univtime.informatique.dto.moduleDto.ComposanteModuleDto;
import com.univtime.informatique.dto.moduleDto.ModuleDto;
import com.univtime.informatique.dto.moduleDto.PromoEstComposeeModuleDto;
import com.univtime.informatique.entities.ComposanteEntity;
import com.univtime.informatique.entities.ModuleEntity;
import com.univtime.informatique.entities.PromoEstComposeeEntity;

import java.util.stream.Collectors;

public class ModuleMapper {

    private ModuleMapper() {

    }

    public static ModuleDto toDto(ModuleEntity entity) {
        if (entity == null) {
            return null;
        }
        ModuleDto dto = new ModuleDto();
        dto.setIdModule(entity.getIdModule());
        dto.setNomModule(entity.getNomModule());
        dto.setPromoEstComposeeDto(entity.getPromoEstComposeeEntities()
                .stream()
                .map(ModuleMapper::promoEstComposeeToDto)
                .collect(Collectors.toSet()));
        dto.setComposanteDto(entity.getComposanteEntities()
                .stream()
                .map(ModuleMapper::composanteToDto)
                .collect(Collectors.toSet()));
        return dto;
    }

    public static ModuleEntity toEntity(ModuleDto dto) {
        if (dto == null) {
            return null;
        }
        ModuleEntity entity = new ModuleEntity();
        entity.setIdModule(dto.getIdModule());
        entity.setNomModule(dto.getNomModule());
        return entity;
    }

    private static PromoEstComposeeModuleDto promoEstComposeeToDto(PromoEstComposeeEntity entity) {
        PromoEstComposeeModuleDto promoEstComposee = new PromoEstComposeeModuleDto();
        if (entity != null) {
            promoEstComposee.setIdPromoEstComposee(entity.getIdPromoEstComposee());
        }
        return promoEstComposee;
    }

    private static ComposanteModuleDto composanteToDto(ComposanteEntity entity) {
        ComposanteModuleDto composante = new ComposanteModuleDto();
        if (entity != null) {
            composante.setIdComposante(entity.getIdComposante());
        }
        return composante;
    }
}
