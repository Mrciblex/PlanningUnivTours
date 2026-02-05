package com.univtime.informatique.dto.professeurDto;

import com.univtime.informatique.dto.tpDto.*;

import java.util.Objects;

public class TPProfesseurDto {
    private SousGroupeTPDto sousGroupeDto;
    private ComposanteTPDto composanteDto;
    private RepartitionSemaineTPDto repartitionSemaineDto;

    public TPProfesseurDto() {

    }

    public TPProfesseurDto(SousGroupeTPDto sousGroupeDto, ComposanteTPDto composanteDto, RepartitionSemaineTPDto repartitionSemaineDto) {
        this.sousGroupeDto = sousGroupeDto;
        this.composanteDto = composanteDto;
        this.repartitionSemaineDto = repartitionSemaineDto;
    }

    public SousGroupeTPDto getSousGroupeDto() {
        return sousGroupeDto;
    }

    public void setSousGroupeDto(SousGroupeTPDto sousGroupeDto) {
        this.sousGroupeDto = sousGroupeDto;
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

    @Override
    public int hashCode() {
        return Objects.hash(sousGroupeDto, composanteDto, repartitionSemaineDto);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TPProfesseurDto tpDto = (TPProfesseurDto) obj;
        return Objects.equals(sousGroupeDto, tpDto.sousGroupeDto)
                && Objects.equals(composanteDto, tpDto.composanteDto)
                && Objects.equals(repartitionSemaineDto, tpDto.repartitionSemaineDto);
    }
}
