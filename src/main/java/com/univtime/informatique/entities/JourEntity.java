package com.univtime.informatique.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "jours")
public class JourEntity {
    @Id
    @Column(name = "idJour", nullable = false)
    private Integer idJour;

    @Column(name = "JourSemaine", nullable = false, length = 5)
    private Integer jourSemaine;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProf", nullable = false)
    private ProfesseurEntity professeur;

    @OneToMany(mappedBy = "jour", fetch = FetchType.LAZY)
    private List<DisponibiliteEntity> disponibiliteEntities = new ArrayList<>();

    public JourEntity() {

    }

    public JourEntity(Integer idJour, Integer jourSemaine, ProfesseurEntity professeur) {
        this.idJour = idJour;
        this.jourSemaine = jourSemaine;
        this.professeur = professeur;
    }

    public Integer getIdJour() {
        return idJour;
    }

    public void setIdJour(Integer idJour) {
        this.idJour = idJour;
    }

    public Integer getJourSemaine() {
        return jourSemaine;
    }

    public void setJourSemaine(Integer jourSemaine) {
        this.jourSemaine = jourSemaine;
    }

    public ProfesseurEntity getProfesseur() {
        return professeur;
    }

    public void setProfesseur(ProfesseurEntity professeur) {
        this.professeur = professeur;
    }
}
