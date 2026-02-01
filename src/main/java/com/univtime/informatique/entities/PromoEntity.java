package com.univtime.informatique.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

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
    private List<PromoEstComposeeEntity> promoEstComposeeEntities = new ArrayList<>();

    @OneToMany(mappedBy = "promo", fetch = FetchType.LAZY)
    private List<CMEntity> cmEntities = new ArrayList<>();

    @OneToMany(mappedBy = "promo", fetch = FetchType.LAZY)
    private List<TDEntity> tdEntities = new ArrayList<>();

    public PromoEntity() {

    }

    public PromoEntity(Integer idPromo, String nomPromo, Integer anneePromo, Integer nbEtuPromo) {
        this.idPromo = idPromo;
        this.nomPromo = nomPromo;
        this.anneePromo = anneePromo;
        this.nbEtuPromo = nbEtuPromo;
    }

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
}
