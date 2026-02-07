package com.univtime.informatique.dto.ids;

import java.util.Objects;

/**
 * DTO représentant l'identifiant composite de CM.
 */
public class CMIdDto {

    private Integer idProf;
    private Integer idPromo;
    private Integer idComposante;
    private Integer idRepartitionSemaine;

    // Constructeur par défaut
    public CMIdDto() {
    }

    // Constructeur complet
    public CMIdDto(Integer idProf,
                   Integer idPromo,
                   Integer idComposante,
                   Integer idRepartitionSemaine) {
        this.idProf = idProf;
        this.idPromo = idPromo;
        this.idComposante = idComposante;
        this.idRepartitionSemaine = idRepartitionSemaine;
    }

    // Getters et Setters
    public Integer getIdProf() { return idProf; }
    public void setIdProf(Integer idProf) { this.idProf = idProf; }

    public Integer getIdPromo() { return idPromo; }
    public void setIdPromo(Integer idPromo) { this.idPromo = idPromo; }

    public Integer getIdComposante() { return idComposante; }
    public void setIdComposante(Integer idComposante) { this.idComposante = idComposante; }

    public Integer getIdRepartitionSemaine() { return idRepartitionSemaine; }
    public void setIdRepartitionSemaine(Integer idRepartitionSemaine) { this.idRepartitionSemaine = idRepartitionSemaine; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CMIdDto cmIdDto = (CMIdDto) o;
        return Objects.equals(idProf, cmIdDto.idProf) &&
                Objects.equals(idPromo, cmIdDto.idPromo) &&
                Objects.equals(idComposante, cmIdDto.idComposante) &&
                Objects.equals(idRepartitionSemaine, cmIdDto.idRepartitionSemaine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProf, idPromo, idComposante, idRepartitionSemaine);
    }
}