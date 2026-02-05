package com.univtime.informatique.dto.repartitionSemaineDto;

import com.univtime.informatique.dto.cmDto.*;

import java.util.Objects;

public class CMRepartitionSemaineDto {
    private ProfesseurCMDto professeurDto;
    private PromoCMDto promoDto;
    private ComposanteCMDto composanteDto;

    public CMRepartitionSemaineDto() {

    }

    public CMRepartitionSemaineDto(ProfesseurCMDto professeurDto, PromoCMDto promoDto, ComposanteCMDto composanteDto) {
        this.professeurDto = professeurDto;
        this.promoDto = promoDto;
        this.composanteDto = composanteDto;
    }

    public ProfesseurCMDto getProfesseurDto() {
        return professeurDto;
    }

    public void setProfesseurDto(ProfesseurCMDto professeurDto) {
        this.professeurDto = professeurDto;
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

    @Override
    public int hashCode() {
        return Objects.hash(professeurDto, promoDto, composanteDto);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        CMRepartitionSemaineDto cmDto = (CMRepartitionSemaineDto) obj;
        return Objects.equals(professeurDto, cmDto.professeurDto)
                && Objects.equals(promoDto, cmDto.promoDto)
                && Objects.equals(composanteDto, cmDto.composanteDto);
    }
}
