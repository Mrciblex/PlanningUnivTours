package com.univtime.informatique.dto.salleDto;

import com.univtime.informatique.constants.TypeBesoin;
import com.univtime.informatique.entities.ids.BesoinSalleId;

import java.util.Objects;

public class BesoinSalleSalleDto {
    private BesoinSalleId idBesoinSalle;
    private TypeBesoin typeBesoin;

    public BesoinSalleSalleDto() {
    }

    public BesoinSalleSalleDto(
            BesoinSalleId idBesoinSalle,
            TypeBesoin typeBesoin) {
        this.idBesoinSalle = idBesoinSalle;
        this.typeBesoin = typeBesoin;
    }

    public BesoinSalleId getIdBesoinSalle() {
        return idBesoinSalle;
    }

    public void setIdBesoinSalle(BesoinSalleId idBesoinSalle) {
        this.idBesoinSalle = idBesoinSalle;
    }

    public TypeBesoin getTypeBesoin() {
        return typeBesoin;
    }

    public void setTypeBesoin(TypeBesoin typeBesoin) {
        this.typeBesoin = typeBesoin;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idBesoinSalle);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BesoinSalleSalleDto dto = (BesoinSalleSalleDto) obj;
        return Objects.equals(idBesoinSalle, dto.idBesoinSalle);
    }
}
