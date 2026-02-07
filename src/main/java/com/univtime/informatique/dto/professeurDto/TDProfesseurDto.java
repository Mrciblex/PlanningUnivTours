package com.univtime.informatique.dto.professeurDto;

import com.univtime.informatique.dto.tdDto.*;
import com.univtime.informatique.entities.ids.TPId;

import java.util.Objects;

public class TDProfesseurDto {
    private TPId idTP;

    public TDProfesseurDto() {

    }

    public TDProfesseurDto(
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
        TDProfesseurDto tdDto = (TDProfesseurDto) obj;
        return Objects.equals(idTP, tdDto.idTP);
    }
}
