package com.univtime.informatique.dto.tpDto;

import java.util.Objects;

public class TPDto {
    private ProfesseurTPDto professeurDto;
    private SousGroupeTPDto sousGroupeDto;
    private ComposanteTPDto composanteDto;
    private RepartitionSemaineTPDto repartitionSemaineDto;

    public TPDto() {

    }

    public TPDto(ProfesseurTPDto professeurDto, SousGroupeTPDto sousGroupeDto, ComposanteTPDto composanteDto, RepartitionSemaineTPDto repartitionSemaineDto) {
        this.professeurDto = professeurDto;
        this.sousGroupeDto = sousGroupeDto;
        this.composanteDto = composanteDto;
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
        return Objects.hash(professeurDto, sousGroupeDto, composanteDto, repartitionSemaineDto);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TPDto tpDto = (TPDto) obj;
        return Objects.equals(professeurDto, tpDto.professeurDto)
                && Objects.equals(sousGroupeDto, tpDto.sousGroupeDto)
                && Objects.equals(composanteDto, tpDto.composanteDto)
                && Objects.equals(repartitionSemaineDto, tpDto.repartitionSemaineDto);
    }
}
