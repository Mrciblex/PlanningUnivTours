package com.univtime.informatique.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sousGroupes")
public class SousGroupeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSousGroupe", nullable = false)
    private Integer idSousGroupe;

    @Column(name = "nomSousGroupe", nullable = false, length = 150)
    private String nomSousGroupe;

    @Column(name = "nbEtuSousGroupe", nullable = false)
    private Integer nbEtuSousGroupe;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "idGroupe", nullable = false)
    private GroupeEntity groupe;

    @OneToMany(mappedBy = "sousGroupe", fetch = FetchType.LAZY)
    private List<TPEntity> tpEntities = new ArrayList<>();

    @OneToMany(mappedBy = "sousGroupe", fetch = FetchType.LAZY)
    private List<ParticipeAEntity> participeAEntities = new ArrayList<>();

    public SousGroupeEntity() {

    }

    public SousGroupeEntity(Integer idSousGroupe, String nomSousGroupe, Integer nbEtuSousGroupe, GroupeEntity groupe) {
        this.idSousGroupe = idSousGroupe;
        this.nomSousGroupe = nomSousGroupe;
        this.nbEtuSousGroupe = nbEtuSousGroupe;
        this.groupe = groupe;
    }

    public Integer getIdSousGroupe() {
        return idSousGroupe;
    }

    public void setIdSousGroupe(Integer idSousGroupe) {
        this.idSousGroupe = idSousGroupe;
    }

    public String getNomSousGroupe() {
        return nomSousGroupe;
    }

    public void setNomSousGroupe(String nomSousGroupe) {
        this.nomSousGroupe = nomSousGroupe;
    }

    public Integer getNbEtuSousGroupe() {
        return nbEtuSousGroupe;
    }

    public void setNbEtuSousGroupe(Integer nbEtuSousGroupe) {
        this.nbEtuSousGroupe = nbEtuSousGroupe;
    }

    public GroupeEntity getGroupe() {
        return groupe;
    }

    public void setGroupe(GroupeEntity groupe) {
        this.groupe = groupe;
    }
}
