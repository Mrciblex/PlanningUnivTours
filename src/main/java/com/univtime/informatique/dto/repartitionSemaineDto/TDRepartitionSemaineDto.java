package com.univtime.informatique.dto.repartitionSemaineDto;

import com.univtime.informatique.dto.tdDto.*;
import com.univtime.informatique.entities.ids.TDId;

import java.util.Objects;

public class TDRepartitionSemaineDto {
    private TDId idTD;

    public TDRepartitionSemaineDto() {

    }

    public TDRepartitionSemaineDto(
            TDId idTD) {
        this.idTD = idTD;
    }

    public TDId getIdTD() {
        return idTD;
    }

    public void setIdTD(TDId idTD) {
        this.idTD = idTD;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTD);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TDRepartitionSemaineDto tdDto = (TDRepartitionSemaineDto) obj;
        return Objects.equals(idTD, tdDto.idTD);
    }
}
