package com.univtime.informatique.dto.ids;

import java.util.Objects;

public class TPIdDto {

    private Integer idProf;
    private Integer idSousGroupe;
    private Integer idComposante;
    private Integer idRepartitionSemaine;

    // Constructeur par défaut
    public TPIdDto() {
    }

    // Constructeur complet
    public TPIdDto(Integer idProf,
                   Integer idSousGroupe,
                   Integer idComposante,
                   Integer idRepartitionSemaine) {
        this.idProf = idProf;
        this.idSousGroupe = idSousGroupe;
        this.idComposante = idComposante;
        this.idRepartitionSemaine = idRepartitionSemaine;
    }

    // Getters et Setters
    public Integer getIdProf() { return idProf; }
    public void setIdProf(Integer idProf) { this.idProf = idProf; }

    public Integer getIdSousGroupe() { return idSousGroupe; }
    public void setIdSousGroupe(Integer idSousGroupe) { this.idSousGroupe = idSousGroupe; }

    public Integer getIdComposante() { return idComposante; }
    public void setIdComposante(Integer idComposante) { this.idComposante = idComposante; }

    public Integer getIdRepartitionSemaine() { return idRepartitionSemaine; }
    public void setIdRepartitionSemaine(Integer idRepartitionSemaine) { this.idRepartitionSemaine = idRepartitionSemaine; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TPIdDto tpIdDto = (TPIdDto) o;
        return Objects.equals(idProf, tpIdDto.idProf) &&
                Objects.equals(idSousGroupe, tpIdDto.idSousGroupe) &&
                Objects.equals(idComposante, tpIdDto.idComposante) &&
                Objects.equals(idRepartitionSemaine, tpIdDto.idRepartitionSemaine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProf, idSousGroupe, idComposante, idRepartitionSemaine);
    }
}