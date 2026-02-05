package com.univtime.informatique.entities.ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BesoinSalleId implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "idSalle", nullable = false)
    private Integer idSalle;

    @Column(name = "idComposante", nullable = false)
    private Integer idComposante;

    public BesoinSalleId() {

    }

    public BesoinSalleId(Integer idSalle, Integer idComposante) {
        this.idSalle = idSalle;
        this.idComposante = idComposante;
    }

    public Integer getIdSalle() {
        return idSalle;
    }

    public void setIdSalle(Integer idSalle) {
        this.idSalle = idSalle;
    }

    public Integer getIdComposante() {
        return idComposante;
    }

    public void setIdComposante(Integer idComposante) {
        this.idComposante = idComposante;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSalle, idComposante);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BesoinSalleId other = (BesoinSalleId) obj;
        return Objects.equals(this.idSalle, other.idSalle) && Objects.equals(this.idComposante, other.idComposante);
    }
}
