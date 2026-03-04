package com.univtime.informatique.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Salles")
public class SalleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSalle", nullable = false)
    private Integer idSalle;

    @Column(name = "nbPlace", nullable = true)
    private Integer nbPlace;

    @Column(name = "salleMachine",  nullable = false)
    private boolean salleMachine;

    @Column(name = "nbPC", nullable = true)
    private Integer nbPC;

    @OneToMany(mappedBy = "salle", fetch = FetchType.LAZY)
    private Set<CoursEntity> coursEntities = new HashSet<>();

    @OneToMany(mappedBy = "salle", fetch = FetchType.LAZY)
    private Set<BesoinSalleEntity> besoinSalleEntities = new HashSet<>();

    public Integer getIdSalle() {
        return idSalle;
    }

    public void setIdSalle(Integer idSalle) {
        this.idSalle = idSalle;
    }

    public Integer getNbPlace() {
        return nbPlace;
    }

    public void setNbPlace(Integer nbPlace) {
        this.nbPlace = nbPlace;
    }

    public boolean isSalleMachine() {
        return salleMachine;
    }

    public void setSalleMachine(boolean salleMachine) {
        this.salleMachine = salleMachine;
    }

    public Integer getNbPC() {
        return nbPC;
    }

    public void setNbPC(Integer nbPC) {
        this.nbPC = nbPC;
    }

    public Set<CoursEntity> getCoursEntities() {
        return coursEntities;
    }

    public void setCoursEntities(Set<CoursEntity> coursEntities) {
        this.coursEntities = coursEntities;
    }

    public Set<BesoinSalleEntity> getBesoinSalleEntities() {
        return besoinSalleEntities;
    }

    public void setBesoinSalleEntities(Set<BesoinSalleEntity> besoinSalleEntities) {
        this.besoinSalleEntities = besoinSalleEntities;
    }
}
