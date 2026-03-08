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
 * DTO représentant l'identifiant composite pour les besoins d'une salle.
 */
public class BesoinSalleIdDto {

    private Integer idSalle;
    private Integer idComposante;

    // Constructeur par défaut
    public BesoinSalleIdDto() {
    }

    // Constructeur complet
    public BesoinSalleIdDto(Integer idSalle,
                            Integer idComposante) {
        this.idSalle = idSalle;
        this.idComposante = idComposante;
    }

    // Getters et Setters
    public Integer getIdSalle() { return idSalle; }
    public void setIdSalle(Integer idSalle) { this.idSalle = idSalle; }

    public Integer getIdComposante() { return idComposante; }
    public void setIdComposante(Integer idComposante) { this.idComposante = idComposante; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BesoinSalleIdDto that = (BesoinSalleIdDto) o;
        return Objects.equals(idSalle, that.idSalle) &&
                Objects.equals(idComposante, that.idComposante);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSalle, idComposante);
    }
}