package com.univtime.informatique.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "professeurs")
public class ProfesseurEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idProf", nullable = false)
    private Integer idProf;

    @Column(name = "nomProf", nullable = false, length = 150)
    private String nomProf;

    @Column(name = "premonProf", nullable = false, length = 150)
    private String prenomProf;

    @Column(name = "intervenantExterieur", nullable = false)
    private Boolean intervenantExterieur;

    public ProfesseurEntity() {

    }

    public ProfesseurEntity(Integer idProf, String nomProf, String prenomProf, Boolean intervenantProf) {
        this.idProf = idProf;
        this.nomProf = nomProf;
        this.prenomProf = prenomProf;
        this.intervenantExterieur = intervenantProf;
    }

    public Integer getIdProf() {
        return idProf;
    }

    public void setIdProf(Integer idProf) {
        this.idProf = idProf;
    }

    public String getNomProf() {
        return nomProf;
    }

    public void setNomProf(String nomProf) {
        this.nomProf = nomProf;
    }

    public String getPrenomProf() {
        return prenomProf;
    }

    public void setPrenomProf(String prenomProf) {
        this.prenomProf = prenomProf;
    }

    public Boolean getIntervenantExterieur() {
        return intervenantExterieur;
    }

    public void setIntervenantExterieur(Boolean intervenantExterieur) {
        this.intervenantExterieur = intervenantExterieur;
    }
}
