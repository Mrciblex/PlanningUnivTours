package com.univtime.informatique.dto.promoEstComposeeDto;

import com.univtime.informatique.entities.ids.PromoEstComposeeId;

import java.util.Objects;

public class PromoEstComposeeDto {
    private PromoPromoEstComposeeDto promoDto;
    private ModulePromoEstComposeeDto moduleDto;

    public PromoEstComposeeDto() {

    }

    public PromoEstComposeeDto(PromoPromoEstComposeeDto promoDto,
                               ModulePromoEstComposeeDto moduleDto) {
        this.promoDto = promoDto;
        this.moduleDto = moduleDto;
    }

    public PromoPromoEstComposeeDto getPromoDto() {
        return promoDto;
    }

    public void setPromoDto(PromoPromoEstComposeeDto promoDto) {
        this.promoDto = promoDto;
    }

    public ModulePromoEstComposeeDto getModuleDto() {
        return moduleDto;
    }

    public void setModuleDto(ModulePromoEstComposeeDto moduleDto) {
        this.moduleDto = moduleDto;
    }

    public PromoEstComposeeId getPromoEstComposeeId() {
        return new PromoEstComposeeId(
                promoDto.getIdPromo(),
                moduleDto.getIdModule()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        PromoEstComposeeDto that = (PromoEstComposeeDto) o;
        return Objects.equals(promoDto, that.promoDto)
                && Objects.equals(moduleDto, that.moduleDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(promoDto, moduleDto);
    }
}
