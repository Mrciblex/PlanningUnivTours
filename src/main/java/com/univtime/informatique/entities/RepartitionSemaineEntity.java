package com.univtime.informatique.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "repartitionSemaines")
public class RepartitionSemaineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRepartitionSemaine", nullable = false)
    private Integer idRepartitionSemaine;

    @Column(name = "numSemaine", nullable = false, length = 5)
    private Integer numSemaine;

    @Column(name = "qteTypeCours", nullable = false)
    private Integer qteTypeCours;

    @OneToMany(mappedBy = "repartitionSemaine", fetch = FetchType.LAZY)
    private List<CMEntity> cmEntities = new ArrayList<>();

    @OneToMany(mappedBy = "repartitionSemaine", fetch = FetchType.LAZY)
    private List<TDEntity> tdEntities = new ArrayList<>();

    @OneToMany(mappedBy = "repartitionSemaine", fetch = FetchType.LAZY)
    private List<TPEntity> tpEntities = new ArrayList<>();

    public RepartitionSemaineEntity() {

    }

    public RepartitionSemaineEntity(Integer idRepartitionSemaine, Integer numSemaine, Integer qteTypeCours) {
        this.idRepartitionSemaine = idRepartitionSemaine;
        this.numSemaine = numSemaine;
        this.qteTypeCours = qteTypeCours;
    }

    public Integer getIdRepartitionSemaine() {
        return idRepartitionSemaine;
    }

    public void setIdRepartitionSemaine(Integer idRepartitionSemaine) {
        this.idRepartitionSemaine = idRepartitionSemaine;
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
