package com.univtime.informatique.dto.promoDto;

import com.univtime.informatique.dto.cmDto.*;
import com.univtime.informatique.entities.ids.CMId;

import java.util.Objects;

public class CMPromoDto {
    private CMId idCM;

    public CMPromoDto() {

    }

    public CMPromoDto(
            CMId idCM) {
        this.idCM = idCM;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCM);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        CMPromoDto cmDto = (CMPromoDto) obj;
        return Objects.equals(idCM, cmDto.idCM);
    }
}
