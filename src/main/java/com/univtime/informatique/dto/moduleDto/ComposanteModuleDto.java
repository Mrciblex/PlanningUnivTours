package com.univtime.informatique.dto.moduleDto;

import com.univtime.informatique.dto.composanteDto.*;

import java.util.Objects;

public class ComposanteModuleDto {
    private Integer idComposante;
    private String nomComposante;
    private Integer volumeHoraireTotal;
    private Integer volumeHoraireCM;
    private Integer volumeHoraireTD;
    private Integer volumeHoraireTP;
    private Integer blocHoraireCM;
    private Integer blocHoraireTD;
    private Integer blocHoraireTP;

    public ComposanteModuleDto(){

    }

    public ComposanteModuleDto(
            Integer idComposante,
            String nomComposante,
            Integer volumeHoraireTotal,
            Integer volumeHoraireCM,
            Integer volumeHoraireTD,
            Integer volumeHoraireTP,
            Integer blocHoraireCM,
            Integer blocHoraireTD,
            Integer blocHoraireTP) {
        this.idComposante = idComposante;
        this.nomComposante = nomComposante;
        this.volumeHoraireTotal = volumeHoraireTotal;
        this.volumeHoraireCM = volumeHoraireCM;
        this.volumeHoraireTD = volumeHoraireTD;
        this.volumeHoraireTP = volumeHoraireTP;
        this.blocHoraireCM = blocHoraireCM;
        this.blocHoraireTD = blocHoraireTD;
        this.blocHoraireTP = blocHoraireTP;
    }

    // Getters et Setters
    public Integer getIdComposante() {
        return idComposante;
    }

    public void setIdComposante(Integer idComposante) {
        this.idComposante = idComposante;
    }

    public String getNomComposante() {
        return nomComposante;
    }

    public void setNomComposante(String nomComposante) {
        this.nomComposante = nomComposante;
    }

    public Integer getVolumeHoraireTotal() {
        return volumeHoraireTotal;
    }

    public void setVolumeHoraireTotal(Integer volumeHoraireTotal) {
        this.volumeHoraireTotal = volumeHoraireTotal;
    }

    public Integer getVolumeHoraireCM() {
        return volumeHoraireCM;
    }

    public void setVolumeHoraireCM(Integer volumeHoraireCM) {
        this.volumeHoraireCM = volumeHoraireCM;
    }

    public Integer getVolumeHoraireTD() {
        return volumeHoraireTD;
    }

    public void setVolumeHoraireTD(Integer volumeHoraireTD) {
        this.volumeHoraireTD = volumeHoraireTD;
    }

    public Integer getVolumeHoraireTP() {
        return volumeHoraireTP;
    }

    public void setVolumeHoraireTP(Integer volumeHoraireTP) {
        this.volumeHoraireTP = volumeHoraireTP;
    }

    public Integer getBlocHoraireCM() {
        return blocHoraireCM;
    }

    public void setBlocHoraireCM(Integer blocHoraireCM) {
        this.blocHoraireCM = blocHoraireCM;
    }

    public Integer getBlocHoraireTD() {
        return blocHoraireTD;
    }

    public void setBlocHoraireTD(Integer blocHoraireTD) {
        this.blocHoraireTD = blocHoraireTD;
    }

    public Integer getBlocHoraireTP() {
        return blocHoraireTP;
    }

    public void setBlocHoraireTP(Integer blocHoraireTP) {
        this.blocHoraireTP = blocHoraireTP;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idComposante);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        ComposanteModuleDto that = (ComposanteModuleDto) obj;
        return Objects.equals(idComposante, that.idComposante);
    }
}
