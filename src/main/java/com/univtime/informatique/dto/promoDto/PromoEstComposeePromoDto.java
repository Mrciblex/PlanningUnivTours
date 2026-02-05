package com.univtime.informatique.dto.promoDto;

import com.univtime.informatique.dto.promoEstComposeeDto.ModulePromoEstComposeeDto;

import java.util.Objects;

public class PromoEstComposeePromoDto {
    private ModulePromoEstComposeeDto moduleDto;

    public PromoEstComposeePromoDto() {

    }

    public PromoEstComposeePromoDto(ModulePromoEstComposeeDto moduleDto) {
        this.moduleDto = moduleDto;
    }

    public ModulePromoEstComposeeDto getModuleDto() {
        return moduleDto;
    }

    public void setModuleDto(ModulePromoEstComposeeDto moduleDto) {
        this.moduleDto = moduleDto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(moduleDto);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PromoEstComposeePromoDto that = (PromoEstComposeePromoDto) obj;
        return Objects.equals(moduleDto, that.moduleDto);
    }
}
