package com.univtime.informatique.mappers;

import com.univtime.informatique.dto.promoEstComposeeDto.*;
import com.univtime.informatique.entities.PromoEstComposeeEntity;
import com.univtime.informatique.entities.PromoEntity;
import com.univtime.informatique.entities.ModuleEntity;

public final class PromoEstComposeeMapper {

    private PromoEstComposeeMapper() {}

    public static PromoEstComposeeDto toDto(PromoEstComposeeEntity entity) {
        if (entity == null) return null;
        PromoEstComposeeDto dto = new PromoEstComposeeDto();
        if (entity.getPromo() != null) {
            PromoPromoEstComposeeDto p = new PromoPromoEstComposeeDto();
            p.setIdPromo(entity.getPromo().getIdPromo());
            p.setNomPromo(entity.getPromo().getNomPromo());
            dto.setPromoDto(p);
        }
        if (entity.getModule() != null) {
            ModulePromoEstComposeeDto m = new ModulePromoEstComposeeDto();
            m.setIdModule(entity.getModule().getIdModule());
            m.setNomModule(entity.getModule().getNomModule());
            dto.setModuleDto(m);
        }
        return dto;
    }

    public static PromoEstComposeeEntity toEntity(PromoEstComposeeDto dto) {
        if (dto == null) return null;
        PromoEstComposeeEntity entity = new PromoEstComposeeEntity();
        if (dto.getPromoDto() != null) {
            PromoEntity p = new PromoEntity();
            p.setIdPromo(dto.getPromoDto().getIdPromo());
            entity.setPromo(p);
        }
        if (dto.getModuleDto() != null) {
            ModuleEntity m = new ModuleEntity();
            m.setIdModule(dto.getModuleDto().getIdModule());
            entity.setModule(m);
        }
        return entity;
    }
}
