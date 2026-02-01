package com.univtime.informatique.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Salles")
public class SalleEntity {
    @Id
    @Column(name = "idSalle", nullable = false)
    private Integer idSalle;

    @Column(name = "nbPlace", nullable = true)
    private Integer nbPlace;

    @Column(name = "salleMachine",  nullable = false)
    private boolean salleMachine;

    @Column(name = "nbPC", nullable = true)
    private Integer nbPC;

    @OneToMany(mappedBy = "salle", fetch = FetchType.LAZY)
    private List<CoursEntity> coursEntities = new ArrayList<>();

    @OneToMany(mappedBy = "salle", fetch = FetchType.LAZY)
    private List<BesoinSalleEntity> besoinSalleEntities = new ArrayList<>();

    public SalleEntity() {

    }

    public SalleEntity(Integer idSalle, Boolean salleMachine) {
        this.idSalle = idSalle;
        this.salleMachine = salleMachine;
    }

    public SalleEntity(Integer idSalle, Integer nbPlace, boolean salleMachine, Integer nbPC) {
        this.idSalle = idSalle;
        this.nbPlace = nbPlace;
        this.salleMachine = salleMachine;
        this.nbPC = nbPC;
    }

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
}
