package com.univtime.informatique.entities;

import com.univtime.informatique.entities.ids.PromoEstComposeeId;
import jakarta.persistence.*;

@Entity
@Table(name = "promoEstComposee")
public class PromoEstComposeeEntity {
    @EmbeddedId
    private PromoEstComposeeId idPromoEstComposee;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idPromo")
    @JoinColumn(name = "idPromo", referencedColumnName = "idPromo", insertable = false, updatable = false)
    private PromoEntity promo;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idModule")
    @JoinColumn(name = "idModule", referencedColumnName = "idModule", insertable = false, updatable = false)
    private ModuleEntity module;

    public PromoEstComposeeEntity() {

    }

    public PromoEstComposeeId getIdPromoEstComposee() {
        return idPromoEstComposee;
    }

    public void setIdPromoEstComposee(PromoEstComposeeId idPromoEstComposee) {
        this.idPromoEstComposee = idPromoEstComposee;
    }

    public PromoEntity getPromo() {
        return promo;
    }

    public void setPromo(PromoEntity promo) {
        this.promo = promo;
    }

    public ModuleEntity getModule() {
        return module;
    }

    public void setModule(ModuleEntity module) {
        this.module = module;
    }
}
