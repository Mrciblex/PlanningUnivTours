package com.univtime.informatique.dto.tdDto;

import com.univtime.informatique.entities.ids.TDId;

import java.util.Objects;

public class TDDto {
    private TDId idTD;

    public TDDto() {

    }

    public TDDto(
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
        TDDto tdDto = (TDDto) obj;
        return Objects.equals(idTD, tdDto.idTD);
    }
}
