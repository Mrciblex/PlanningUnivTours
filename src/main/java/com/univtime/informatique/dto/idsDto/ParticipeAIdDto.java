package com.univtime.informatique.dto.idsDto;

import java.util.Objects;

public class ParticipeAIdDto {

    private Integer idSousGroupe;
    private Integer idCours;

    // Constructeur par défaut
    public ParticipeAIdDto() {
    }

    // Constructeur complet
    public ParticipeAIdDto(Integer idSousGroupe,
                           Integer idCours) {
        this.idSousGroupe = idSousGroupe;
        this.idCours = idCours;
    }

    // Getters et Setters
    public Integer getIdSousGroupe() { return idSousGroupe; }
    public void setIdSousGroupe(Integer idSousGroupe) { this.idSousGroupe = idSousGroupe; }

    public Integer getIdCours() { return idCours; }
    public void setIdCours(Integer idCours) { this.idCours = idCours; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParticipeAIdDto that = (ParticipeAIdDto) o;
        return Objects.equals(idSousGroupe, that.idSousGroupe) &&
                Objects.equals(idCours, that.idCours);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSousGroupe, idCours);
    }
}