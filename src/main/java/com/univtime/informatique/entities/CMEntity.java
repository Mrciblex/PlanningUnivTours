package com.univtime.informatique.entities;

import com.univtime.informatique.entities.ids.CMId;
import jakarta.persistence.*;

@Entity
@Table(name = "cms")
public class CMEntity {
    @EmbeddedId
    private CMId idCM;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idProf")
    @JoinColumn(name = "idProf", referencedColumnName = "idProf", insertable = false, updatable = false)
    private ProfesseurEntity professeur;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idPromo")
    @JoinColumn(name = "idPromo", referencedColumnName = "idPromo", insertable = false, updatable = false)
    private PromoEntity promo;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idComposante")
    @JoinColumn(name = "idComposante", referencedColumnName = "idComposante", insertable = false, updatable = false)
    private ComposanteEntity composante;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idRepartitionSemaine")
    @JoinColumn(name = "idRepartitionSemaine", referencedColumnName = "idRepartitionSemaine", insertable = false, updatable = false)
    private RepartitionSemaineEntity repartitionSemaine;

    public CMEntity() {

    }
}
