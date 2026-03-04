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
