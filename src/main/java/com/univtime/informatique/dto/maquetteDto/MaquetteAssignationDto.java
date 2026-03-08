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

import java.util.Map;

public class MaquetteAssignationDto {
    private String type; // "CM", "TD", "TP"
    private Long idProf;
    private String cibleId; // "PROMO", "GRP_x", "SG_x"
    private Map<Integer, Double> volumes; // Clé: Numéro de semaine, Valeur: Volume

    // Getters et Setters
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public Long getIdProf() { return idProf; }
    public void setIdProf(Long idProf) { this.idProf = idProf; }
    public String getCibleId() { return cibleId; }
    public void setCibleId(String cibleId) { this.cibleId = cibleId; }
    public Map<Integer, Double> getVolumes() { return volumes; }
    public void setVolumes(Map<Integer, Double> volumes) { this.volumes = volumes; }
}
