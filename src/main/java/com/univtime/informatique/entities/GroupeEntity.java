package com.univtime.informatique.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "groupes")
public class GroupeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idGroupe", nullable = false)
    private Integer idGroupe;

    @Column(name = "nomGroupe", nullable = false, length = 150)
    private String nomGroupe;

    @Column(name = "nbEtuGroupe", nullable = false)
    private Integer nbEtuGroupe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPromo", nullable = false)
    private PromoEntity promo;

    @OneToMany(mappedBy = "groupe", fetch = FetchType.LAZY)
    private List<TDEntity> tdEntities = new ArrayList<>();

    @OneToMany(mappedBy = "groupe", fetch = FetchType.LAZY)
    private List<SousGroupeEntity> sousGroupeEntities = new ArrayList<>();

    public GroupeEntity() {

    }

    public GroupeEntity(Integer idGroupe, String nomGroupe, Integer nbEtuGroupe, PromoEntity promo) {
        this.idGroupe = idGroupe;
        this.nomGroupe = nomGroupe;
        this.nbEtuGroupe = nbEtuGroupe;
        this.promo = promo;
    }

    public Integer getIdGroupe() {
        return idGroupe;
    }

    public void setIdGroupe(Integer idGroupe) {
        this.idGroupe = idGroupe;
    }

    public String getNomGroupe() {
        return nomGroupe;
    }

    public void setNomGroupe(String nomGroupe) {
        this.nomGroupe = nomGroupe;
    }

    public Integer getNbEtuGroupe() {
        return nbEtuGroupe;
    }

    public void setNbEtuGroupe(Integer nbEtuGroupe) {
        this.nbEtuGroupe = nbEtuGroupe;
    }

    public PromoEntity getPromo() {
        return promo;
    }

    public void setPromo(PromoEntity promo) {
        this.promo = promo;
    }
}
