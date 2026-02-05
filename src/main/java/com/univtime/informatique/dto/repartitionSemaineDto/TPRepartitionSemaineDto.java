package com.univtime.informatique.dto.repartitionSemaineDto;

import com.univtime.informatique.dto.tpDto.*;

import java.util.Objects;

public class TPRepartitionSemaineDto {
    private ProfesseurTPDto professeurDto;
    private SousGroupeTPDto sousGroupeDto;
    private ComposanteTPDto composanteDto;

    public TPRepartitionSemaineDto() {

    }

    public TPRepartitionSemaineDto(ProfesseurTPDto professeurDto, SousGroupeTPDto sousGroupeDto, ComposanteTPDto composanteDto) {
        this.professeurDto = professeurDto;
        this.sousGroupeDto = sousGroupeDto;
        this.composanteDto = composanteDto;
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

    public ComposanteTPDto getComposanteDto() {
        return composanteDto;
    }

    public void setComposanteDto(ComposanteTPDto composanteDto) {
        this.composanteDto = composanteDto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(professeurDto, sousGroupeDto, composanteDto);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TPRepartitionSemaineDto tpDto = (TPRepartitionSemaineDto) obj;
        return Objects.equals(professeurDto, tpDto.professeurDto)
                && Objects.equals(sousGroupeDto, tpDto.sousGroupeDto)
                && Objects.equals(composanteDto, tpDto.composanteDto);
    }
}
