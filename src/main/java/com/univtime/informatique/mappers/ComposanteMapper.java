package com.univtime.informatique.mappers;

import com.univtime.informatique.dtos.ComposanteDto;
import com.univtime.informatique.entities.ComposanteEntity;

public final class ComposanteMapper {

    private ComposanteMapper() {
        // Private constructor pour éviter l'instantiation
    }

    public static ComposanteDto toDto(ComposanteEntity entity) {
        return entity == null ? null : new ComposanteDto()
                .setIdComposante(entity.getIdComposante())
                .setNomComposante(entity.getNomComposante())
                .setVolumeHoraireTotal(entity.getVolumeHoraireTotal())
                .setVolumeHoraireCM(entity.getVolumeHoraireCM())
                .setVolumeHoraireTD(entity.getVolumeHoraireTD())
                .setVolumeHoraireTP(entity.getVolumeHoraireTP())
                .setBlocHoraireCM(entity.getBlocHoraireCM())
                .setBlocHoraireTD(entity.getBlocHoraireTD())
                .setBlocHoraireTP(entity.getBlocHoraireTP())
                .setIdModule(entity.getIdModule());
    }

    public static ComposanteEntity toEntity(ComposanteDto dto) {
        return dto == null ? null : new ComposanteEntity()
                .setIdComposante(dto.getIdComposante())
                .setNomComposante(dto.getNomComposante())
                .setVolumeHoraireTotal(dto.getVolumeHoraireTotal())
                .setVolumeHoraireCM(dto.getVolumeHoraireCM())
                .setVolumeHoraireTD(dto.getVolumeHoraireTD())
                .setVolumeHoraireTP(dto.getVolumeHoraireTP())
                .setBlocHoraireCM(dto.getBlocHoraireCM())
                .setBlocHoraireTD(dto.getBlocHoraireTD())
                .setBlocHoraireTP(dto.getBlocHoraireTP())
                .setIdModule(dto.getIdModule());
    }
}

