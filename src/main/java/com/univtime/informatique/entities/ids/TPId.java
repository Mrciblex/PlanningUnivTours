package com.univtime.informatique.entities.ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TPId implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "idProf", nullable = false)
    private Integer idProf;

    @Column(name = "idSousGroupe", nullable = false)
    private Integer idSousGroupe;

    @Column(name = "idComposante", nullable = false)
    private Integer idComposante;

    @Column(name = "idRepartitionSemaine", nullable = false)
    private Integer idRepartitionSemaine;

    public TPId() {

    }

    public TPId(Integer idProf, Integer idSousGroupe, Integer idComposante, Integer idRepartitionSemaine) {
        this.idProf = idProf;
        this.idSousGroupe = idSousGroupe;
        this.idComposante = idComposante;
        this.idRepartitionSemaine = idRepartitionSemaine;
    }

    public Integer getIdProf() {
        return idProf;
    }

    public void setIdProf(Integer idProf) {
        this.idProf = idProf;
    }

    public Integer getIdSousGroupe() {
        return idSousGroupe;
    }

    public void setIdSousGroupe(Integer idSousGroupe) {
        this.idSousGroupe = idSousGroupe;
    }

    public Integer getIdComposante() {
        return idComposante;
    }

    public void setIdComposante(Integer idComposante) {
        this.idComposante = idComposante;
    }

    public Integer getIdRepartitionSemaine() {
        return idRepartitionSemaine;
    }

    public void setIdRepartitionSemaine(Integer idRepartitionSemaine) {
        this.idRepartitionSemaine = idRepartitionSemaine;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProf, idSousGroupe, idComposante, idRepartitionSemaine);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TPId other = (TPId) obj;
        return Objects.equals(this.idProf, other.idProf)
                && Objects.equals(this.idSousGroupe, other.idSousGroupe)
                && Objects.equals(this.idComposante, other.idComposante)
                && Objects.equals(this.idRepartitionSemaine, other.idRepartitionSemaine);
    }
}
