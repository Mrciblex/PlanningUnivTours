package com.univtime.informatique.entities;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
    @JoinColumn(name = "idModule", nullable = false)
    private ModuleEntity module;

    @OneToMany(mappedBy = "composante", fetch = FetchType.LAZY)
    private Set<CMEntity> cmEntities = new HashSet<>();

    @OneToMany(mappedBy = "composante", fetch = FetchType.LAZY)
    private Set<TDEntity> tdEntities = new HashSet<>();

    @OneToMany(mappedBy = "composante", fetch = FetchType.LAZY)
    private Set<TPEntity> tpEntities = new HashSet<>();

    @OneToMany(mappedBy = "composante", fetch = FetchType.LAZY)
    private Set<CoursEntity> coursEntities = new HashSet<>();

    @OneToMany(mappedBy = "composante", fetch = FetchType.LAZY)
    private Set<BesoinSalleEntity> besoinSalleEntities = new HashSet<>();

    public Integer getIdComposante() {
        return idComposante;
    }

    public void setIdComposante(Integer idComposante) {
        this.idComposante = idComposante;
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

    public Integer getBlocHoraireCM() {
        return blocHoraireCM;
    }

    public void setBlocHoraireCM(Integer blocHoraireCM) {
        this.blocHoraireCM = blocHoraireCM;
    }

    public Integer getVolumeHoraireTD() {
        return volumeHoraireTD;
    }

    public void setVolumeHoraireTD(Integer volumeHoraireTD) {
        this.volumeHoraireTD = volumeHoraireTD;
    }

    public Integer getBlocHoraireTD() {
        return blocHoraireTD;
    }

    public void setBlocHoraireTD(Integer blocHoraireTD) {
        this.blocHoraireTD = blocHoraireTD;
    }

    public Integer getVolumeHoraireTP() {
        return volumeHoraireTP;
    }

    public void setVolumeHoraireTP(Integer volumeHoraireTP) {
        this.volumeHoraireTP = volumeHoraireTP;
    }

    public Integer getBlocHoraireTP() {
        return blocHoraireTP;
    }

    public void setBlocHoraireTP(Integer blocHoraireTP) {
        this.blocHoraireTP = blocHoraireTP;
    }

    public ModuleEntity getModule() {
        return module;
    }

    public void setModule(ModuleEntity module) {
        this.module = module;
    }

    public Set<CMEntity> getCmEntities() {
        return cmEntities;
    }

    public void setCmEntities(Set<CMEntity> cmEntities) {
        this.cmEntities = cmEntities;
    }

    public Set<TDEntity> getTdEntities() {
        return tdEntities;
    }

    public void setTdEntities(Set<TDEntity> tdEntities) {
        this.tdEntities = tdEntities;
    }

    public Set<TPEntity> getTpEntities() {
        return tpEntities;
    }

    public void setTpEntities(Set<TPEntity> tpEntities) {
        this.tpEntities = tpEntities;
    }

    public Set<CoursEntity> getCoursEntities() {
        return coursEntities;
    }

    public void setCoursEntities(Set<CoursEntity> coursEntities) {
        this.coursEntities = coursEntities;
    }

    public Set<BesoinSalleEntity> getBesoinSalleEntities() {
        return besoinSalleEntities;
    }

    public void setBesoinSalleEntities(Set<BesoinSalleEntity> besoinSalleEntities) {
        this.besoinSalleEntities = besoinSalleEntities;
    }
}
