package com.univtime.informatique.dto.composanteDto;

import com.univtime.informatique.dto.tpDto.ProfesseurTPDto;
import com.univtime.informatique.dto.tpDto.RepartitionSemaineTPDto;
import com.univtime.informatique.dto.tpDto.SousGroupeTPDto;

import java.util.Objects;

public class TPComposanteDto {
    private ProfesseurTPDto professeurDto;
    private SousGroupeTPDto sousGroupeDto;
    private RepartitionSemaineTPDto repartitionSemaineDto;

    public TPComposanteDto() {

    }

    public TPComposanteDto(ProfesseurTPDto professeurDto, SousGroupeTPDto sousGroupeDto, RepartitionSemaineTPDto repartitionSemaineDto) {
        this.professeurDto = professeurDto;
        this.sousGroupeDto = sousGroupeDto;
        this.repartitionSemaineDto = repartitionSemaineDto;
    }

    public ProfesseurTPDto getProfesseurDto() {
        return professeurDto;
    }

    public void setProfesseurDto(ProfesseurTPDto professeurDto) {
        this.professeurDto = professeurDto;
    }

    public SousGroupeTPDto getSousGroupeDto() {
        return sousGroupeDto;
    }

    public void setSousGroupeDto(SousGroupeTPDto sousGroupeDto) {
        this.sousGroupeDto = sousGroupeDto;
    }

    public RepartitionSemaineTPDto getRepartitionSemaineDto() {
        return repartitionSemaineDto;
    }

    public void setRepartitionSemaineDto(RepartitionSemaineTPDto repartitionSemaineDto) {
        this.repartitionSemaineDto = repartitionSemaineDto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(professeurDto, sousGroupeDto, repartitionSemaineDto);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TPComposanteDto tpDto = (TPComposanteDto) obj;
        return Objects.equals(professeurDto, tpDto.professeurDto)
                && Objects.equals(sousGroupeDto, tpDto.sousGroupeDto)
                && Objects.equals(repartitionSemaineDto, tpDto.repartitionSemaineDto);
    }
}
