package com.univtime.informatique.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tds")
public class TDEntity {
    @EmbeddedId
    private Integer idTD;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idProf")
    @JoinColumn(name = "idProf", referencedColumnName = "idProf", insertable = false, updatable = false)
    private ProfesseurEntity professeur;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idGroupe")
    @JoinColumn(name = "idGroupe", referencedColumnName = "idGroupe", insertable = false, updatable = false)
    private GroupeEntity groupe;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idComposante")
    @JoinColumn(name = "idComposante", referencedColumnName = "idComposante", insertable = false, updatable = false)
    private ComposanteEntity composante;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idRepartitionSemaine")
    @JoinColumn(name = "idRepartitionSemaine", referencedColumnName = "idRepartitionSemaine", insertable = false, updatable = false)
    private RepartitionSemaineEntity repartitionSemaine;

    public TDEntity() {

    }

    public Integer getIdTD() {
        return idTD;
    }

    public void setIdTD(Integer idTD) {
        this.idTD = idTD;
    }

    public ProfesseurEntity getProfesseur() {
        return professeur;
    }

    public void setProfesseur(ProfesseurEntity professeur) {
        this.professeur = professeur;
    }

    public GroupeEntity getGroupe() {
        return groupe;
    }

    public void setGroupe(GroupeEntity groupe) {
        this.groupe = groupe;
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
