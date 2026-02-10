package com.univtime.informatique.dto.idsDto;

import java.util.Objects;

public class TDIdDto {

    private Integer idProf;
    private Integer idGroupe;
    private Integer idComposante;
    private Integer idRepartitionSemaine;

    // Constructeur par défaut
    public TDIdDto() {
    }

    // Constructeur complet
    public TDIdDto(Integer idProf,
                   Integer idGroupe,
                   Integer idComposante,
                   Integer idRepartitionSemaine) {
        this.idProf = idProf;
        this.idGroupe = idGroupe;
        this.idComposante = idComposante;
        this.idRepartitionSemaine = idRepartitionSemaine;
    }

    // Getters et Setters
    public Integer getIdProf() { return idProf; }
    public void setIdProf(Integer idProf) { this.idProf = idProf; }

    public Integer getIdGroupe() { return idGroupe; }
    public void setIdGroupe(Integer idGroupe) { this.idGroupe = idGroupe; }

    public Integer getIdComposante() { return idComposante; }
    public void setIdComposante(Integer idComposante) { this.idComposante = idComposante; }

    public Integer getIdRepartitionSemaine() { return idRepartitionSemaine; }
    public void setIdRepartitionSemaine(Integer idRepartitionSemaine) { this.idRepartitionSemaine = idRepartitionSemaine; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TDIdDto tdIdDto = (TDIdDto) o;
        return Objects.equals(idProf, tdIdDto.idProf) &&
                Objects.equals(idGroupe, tdIdDto.idGroupe) &&
                Objects.equals(idComposante, tdIdDto.idComposante) &&
                Objects.equals(idRepartitionSemaine, tdIdDto.idRepartitionSemaine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProf, idGroupe, idComposante, idRepartitionSemaine);
    }
}