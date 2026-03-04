package com.univtime.informatique.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

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
    @JoinColumn(name = "idGroupe", nullable = false)
    private GroupeEntity groupe;

    @OneToMany(mappedBy = "sousGroupe", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TPEntity> tpEntities = new HashSet<>();

    @OneToMany(mappedBy = "sousGroupe", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ParticipeAEntity> participeAEntities = new HashSet<>();

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

    public Set<TPEntity> getTpEntities() {
        return tpEntities;
    }

    public void setTpEntities(Set<TPEntity> tpEntities) {
        this.tpEntities = tpEntities;
    }

    public Set<ParticipeAEntity> getParticipeAEntities() {
        return participeAEntities;
    }

    public void setParticipeAEntities(Set<ParticipeAEntity> participeAEntities) {
        this.participeAEntities = participeAEntities;
    }
}
