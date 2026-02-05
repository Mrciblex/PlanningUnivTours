package com.univtime.informatique.entities;

import com.univtime.informatique.constants.TypeCours;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cours")
public class CoursEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCours", nullable = false)
    private Integer idCours;

    @Column(name = "heureDebutCours", nullable = false)
    private LocalDateTime heureDebutCours;

    @Column(name = "heureFinCours", nullable = false)
    private LocalDateTime heureFinCours;

    @Column(name = "typeCours", nullable = false, length = 150)
    private TypeCours typeCours;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idComposante", nullable = false)
    private ComposanteEntity composante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProf", nullable = false)
    private ProfesseurEntity professeur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idSalle", nullable = false)
    private SalleEntity salle;

    @OneToMany(mappedBy = "cours", fetch = FetchType.LAZY)
    private Set<ParticipeAEntity> participeAEntities = new HashSet<>();

    public Integer getIdCours() {
        return idCours;
    }

    public void setIdCours(Integer idCours) {
        this.idCours = idCours;
    }

    public LocalDateTime getHeureDebutCours() {
        return heureDebutCours;
    }

    public void setHeureDebutCours(LocalDateTime heureDebutCours) {
        this.heureDebutCours = heureDebutCours;
    }

    public LocalDateTime getHeureFinCours() {
        return heureFinCours;
    }

    public void setHeureFinCours(LocalDateTime heureFinCours) {
        this.heureFinCours = heureFinCours;
    }

    public TypeCours getTypeCours() {
        return typeCours;
    }

    public void setTypeCours(TypeCours typeCours) {
        this.typeCours = typeCours;
    }

    public ComposanteEntity getComposante() {
        return composante;
    }

    public void setComposante(ComposanteEntity composante) {
        this.composante = composante;
    }

    public ProfesseurEntity getProfesseur() {
        return professeur;
    }

    public void setProfesseur(ProfesseurEntity professeur) {
        this.professeur = professeur;
    }

    public SalleEntity getSalle() {
        return salle;
    }

    public void setSalle(SalleEntity salle) {
        this.salle = salle;
    }

    public Set<ParticipeAEntity> getParticipeAEntities() {
        return participeAEntities;
    }

    public void setParticipeAEntities(Set<ParticipeAEntity> participeAEntities) {
        this.participeAEntities = participeAEntities;
    }
}
