package com.univtime.informatique.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "groupes")
public class GroupeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idGroupe", nullable = false)
    private Integer idGroupe;

    @Column(name = "nomGroupe", nullable = false, length = 150)
    private String nomGroup;

    @Column(name = "nbEtuGroupe", nullable = false)
    private Integer nbEtuGroupe;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "idPromo", nullable = false)
    private PromoEntity promoEntity;

    public GroupeEntity() {

    }

    public GroupeEntity(Integer idGroupe, String nomGroup, Integer nbEtuGroupe, PromoEntity promoEntity) {
        this.idGroupe = idGroupe;
        this.nomGroup = nomGroup;
        this.nbEtuGroupe = nbEtuGroupe;
        this.promoEntity = promoEntity;
    }

    public Integer getIdGroupe() {
        return idGroupe;
    }

    public void setIdGroupe(Integer idGroupe) {
        this.idGroupe = idGroupe;
    }

    public String getNomGroup() {
        return nomGroup;
    }

    public void setNomGroup(String nomGroup) {
        this.nomGroup = nomGroup;
    }

    public Integer getNbEtuGroupe() {
        return nbEtuGroupe;
    }

    public void setNbEtuGroupe(Integer nbEtuGroupe) {
        this.nbEtuGroupe = nbEtuGroupe;
    }

    public PromoEntity getPromoEntity() {
        return promoEntity;
    }

    public void setPromoEntity(PromoEntity promoEntity) {
        this.promoEntity = promoEntity;
    }
}
