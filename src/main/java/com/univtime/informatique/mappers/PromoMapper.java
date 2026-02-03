package com.univtime.informatique.mappers;

import com.univtime.informatique.dtos.PromoDto;
import com.univtime.informatique.entities.PromoEntity;

public final class PromoMapper {

    private PromoMapper() {
        // Private constructor pour éviter l'instantiation
    }

    public static PromoDto toDto(PromoEntity entity) {
        return entity == null ? null : new PromoDto()
                .setIdPromo(entity.getIdPromo())
                .setNomPromo(entity.getNomPromo())
                .setAnneePromo(entity.getAnneePromo())
                .setNbEtuPromo(entity.getNbEtuPromo());
    }

    public static PromoEntity toEntity(PromoDto dto) {
        return dto == null ? null : new PromoEntity()
                .setIdPromo(dto.getIdPromo())
                .setNomPromo(dto.getNomPromo())
                .setAnneePromo(dto.getAnneePromo())
                .setNbEtuPromo(dto.getNbEtuPromo());
    }
}
