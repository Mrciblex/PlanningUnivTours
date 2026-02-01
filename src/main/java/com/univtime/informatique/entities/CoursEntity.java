package com.univtime.informatique.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private Enum typeCours;

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
    private List<ParticipeAEntity> participeAEntities = new ArrayList<>();

    public CoursEntity() {

    }

    public CoursEntity(Integer idCours, LocalDateTime heureDebutCours, LocalDateTime heureFinCours, Enum typeCours,
                       ComposanteEntity composante, ProfesseurEntity professeur, SalleEntity salle) {
        this.idCours = idCours;
        this.heureDebutCours = heureDebutCours;
        this.heureFinCours = heureFinCours;
        this.typeCours = typeCours;
        this.composante = composante;
        this.professeur = professeur;
        this.salle = salle;
    }

    public Integer getId() {
        return idCours;
    }

    public void setId(Integer id) {
        this.idCours = id;
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

    public Enum getTypeCours() {
        return typeCours;
    }

    public void setTypeCours(Enum typeCours) {
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
}
