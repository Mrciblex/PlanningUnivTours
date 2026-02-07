package com.univtime.informatique.dto.repartitionSemaineDto;

import com.univtime.informatique.dto.cmDto.*;
import com.univtime.informatique.entities.ids.CMId;

import java.util.Objects;

public class CMRepartitionSemaineDto {
    private CMId idCM;

    public CMRepartitionSemaineDto() {

    }

    public CMRepartitionSemaineDto(
            CMId idCM) {
        this.idCM = idCM;
    }

    public CMId getIdCM() {
        return idCM;
    }

    public void setIdCM(CMId idCM) {
        this.idCM = idCM;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCM);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CMRepartitionSemaineDto cmDto = (CMRepartitionSemaineDto) obj;
        return Objects.equals(idCM, cmDto.idCM);
    }
}
