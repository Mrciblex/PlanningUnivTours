package com.univtime.informatique.dto.professeurDto;

import com.univtime.informatique.dto.cmDto.*;

import java.util.Objects;

public class CMProfesseurDto {
    private PromoCMDto promoDto;
    private ComposanteCMDto composanteDto;
    private RepartitionSemaineCMDto repartitionSemaineDto;

    public CMProfesseurDto() {

    }

    public CMProfesseurDto(PromoCMDto promoDto, ComposanteCMDto composanteDto, RepartitionSemaineCMDto repartitionSemaineDto) {
        this.promoDto = promoDto;
        this.composanteDto = composanteDto;
        this.repartitionSemaineDto = repartitionSemaineDto;
    }

    public PromoCMDto getPromoDto() {
        return promoDto;
    }

    public void setPromoDto(PromoCMDto promoDto) {
        this.promoDto = promoDto;
    }

    public ComposanteCMDto getComposanteDto() {
        return composanteDto;
    }

    public void setComposanteDto(ComposanteCMDto composanteDto) {
        this.composanteDto = composanteDto;
    }

    public RepartitionSemaineCMDto getRepartitionSemaineDto() {
        return repartitionSemaineDto;
    }

    public void setRepartitionSemaineDto(RepartitionSemaineCMDto repartitionSemaineDto) {
        this.repartitionSemaineDto = repartitionSemaineDto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(promoDto, composanteDto, repartitionSemaineDto);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        CMProfesseurDto cmDto = (CMProfesseurDto) obj;
        return Objects.equals(promoDto, cmDto.promoDto)
                && Objects.equals(composanteDto, cmDto.composanteDto)
                && Objects.equals(repartitionSemaineDto, cmDto.repartitionSemaineDto);
    }
}
