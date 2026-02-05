package com.univtime.informatique.entities;

import com.univtime.informatique.constants.TypeBesoin;
import com.univtime.informatique.entities.ids.BesoinSalleId;
import jakarta.persistence.*;

@Entity
@Table(name = "besoinSalle")
public class BesoinSalleEntity {
    @EmbeddedId
    private BesoinSalleId idBesoinSalle;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idSalle")
    @JoinColumn(name = "idSalle", referencedColumnName = "idSalle", insertable = false, updatable = false)
    private SalleEntity salle;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idComposante")
    @JoinColumn(name = "idComposante", referencedColumnName = "idComposante", insertable = false, updatable = false)
    private ComposanteEntity composante;

    @Column(name = "typeBesoin", nullable = false)
    private TypeBesoin typeBesoin;

    public BesoinSalleEntity() {

    }

    public BesoinSalleId getIdBesoinSalle() {
        return idBesoinSalle;
    }

    public void setIdBesoinSalle(BesoinSalleId idBesoinSalle) {
        this.idBesoinSalle = idBesoinSalle;
    }

    public SalleEntity getSalle() {
        return salle;
    }

    public void setSalle(SalleEntity salle) {
        this.salle = salle;
    }

    public ComposanteEntity getComposante() {
        return composante;
    }

    public void setComposante(ComposanteEntity composante) {
        this.composante = composante;
    }

    public TypeBesoin getTypeBesoin() {
        return typeBesoin;
    }

    public void setTypeBesoin(TypeBesoin typeBesoin) {
        this.typeBesoin = typeBesoin;
    }
}
