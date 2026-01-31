package com.univtime.informatique.entities;

import jakarta.persistence.*;

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
    private GroupeEntity groupeEntity;

    public SousGroupeEntity() {

    }

    public SousGroupeEntity(Integer idSousGroupe, String nomSousGroupe, Integer nbEtuSousGroupe, GroupeEntity groupeEntity) {
        this.idSousGroupe = idSousGroupe;
        this.nomSousGroupe = nomSousGroupe;
        this.nbEtuSousGroupe = nbEtuSousGroupe;
        this.groupeEntity = groupeEntity;
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

    public GroupeEntity getGroupeEntity() {
        return groupeEntity;
    }

    public void setGroupeEntity(GroupeEntity groupeEntity) {
        this.groupeEntity = groupeEntity;
    }
}
