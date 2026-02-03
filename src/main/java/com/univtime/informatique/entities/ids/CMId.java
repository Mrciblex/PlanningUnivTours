package com.univtime.informatique.entities.ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public int hashCode() {
        return Objects.hash(idProf, idPromo, idComposante, idRepartitionSemaine);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CMId other = (CMId) obj;
        return Objects.equals(this.idProf, other.idProf)
                && Objects.equals(this.idPromo, other.idPromo)
                && Objects.equals(this.idComposante, other.idComposante)
                && Objects.equals(this.idRepartitionSemaine, other.idRepartitionSemaine);
    }
}
