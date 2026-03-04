package com.univtime.informatique.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "professeurs")
public class ProfesseurEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProf", nullable = false)
    private Integer idProf;

    @Column(name = "nomProf", nullable = false, length = 150)
    private String nomProf;

    @Column(name = "prenomProf", nullable = false, length = 150)
    private String prenomProf;

    @Column(name = "intervenantExterieur", nullable = false)
    private Boolean intervenantExterieur;

    @OneToMany(mappedBy = "professeur", fetch = FetchType.LAZY)
    private Set<CMEntity> cmEntities = new HashSet<>();

    @OneToMany(mappedBy = "professeur", fetch = FetchType.LAZY)
    private Set<TDEntity> tdEntities = new HashSet<>();

    @OneToMany(mappedBy = "professeur", fetch = FetchType.LAZY)
    private Set<TPEntity> tpEntities = new HashSet<>();

    @OneToMany(mappedBy = "professeur", fetch = FetchType.LAZY)
    private Set<CoursEntity> coursEntities = new HashSet<>();

    @OneToMany(mappedBy = "professeur", fetch = FetchType.LAZY)
    private Set<JourEntity> jourEntities = new HashSet<>();

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

    public Set<CoursEntity> getCoursEntities() {
        return coursEntities;
    }

    public void setCoursEntities(Set<CoursEntity> coursEntities) {
        this.coursEntities = coursEntities;
    }

    public Set<JourEntity> getJourEntities() {
        return jourEntities;
    }

    public void setJourEntities(Set<JourEntity> jourEntities) {
        this.jourEntities = jourEntities;
    }
}
