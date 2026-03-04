package com.univtime.informatique.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "disponibilites")
public class DisponibiliteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDispo", nullable = false)
    private Integer idDispo;

    @Column(name = "heureDebutDispo", nullable = false)
    private Integer heureDebutDispo;

    @Column(name = "heureFinDispo", nullable = false)
    private Integer heureFinDispo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idJour", nullable = false)
    private JourEntity jour;

    public Integer getIdDispo() {
        return idDispo;
    }

    public void setIdDispo(Integer idDispo) {
        this.idDispo = idDispo;
    }

    public Integer getHeureDebutDispo() {
        return heureDebutDispo;
    }

    public void setHeureDebutDispo(Integer heureDebutDispo) {
        this.heureDebutDispo = heureDebutDispo;
    }

    public Integer getHeureFinDispo() {
        return heureFinDispo;
    }

    public void setHeureFinDispo(Integer heureFinDispo) {
        this.heureFinDispo = heureFinDispo;
    }

    public JourEntity getJour() {
        return jour;
    }

    public void setJour(JourEntity jour) {
        this.jour = jour;
    }
}
