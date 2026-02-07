package com.univtime.informatique.dto.sousGroupeDto;

import com.univtime.informatique.dto.tpDto.*;
import com.univtime.informatique.entities.ids.TPId;

import java.util.Objects;

public class TPSousGroupeDto {
    private TPId idTP;
    private ProfesseurTPDto professeurDto;
    private ComposanteTPDto composanteDto;
    private RepartitionSemaineTPDto repartitionSemaineDto;

    public TPSousGroupeDto() {

    }

    public TPSousGroupeDto(ProfesseurTPDto professeurDto, ComposanteTPDto composanteDto, RepartitionSemaineTPDto repartitionSemaineDto) {
        this.professeurDto = professeurDto;
        this.composanteDto = composanteDto;
        this.repartitionSemaineDto = repartitionSemaineDto;
    }

    public ProfesseurTPDto getProfesseurDto() {
        return professeurDto;
    }

    public void setProfesseurDto(ProfesseurTPDto professeurDto) {
        this.professeurDto = professeurDto;
    }

    public ComposanteTPDto getComposanteDto() {
        return composanteDto;
    }

    public void setComposanteDto(ComposanteTPDto composanteDto) {
        this.composanteDto = composanteDto;
    }

    public RepartitionSemaineTPDto getRepartitionSemaineDto() {
        return repartitionSemaineDto;
    }

    public void setRepartitionSemaineDto(RepartitionSemaineTPDto repartitionSemaineDto) {
        this.repartitionSemaineDto = repartitionSemaineDto;
    }

    public TPId getIdTP() {
        return idTP;
    }

    public void setIdTP(TPId idTP) {
        this.idTP = idTP;
    }

    @Override
    public int hashCode() {
        return Objects.hash(professeurDto, composanteDto, repartitionSemaineDto);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TPSousGroupeDto tpDto = (TPSousGroupeDto) obj;
        return Objects.equals(professeurDto, tpDto.professeurDto)
                && Objects.equals(composanteDto, tpDto.composanteDto)
                && Objects.equals(repartitionSemaineDto, tpDto.repartitionSemaineDto);
    }
}
