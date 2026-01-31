package com.univtime.informatique.entities;

import com.univtime.informatique.entities.ids.PromoEstComposeeId;
import jakarta.persistence.*;

@Entity
@Table(name = "promoEstComposee")
public class PromoEstComposeeEntity {
    @EmbeddedId
    private PromoEstComposeeId idPromoEstComposee;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idModule")
    @JoinColumn(name = "idPromo", referencedColumnName = "idPromo", insertable = false, updatable = false)
    private PromoEntity promo;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idModule")
    @JoinColumn(name = "idModule", referencedColumnName = "idModule", insertable = false, updatable = false)
    private ModuleEntity module;

    public PromoEstComposeeEntity() {

    }
}
