package com.univtime.informatique.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "composantes")
public class ComposanteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idComposante", nullable = false)
    private Integer idComposante;

    @Column(name = "nomCoposante", nullable = false, length = 150)
    private String nomCoposante;

    @Column(name = "volumeHoraireTotal", nullable = false)
    private Integer volumeHoraireTotal;

    @Column(name = "volumeHoraireCM", nullable = true)
    private Integer volumeHoraireCM;

    @Column(name = "blocHoraireCM", nullable = true)
    private Integer blocHoraireCM;

    @Column(name = "volumeHoraireTD", nullable = true)
    private Integer volumeHoraireTD;

    @Column(name = "blocHoraireTD", nullable = true)
    private Integer blocHoraireTD;

    @Column(name = "volumeHoraireTP", nullable = true)
    private Integer volumeHoraireTP;

    @Column(name = "blocHoraireTP", nullable = true)
    private Integer blocHoraireTP;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "idModule", nullable = false)
    private ModuleEntity idModule;

    @OneToMany(mappedBy = "composante", fetch = FetchType.LAZY)
    private List<CMEntity> cmEntities = new ArrayList<>();

    @OneToMany(mappedBy = "composante", fetch = FetchType.LAZY)
    private List<TDEntity> tdEntities = new ArrayList<>();

    public ComposanteEntity() {

    }

    public ComposanteEntity(Integer idComposante, String nomCoposante, Integer volumeHoraireTotal, ModuleEntity idModule) {
        this.idComposante = idComposante;
        this.nomCoposante = nomCoposante;
        this.volumeHoraireTotal = volumeHoraireTotal;
        this.idModule = idModule;
    }

    public ModuleEntity getIdModule() {
        return idModule;
    }

    public void setIdModule(ModuleEntity idModule) {
        this.idModule = idModule;
    }

    public String getNomCoposante() {
        return nomCoposante;
    }

    public void setNomCoposante(String nomCoposante) {
        this.nomCoposante = nomCoposante;
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

    public Integer getBlocoHoraireCM() {
        return blocHoraireCM;
    }

    public void setBlocoHoraireCM(Integer blocHoraireCM) {
        this.blocHoraireCM = blocHoraireCM;
    }

    public Integer getVolumeHoraireTD() {
        return volumeHoraireTD;
    }

    public void setVolumeHoraireTD(Integer volumeHoraireTD) {
        this.volumeHoraireTD = volumeHoraireTD;
    }

    public Integer getBlocoHoraireTD() {
        return blocHoraireTD;
    }

    public void setBlocoHoraireTD(Integer blocHoraireTD) {
        this.blocHoraireTD = blocHoraireTD;
    }

    public Integer getVolumeHoraireTP() {
        return volumeHoraireTP;
    }

    public void setVolumeHoraireTP(Integer volumeHoraireTP) {
        this.volumeHoraireTP = volumeHoraireTP;
    }

    public Integer getBlocoHoraireTP() {
        return blocHoraireTP;
    }

    public void setBlocoHoraireTP(Integer blocHoraireTP) {
        this.blocHoraireTP = blocHoraireTP;
    }
}

