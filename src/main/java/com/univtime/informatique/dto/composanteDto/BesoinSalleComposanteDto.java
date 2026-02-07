package com.univtime.informatique.dto.composanteDto;

import com.univtime.informatique.constants.TypeBesoin;
import com.univtime.informatique.dto.ids.BesoinSalleIdDto;

import java.util.Objects;

public class BesoinSalleComposanteDto {
    private BesoinSalleIdDto besoinSalleId;
    private TypeBesoin typeBesoin;

    public BesoinSalleComposanteDto() {
    }

    public BesoinSalleComposanteDto(BesoinSalleIdDto besoinSalleId, TypeBesoin typeBesoin) {
        this.besoinSalleId = besoinSalleId;
        this.typeBesoin = typeBesoin;
    }

    public BesoinSalleIdDto getBesoinSalleId() {
        return besoinSalleId;
    }

    public void setBesoinSalleId(BesoinSalleIdDto besoinSalleId) {
        this.besoinSalleId = besoinSalleId;
    }

    public TypeBesoin getTypeBesoin() {
        return typeBesoin;
    }

    public void setTypeBesoin(TypeBesoin typeBesoin) {
        this.typeBesoin = typeBesoin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(besoinSalleId);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        BesoinSalleComposanteDto that = (BesoinSalleComposanteDto) obj;
        return Objects.equals(besoinSalleId, that.besoinSalleId);
    }
}
