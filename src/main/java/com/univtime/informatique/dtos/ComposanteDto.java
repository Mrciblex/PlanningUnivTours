package com.univtime.informatique.dtos;

public class ComposanteDto {
    private int idComposante;
    private String nomComposante;
    private int volumeHoraireTotal;
    private int volumeHoraireCM;
    private int volumeHoraireTD;
    private int volumeHoraireTP;
    private Integer blocHoraireCM;
    private Integer blocHoraireTD;
    private Integer blocHoraireTP;
    private int idModule;

    // Getters et Setters
    public int getIdComposante() {
        return idComposante;
    }

    public void setIdComposante(int idComposante) {
        this.idComposante = idComposante;
    }

    public String getNomComposante() {
        return nomComposante;
    }

    public void setNomComposante(String nomComposante) {
        this.nomComposante = nomComposante;
    }

    public int getVolumeHoraireTotal() {
        return volumeHoraireTotal;
    }

    public void setVolumeHoraireTotal(int volumeHoraireTotal) {
        this.volumeHoraireTotal = volumeHoraireTotal;
    }

    public int getVolumeHoraireCM() {
        return volumeHoraireCM;
    }

    public void setVolumeHoraireCM(int volumeHoraireCM) {
        this.volumeHoraireCM = volumeHoraireCM;
    }

    public int getVolumeHoraireTD() {
        return volumeHoraireTD;
    }

    public void setVolumeHoraireTD(int volumeHoraireTD) {
        this.volumeHoraireTD = volumeHoraireTD;
    }

    public int getVolumeHoraireTP() {
        return volumeHoraireTP;
    }

    public void setVolumeHoraireTP(int volumeHoraireTP) {
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

    public int getIdModule() {
        return idModule;
    }

    public void setIdModule(int idModule) {
        this.idModule = idModule;
    }
}

