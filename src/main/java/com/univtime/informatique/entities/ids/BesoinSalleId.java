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

    @Column(name = "typeBesoin", nullable = false)
    private Enum typeBesoin;

    public BesoinSalleId() {

    }

    public BesoinSalleId(Integer idSalle, Integer idComposante, Enum typeBesoin) {
        this.idSalle = idSalle;
        this.idComposante = idComposante;
        this.typeBesoin = typeBesoin;
    }

    public Enum getTypeBesoin() {
        return typeBesoin;
    }

    public void setTypeBesoin(Enum typeBesoin) {
        this.typeBesoin = typeBesoin;
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
