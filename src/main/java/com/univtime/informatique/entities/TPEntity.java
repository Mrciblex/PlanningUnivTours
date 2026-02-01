package com.univtime.informatique.entities;

import com.univtime.informatique.entities.ids.TPId;
import jakarta.persistence.*;

@Entity
@Table(name = "tps")
public class TPEntity {
    @EmbeddedId
    private TPId idTP;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idProf")
    @JoinColumn(name = "idProf", referencedColumnName = "idProf", insertable = false, updatable = false)
    private ProfesseurEntity professeur;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idPromo")
    @JoinColumn(name = "idSousGroupe", referencedColumnName = "idSousGroupe", insertable = false, updatable = false)
    private SousGroupeEntity sousGroupe;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idComposante")
    @JoinColumn(name = "idComposante", referencedColumnName = "idComposante", insertable = false, updatable = false)
    private ComposanteEntity composante;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idRepartitionSemaine")
    @JoinColumn(name = "idRepartitionSemaine", referencedColumnName = "idRepartitionSemaine", insertable = false, updatable = false)
    private RepartitionSemaineEntity repartitionSemaine;

    public TPEntity() {

    }

    public TPId getIdTP() {
        return idTP;
    }

    public void setIdTP(TPId idTP) {
        this.idTP = idTP;
    }

    public ProfesseurEntity getProfesseur() {
        return professeur;
    }

    public void setProfesseur(ProfesseurEntity professeur) {
        this.professeur = professeur;
    }

    public SousGroupeEntity getSousGroupe() {
        return sousGroupe;
    }

    public void setSousGroupe(SousGroupeEntity sousGroupe) {
        this.sousGroupe = sousGroupe;
    }

    public ComposanteEntity getComposante() {
        return composante;
    }

    public void setComposante(ComposanteEntity composante) {
        this.composante = composante;
    }

    public RepartitionSemaineEntity getRepartitionSemaine() {
        return repartitionSemaine;
    }

    public void setRepartitionSemaine(RepartitionSemaineEntity repartitionSemaine) {
        this.repartitionSemaine = repartitionSemaine;
    }
}
