/*
 * Copyright (c) 2026 Ademi Musa. Tous droits réservés.
 * Projet : univTime - Logiciel de gestion d'emplois du temps.
 *
 * Ce code source et l'algorithme associé sont la propriété exclusive de l'auteur.
 * Toute reproduction, modification ou distribution non autorisée, par quelque moyen que ce soit, est strictement interdite.
 *
 * Ce fichier fait partie du projet univTime, concédé sous licence d'usage
 * restreinte à l'Université de Tours, 37000, en France.
 */

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
