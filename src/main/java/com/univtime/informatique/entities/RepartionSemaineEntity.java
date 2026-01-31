package com.univtime.informatique.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "repartionSemaines")
public class RepartionSemaineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRepartionSemaine", nullable = false)
    private Integer idRepartionSemaine;

    @Column(name = "numSemaine", nullable = false, length = 5)
    private Integer numSemaine;

    @Column(name = "qteTypeCours", nullable = false)
    private Integer qteTypeCours;

    public RepartionSemaineEntity() {

    }

    public RepartionSemaineEntity(Integer idRepartionSemaine, Integer numSemaine, Integer qteTypeCours) {
        this.idRepartionSemaine = idRepartionSemaine;
        this.numSemaine = numSemaine;
        this.qteTypeCours = qteTypeCours;
    }

    public Integer getIdRepartionSemaine() {
        return idRepartionSemaine;
    }

    public void setIdRepartionSemaine(Integer idRepartionSemaine) {
        this.idRepartionSemaine = idRepartionSemaine;
    }

    public Integer getNumSemaine() {
        return numSemaine;
    }

    public void setNumSemaine(Integer numSemaine) {
        this.numSemaine = numSemaine;
    }

    public Integer getQteTypeCours() {
        return qteTypeCours;
    }

    public void setQteTypeCours(Integer qteTypeCours) {
        this.qteTypeCours = qteTypeCours;
    }
}
