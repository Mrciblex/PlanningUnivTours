package com.univtime.informatique.dto.professeurDto;

import com.univtime.informatique.dto.tpDto.*;
import com.univtime.informatique.entities.ids.TPId;

import java.util.Objects;

public class TPProfesseurDto {
    private TPId idTP;

    public TPProfesseurDto() {

    }

    public TPProfesseurDto(
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
        TPProfesseurDto tpDto = (TPProfesseurDto) obj;
        return Objects.equals(idTP, tpDto.idTP);
    }
}
