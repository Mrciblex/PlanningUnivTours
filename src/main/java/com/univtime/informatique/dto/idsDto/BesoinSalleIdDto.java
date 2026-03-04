package com.univtime.informatique.dto.idsDto;

import java.util.Objects;

/**
 * DTO représentant l'identifiant composite pour les besoins d'une salle.
 */
public class BesoinSalleIdDto {

    private Integer idSalle;
    private Integer idComposante;

    // Constructeur par défaut
    public BesoinSalleIdDto() {
    }

    // Constructeur complet
    public BesoinSalleIdDto(Integer idSalle,
                            Integer idComposante) {
        this.idSalle = idSalle;
        this.idComposante = idComposante;
    }

    // Getters et Setters
    public Integer getIdSalle() { return idSalle; }
    public void setIdSalle(Integer idSalle) { this.idSalle = idSalle; }

    public Integer getIdComposante() { return idComposante; }
    public void setIdComposante(Integer idComposante) { this.idComposante = idComposante; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BesoinSalleIdDto that = (BesoinSalleIdDto) o;
        return Objects.equals(idSalle, that.idSalle) &&
                Objects.equals(idComposante, that.idComposante);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSalle, idComposante);
    }
}