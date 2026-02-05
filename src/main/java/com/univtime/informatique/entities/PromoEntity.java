package com.univtime.informatique.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "promos")
public class PromoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPromo", nullable = false)
    private Integer idPromo;

    @Column(name = "nomPromo", nullable = false, length = 150)
    private String nomPromo;

    @Column(name = "anneePromo", nullable = false)
    private Integer anneePromo;

    @Column(name = "nbEtuPromo", nullable = false)
    private Integer nbEtuPromo;

    @OneToMany(mappedBy = "promo", fetch = FetchType.LAZY)
    private Set<PromoEstComposeeEntity> promoEstComposeeEntities = new HashSet<>();

    @OneToMany(mappedBy = "promo", fetch = FetchType.LAZY)
    private Set<CMEntity> cmEntities = new HashSet<>();

    @OneToMany(mappedBy = "promo", fetch = FetchType.LAZY)
    private Set<GroupeEntity> groupeEntities = new HashSet<>();

    public Integer getIdPromo() {
        return idPromo;
    }

    public void setIdPromo(Integer idPromo) {
        this.idPromo = idPromo;
    }

    public String getNomPromo() {
        return nomPromo;
    }

    public void setNomPromo(String nomPromo) {
        this.nomPromo = nomPromo;
    }

    public Integer getAnneePromo() {
        return anneePromo;
    }

    public void setAnneePromo(Integer anneePromo) {
        this.anneePromo = anneePromo;
    }

    public Integer getNbEtuPromo() {
        return nbEtuPromo;
    }

    public void setNbEtuPromo(Integer nbEtuPromo) {
        this.nbEtuPromo = nbEtuPromo;
    }

    public Set<PromoEstComposeeEntity> getPromoEstComposeeEntities() {
        return promoEstComposeeEntities;
    }

    public void setPromoEstComposeeEntities(Set<PromoEstComposeeEntity> promoEstComposeeEntities) {
        this.promoEstComposeeEntities = promoEstComposeeEntities;
    }

    public Set<CMEntity> getCmEntities() {
        return cmEntities;
    }

    public void setCmEntities(Set<CMEntity> cmEntities) {
        this.cmEntities = cmEntities;
    }

    public Set<GroupeEntity> getGroupeEntities() {
        return groupeEntities;
    }

    public void setGroupeEntities(Set<GroupeEntity> groupeEntities) {
        this.groupeEntities = groupeEntities;
    }
}
