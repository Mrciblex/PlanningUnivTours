package com.univtime.informatique.dto.professeurDto;

import com.univtime.informatique.dto.cmDto.*;
import com.univtime.informatique.entities.ids.CMId;

import java.util.Objects;

public class CMProfesseurDto {
    private CMId idCM;

    public CMProfesseurDto() {

    }

    public CMProfesseurDto(
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
        CMProfesseurDto cmDto = (CMProfesseurDto) obj;
        return Objects.equals(idCM, cmDto.idCM);
    }
}
