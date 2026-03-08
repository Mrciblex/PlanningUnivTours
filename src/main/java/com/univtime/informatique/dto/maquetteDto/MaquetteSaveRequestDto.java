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

package com.univtime.informatique.dto.maquetteDto;

import java.util.List;
import java.util.Map;

public class MaquetteSaveRequestDto {
    private Long idPromo;
    private List<MaquetteLigneDto> lignes;

    // Getters et Setters
    public Long getIdPromo() {
        return idPromo;
    }
    public void setIdPromo(Long idPromo) {
        this.idPromo = idPromo;
    }
    public List<MaquetteLigneDto> getLignes() {
        return lignes;
    }
    public void setLignes(List<MaquetteLigneDto> lignes) {
        this.lignes = lignes;
    }
}

