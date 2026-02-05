package com.univtime.informatique.entities.ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PromoEstComposeeId implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "idPromo", nullable = false)
    private Integer idPromo;

    @Column(name = "idModule", nullable = false)
    private Integer idModule;

    public PromoEstComposeeId() {

    }

    public PromoEstComposeeId(Integer idPromo, Integer idModule) {
        this.idPromo = idPromo;
        this.idModule = idModule;
    }

    public Integer getIdPromo() {
        return idPromo;
    }

    public void setIdPromo(Integer idPromo) {
        this.idPromo = idPromo;
    }

    public Integer getIdModule() {
        return idModule;
    }

    public void setIdModule(Integer idModule) {
        this.idModule = idModule;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPromo, idModule);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PromoEstComposeeId other = (PromoEstComposeeId) obj;
        return Objects.equals(this.idPromo, other.idPromo) && Objects.equals(this.idModule, other.idModule);
    }
}
