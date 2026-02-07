package com.univtime.informatique.mappers;

import com.univtime.informatique.dto.composanteDto.ComposanteDto;
import com.univtime.informatique.dto.composanteDto.ModuleComposanteDto;
import com.univtime.informatique.entities.ComposanteEntity;
import com.univtime.informatique.entities.ModuleEntity;

public final class ComposanteMapper {

    private ComposanteMapper() {
        // Private constructor pour éviter l'instantiation
    }

    public static ComposanteDto toDto(ComposanteEntity entity) {
        if (entity == null) return null;
        ComposanteDto dto = new ComposanteDto();
        dto.setIdComposante(entity.getIdComposante());
        dto.setNomComposante(entity.getNomCoposante());
        dto.setVolumeHoraireTotal(entity.getVolumeHoraireTotal());
        dto.setVolumeHoraireCM(entity.getVolumeHoraireCM());
        dto.setVolumeHoraireTD(entity.getVolumeHoraireTD());
        dto.setVolumeHoraireTP(entity.getVolumeHoraireTP());
        dto.setBlocHoraireCM(entity.getBlocHoraireCM());
        dto.setBlocHoraireTD(entity.getBlocHoraireTD());
        dto.setBlocHoraireTP(entity.getBlocHoraireTP());
        if (entity.getModule() != null) {
            ModuleComposanteDto m = new ModuleComposanteDto();
            m.setIdModule(entity.getModule().getIdModule());
            m.setNomModule(entity.getModule().getNomModule());
            dto.setModuleDto(m);
        }
        return dto;
    }

    public static ComposanteEntity toEntity(ComposanteDto dto) {
        if (dto == null) return null;
        ComposanteEntity entity = new ComposanteEntity();
        entity.setIdComposante(dto.getIdComposante());
        entity.setNomCoposante(dto.getNomComposante());
        entity.setVolumeHoraireTotal(dto.getVolumeHoraireTotal());
        entity.setVolumeHoraireCM(dto.getVolumeHoraireCM());
        entity.setVolumeHoraireTD(dto.getVolumeHoraireTD());
        entity.setVolumeHoraireTP(dto.getVolumeHoraireTP());
        entity.setBlocHoraireCM(dto.getBlocHoraireCM());
        entity.setBlocHoraireTD(dto.getBlocHoraireTD());
        entity.setBlocHoraireTP(dto.getBlocHoraireTP());
        if (dto.getModuleDto() != null) {
            ModuleEntity m = new ModuleEntity();
            m.setIdModule(dto.getModuleDto().getIdModule());
            entity.setModule(m);
        }
        return entity;
    }
}
