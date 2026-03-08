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

package com.univtime.informatique.dto.idsDto;

import java.util.Objects;

/**
 * DTO pour l'identifiant composite de la relation PromoEstComposee.
 */
public class PromoEstComposeeIdDto {

    private Integer idPromo;
    private Integer idModule;

    // Constructeur par défaut
    public PromoEstComposeeIdDto() {
    }

    // Constructeur complet
    public PromoEstComposeeIdDto(Integer idPromo,
                                 Integer idModule) {
        this.idPromo = idPromo;
        this.idModule = idModule;
    }

    // Getters et Setters
    public Integer getIdPromo() { return idPromo; }
    public void setIdPromo(Integer idPromo) { this.idPromo = idPromo; }

    public Integer getIdModule() { return idModule; }
    public void setIdModule(Integer idModule) { this.idModule = idModule; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PromoEstComposeeIdDto that = (PromoEstComposeeIdDto) o;
        return Objects.equals(idPromo, that.idPromo) &&
                Objects.equals(idModule, that.idModule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPromo, idModule);
    }
}