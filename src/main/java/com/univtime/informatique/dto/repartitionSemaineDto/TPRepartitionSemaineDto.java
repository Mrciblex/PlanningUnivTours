package com.univtime.informatique.dto.repartitionSemaineDto;

import com.univtime.informatique.dto.tpDto.*;
import com.univtime.informatique.entities.ids.TPId;

import java.util.Objects;

public class TPRepartitionSemaineDto {
    private TPId idTP;

    public TPRepartitionSemaineDto() {

    }

    public TPRepartitionSemaineDto(
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
        return Objects.hashCode(idTP);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TPRepartitionSemaineDto that = (TPRepartitionSemaineDto) o;
        return Objects.equals(idTP, that.idTP);
    }
}
