package com.univtime.informatique.dto.promoDto;

import com.univtime.informatique.dto.cmDto.*;

import java.util.Objects;

public class CMPromoDto {
    private ProfesseurCMDto professeurDto;
    private ComposanteCMDto composanteDto;
    private RepartitionSemaineCMDto repartitionSemaineDto;

    public CMPromoDto() {

    }

    public CMPromoDto(ProfesseurCMDto professeurDto, ComposanteCMDto composanteDto, RepartitionSemaineCMDto repartitionSemaineDto) {
        this.professeurDto = professeurDto;
        this.composanteDto = composanteDto;
        this.repartitionSemaineDto = repartitionSemaineDto;
    }

    public ProfesseurCMDto getProfesseurDto() {
        return professeurDto;
    }

    public void setProfesseurDto(ProfesseurCMDto professeurDto) {
        this.professeurDto = professeurDto;
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
        return Objects.hash(professeurDto, composanteDto, repartitionSemaineDto);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        CMPromoDto cmDto = (CMPromoDto) obj;
        return Objects.equals(professeurDto, cmDto.professeurDto)
                && Objects.equals(composanteDto, cmDto.composanteDto)
                && Objects.equals(repartitionSemaineDto, cmDto.repartitionSemaineDto);
    }
}
