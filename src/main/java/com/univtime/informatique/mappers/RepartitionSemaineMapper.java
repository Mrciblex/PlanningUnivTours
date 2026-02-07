package com.univtime.informatique.mappers;

import com.univtime.informatique.dto.repartitionSemaineDto.RepartitionSemaineDto;
import com.univtime.informatique.entities.RepartitionSemaineEntity;

public final class RepartitionSemaineMapper {

    private RepartitionSemaineMapper() {}

    public static RepartitionSemaineDto toDto(RepartitionSemaineEntity entity) {
        if (entity == null) return null;
        RepartitionSemaineDto dto = new RepartitionSemaineDto();
        dto.setIdRepartitionSemaine(entity.getIdRepartitionSemaine());
        dto.setNumSemaine(entity.getNumSemaine());
        dto.setQteTypeCours(entity.getQteTypeCours());
        return dto;
    }

    public static RepartitionSemaineEntity toEntity(RepartitionSemaineDto dto) {
        if (dto == null) return null;
        RepartitionSemaineEntity entity = new RepartitionSemaineEntity();
        entity.setIdRepartitionSemaine(dto.getIdRepartitionSemaine());
        entity.setNumSemaine(dto.getNumSemaine());
        entity.setQteTypeCours(dto.getQteTypeCours());
        return entity;
    }
}
