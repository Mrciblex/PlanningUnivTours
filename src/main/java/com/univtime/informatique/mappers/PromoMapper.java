package com.univtime.informatique.mappers;

import com.univtime.informatique.dto.promoDto.PromoDto;
import com.univtime.informatique.entities.PromoEntity;

public final class PromoMapper {

    private PromoMapper() {
        // Private constructor pour éviter l'instantiation
    }

    public static PromoDto toDto(PromoEntity entity) {
        if (entity == null) return null;
        PromoDto dto = new PromoDto();
        dto.setIdPromo(entity.getIdPromo());
        dto.setNomPromo(entity.getNomPromo());
        dto.setAnneePromo(entity.getAnneePromo());
        dto.setNbEtuPromo(entity.getNbEtuPromo());
        return dto;
    }

    public static PromoEntity toEntity(PromoDto dto) {
        if (dto == null) return null;
        PromoEntity entity = new PromoEntity();
        entity.setIdPromo(dto.getIdPromo());
        entity.setNomPromo(dto.getNomPromo());
        entity.setAnneePromo(dto.getAnneePromo());
        entity.setNbEtuPromo(dto.getNbEtuPromo());
        return entity;
    }
}
