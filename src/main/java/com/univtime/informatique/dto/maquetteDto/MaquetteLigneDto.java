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

public class MaquetteLigneDto {
    private Long idComposante;
    private List<MaquetteAssignationDto> assignations;

    // Getters et Setters
    public Long getIdComposante() { return idComposante; }
    public void setIdComposante(Long idComposante) { this.idComposante = idComposante; }
    public List<MaquetteAssignationDto> getAssignations() { return assignations; }
    public void setAssignations(List<MaquetteAssignationDto> assignations) { this.assignations = assignations; }
}
