package com.univtime.informatique.dto.idsDto;

import java.util.Objects;

/**
 * DTO pour l'identifiant composite de la relation PromoEstComposee.
 */
public class PromoEstComposeeIdDto {

    private Integer idPromo;
    private Integer idModule;

    // Constructeur par défaut
    public PromoEstComposeeIdDto() {
    }

    // Constructeur complet
    public PromoEstComposeeIdDto(Integer idPromo,
                                 Integer idModule) {
        this.idPromo = idPromo;
        this.idModule = idModule;
    }

    // Getters et Setters
    public Integer getIdPromo() { return idPromo; }
    public void setIdPromo(Integer idPromo) { this.idPromo = idPromo; }

    public Integer getIdModule() { return idModule; }
    public void setIdModule(Integer idModule) { this.idModule = idModule; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PromoEstComposeeIdDto that = (PromoEstComposeeIdDto) o;
        return Objects.equals(idPromo, that.idPromo) &&
                Objects.equals(idModule, that.idModule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPromo, idModule);
    }
}