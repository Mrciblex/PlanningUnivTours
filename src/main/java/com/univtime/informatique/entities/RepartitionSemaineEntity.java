package com.univtime.informatique.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

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
    private Set<CMEntity> cmEntities = new HashSet<>();

    @OneToMany(mappedBy = "repartitionSemaine", fetch = FetchType.LAZY)
    private Set<TDEntity> tdEntities = new HashSet<>();

    @OneToMany(mappedBy = "repartitionSemaine", fetch = FetchType.LAZY)
    private Set<TPEntity> tpEntities = new HashSet<>();

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

    public Set<CMEntity> getCmEntities() {
        return cmEntities;
    }

    public void setCmEntities(Set<CMEntity> cmEntities) {
        this.cmEntities = cmEntities;
    }

    public Set<TDEntity> getTdEntities() {
        return tdEntities;
    }

    public void setTdEntities(Set<TDEntity> tdEntities) {
        this.tdEntities = tdEntities;
    }

    public Set<TPEntity> getTpEntities() {
        return tpEntities;
    }

    public void setTpEntities(Set<TPEntity> tpEntities) {
        this.tpEntities = tpEntities;
    }
}
