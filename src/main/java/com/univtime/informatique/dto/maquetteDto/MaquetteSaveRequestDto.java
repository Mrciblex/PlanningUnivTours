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

