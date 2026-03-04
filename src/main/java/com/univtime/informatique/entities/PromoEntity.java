package com.univtime.informatique.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
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

    @Column(name = "nbEtuPromo")
    private Integer nbEtuPromo;

    @Column(name = "debutS1Promo", nullable = false)
    private LocalDate debutS1Promo;

    @Column(name = "finS1Promo", nullable = false)
    private LocalDate finS1Promo;

    @Column(name = "debutS2Promo", nullable = false)
    private LocalDate debutS2Promo;

    @Column(name = "finS2Promo", nullable = false)
    private LocalDate finS2Promo;

    @OneToMany(mappedBy = "promo", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PromoEstComposeeEntity> promoEstComposeeEntities = new HashSet<>();

    @OneToMany(mappedBy = "promo", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CMEntity> cmEntities = new HashSet<>();

    @OneToMany(mappedBy = "promo", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
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

    public LocalDate getDebutS1Promo() {
        return debutS1Promo;
    }

    public void setDebutS1Promo(LocalDate debutS1Promo) {
        this.debutS1Promo = debutS1Promo;
    }

    public LocalDate getFinS1Promo() {
        return finS1Promo;
    }

    public void setFinS1Promo(LocalDate finS1Promo) {
        this.finS1Promo = finS1Promo;
    }

    public LocalDate getDebutS2Promo() {
        return debutS2Promo;
    }

    public void setDebutS2Promo(LocalDate debutS2Promo) {
        this.debutS2Promo = debutS2Promo;
    }

    public LocalDate getFinS2Promo() {
        return finS2Promo;
    }

    public void setFinS2Promo(LocalDate finS2Promo) {
        this.finS2Promo = finS2Promo;
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
