package com.univtime.informatique.mappers;

import com.univtime.informatique.dto.promoEstComposeeDto.*;
import com.univtime.informatique.entities.PromoEstComposeeEntity;
import com.univtime.informatique.entities.PromoEntity;
import com.univtime.informatique.entities.ModuleEntity;
import com.univtime.informatique.entities.ids.PromoEstComposeeId;

public class PromoEstComposeeMapper {

    private PromoEstComposeeMapper() {

    }

    public static PromoEstComposeeDto toDto(PromoEstComposeeEntity entity) {
        if (entity == null) {
            return null;
        }
        PromoEstComposeeDto dto = new PromoEstComposeeDto();
        dto.setPromoDto(promoToDto(entity.getPromo()));
        dto.setModuleDto(moduleToDto(entity.getModule()));

        return dto;
    }

    public static PromoEstComposeeEntity toEntity(PromoEstComposeeDto dto) {
        if (dto == null) {
            return null;
        }
        PromoEstComposeeEntity entity = new PromoEstComposeeEntity();

        PromoEntity promo = new PromoEntity();
        if (dto.getPromoDto() != null) {
            promo.setIdPromo(dto.getPromoDto().getIdPromo());
            entity.setPromo(promo);
        }

        ModuleEntity module = new ModuleEntity();
        if (dto.getModuleDto() != null) {
            module.setIdModule(dto.getModuleDto().getIdModule());
            entity.setModule(module);
        }

        entity.setIdPromoEstComposee(new PromoEstComposeeId(promo.getIdPromo(), module.getIdModule()));

        return entity;
    }

    private static PromoPromoEstComposeeDto promoToDto(PromoEntity entity) {
        PromoPromoEstComposeeDto promo = new PromoPromoEstComposeeDto();
        if (entity != null) {
            promo.
        }
        return promo;
    }

    private static ModulePromoEstComposeeDto moduleToDto(ModuleEntity entity) {
        ModulePromoEstComposeeDto module = new ModulePromoEstComposeeDto();
        if (entity != null) {
            module.
        }
        return module;
    }
}
