package com.univtime.informatique.dto.sousGroupeDto;

import com.univtime.informatique.dto.tpDto.*;
import com.univtime.informatique.entities.ids.TPId;

import java.util.Objects;

public class TPSousGroupeDto {
    private TPId idTP;

    public TPSousGroupeDto() {

    }

    public TPSousGroupeDto(
            TPId idTP) {
        this.idTP = idTP;
    }

    public TPId getIdTP() {
        return idTP;
    }

    public void setIdTP(TPId idTP) {
        this.idTP = idTP;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTP);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TPSousGroupeDto tpDto = (TPSousGroupeDto) obj;
        return Objects.equals(idTP, tpDto.idTP);
    }
}
