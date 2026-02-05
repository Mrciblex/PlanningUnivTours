package com.univtime.informatique.dto.composanteDto;

import com.univtime.informatique.dto.cmDto.ProfesseurCMDto;
import com.univtime.informatique.dto.cmDto.PromoCMDto;
import com.univtime.informatique.dto.cmDto.RepartitionSemaineCMDto;
import com.univtime.informatique.entities.ComposanteEntity;
import com.univtime.informatique.entities.ProfesseurEntity;
import com.univtime.informatique.entities.PromoEntity;
import com.univtime.informatique.entities.RepartitionSemaineEntity;
import com.univtime.informatique.entities.ids.CMId;

import java.util.Objects;

public class CMComposanteDto {
    private ProfesseurCMDto professeurDto;
    private PromoCMDto promoDto;
    private RepartitionSemaineCMDto repartitionSemaineDto;

    public CMComposanteDto() {

    }

    public CMComposanteDto(ProfesseurCMDto professeurDto, PromoCMDto promoDto, RepartitionSemaineCMDto repartitionSemaineDto) {
        this.professeurDto = professeurDto;
        this.promoDto = promoDto;
        this.repartitionSemaineDto = repartitionSemaineDto;
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

    public RepartitionSemaineCMDto getRepartitionSemaineDto() {
        return repartitionSemaineDto;
    }

    public void setRepartitionSemaineDto(RepartitionSemaineCMDto repartitionSemaineDto) {
        this.repartitionSemaineDto = repartitionSemaineDto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(professeurDto, promoDto, repartitionSemaineDto);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        CMComposanteDto cmDto = (CMComposanteDto) obj;
        return Objects.equals(professeurDto, cmDto.professeurDto)
                && Objects.equals(promoDto, cmDto.promoDto)
                && Objects.equals(repartitionSemaineDto, cmDto.repartitionSemaineDto);
    }
}
