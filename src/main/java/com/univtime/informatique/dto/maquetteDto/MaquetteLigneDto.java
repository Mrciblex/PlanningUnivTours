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
