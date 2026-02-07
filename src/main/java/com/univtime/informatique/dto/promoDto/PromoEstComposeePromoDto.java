package com.univtime.informatique.dto.promoDto;

import com.univtime.informatique.entities.ids.PromoEstComposeeId;

import java.util.Objects;

public class PromoEstComposeePromoDto {
    private PromoEstComposeeId idPromoEstComposee;

    public PromoEstComposeePromoDto() {

    }

    public PromoEstComposeePromoDto(
            PromoEstComposeeId idPromoEstComposee) {
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
        PromoEstComposeePromoDto that = (PromoEstComposeePromoDto) obj;
        return Objects.equals(idPromoEstComposee, that.idPromoEstComposee);
    }
}
