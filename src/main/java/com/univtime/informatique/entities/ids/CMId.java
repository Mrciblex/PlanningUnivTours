package com.univtime.informatique.entities.ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serial;
import java.io.Serializable;

@Embeddable
public class CMId implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "idProf", nullable = false)
    private Integer idProf;

    @Column(name = "idPromo", nullable = false)
    private Integer idPromo;

    @Column(name = "idComposante", nullable = false)
    private Integer idComposante;

    @Column(name = "idRepartitionSemaine", nullable = false)
    private Integer idRepartitionSemaine;

    public  CMId() {

    }

    public CMId(Integer idProf, Integer idPromo, Integer idComposante, Integer idRepartitionSemaine) {
        this.idProf = idProf;
        this.idPromo = idPromo;
        this.idComposante = idComposante;
        this.idRepartitionSemaine = idRepartitionSemaine;
    }
}
