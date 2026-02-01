package com.univtime.informatique.entities.ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TDId implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "idProf", nullable = false)
    private Integer idProf;

    @Column(name = "idGroupe", nullable = false)
    private Integer idGroupe;

    @Column(name = "idComposante", nullable = false)
    private Integer idComposante;

    @Column(name = "idRepartitionSemaine", nullable = false)
    private Integer idRepartitionSemaine;

    public TDId() {

    }

    public TDId(Integer idProf, Integer idGroupe, Integer idComposante, Integer idRepartitionSemaine) {
        this.idProf = idProf;
        this.idGroupe = idGroupe;
        this.idComposante = idComposante;
        this.idRepartitionSemaine = idRepartitionSemaine;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProf, idGroupe, idComposante, idRepartitionSemaine);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TDId other = (TDId) obj;
        return Objects.equals(this.idProf, other.idProf)
                && Objects.equals(this.idGroupe, other.idGroupe)
                && Objects.equals(this.idComposante, other.idComposante)
                && Objects.equals(this.idRepartitionSemaine, other.idRepartitionSemaine);
    }
}
