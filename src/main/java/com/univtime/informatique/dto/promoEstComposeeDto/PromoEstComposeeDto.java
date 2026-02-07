package com.univtime.informatique.dto.promoEstComposeeDto;

import com.univtime.informatique.entities.ids.PromoEstComposeeId;

import java.util.Objects;

public class PromoEstComposeeDto {
    private PromoEstComposeeId idPromoEstComposee;

    public PromoEstComposeeDto() {

    }

    public PromoEstComposeeDto(PromoEstComposeeId idPromoEstComposee) {
        this.idPromoEstComposee = idPromoEstComposee;
    }

    public PromoEstComposeeId getIdPromoEstComposee() {
        return idPromoEstComposee;
    }

    public void setIdPromoEstComposee(PromoEstComposeeId idPromoEstComposee) {
        this.idPromoEstComposee = idPromoEstComposee;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPromoEstComposee);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PromoEstComposeeDto that = (PromoEstComposeeDto) obj;
        return Objects.equals(idPromoEstComposee, that.idPromoEstComposee);
    }
}
