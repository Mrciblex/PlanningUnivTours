package com.univtime.informatique.dto.cmDto;

import java.util.Objects;

public class CMDto {
    private ProfesseurCMDto professeurDto;
    private PromoCMDto promoDto;
    private ComposanteCMDto composanteDto;
    private RepartitionSemaineCMDto repartitionSemaineDto;

    public CMDto() {

    }

    public CMDto(ProfesseurCMDto professeurDto, PromoCMDto promoDto, ComposanteCMDto composanteDto, RepartitionSemaineCMDto repartitionSemaineDto) {
        this.professeurDto = professeurDto;
        this.promoDto = promoDto;
        this.composanteDto = composanteDto;
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
        return Objects.hash(professeurDto, promoDto, composanteDto, repartitionSemaineDto);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        CMDto cmDto = (CMDto) obj;
        return Objects.equals(professeurDto, cmDto.professeurDto)
                && Objects.equals(promoDto, cmDto.promoDto)
                && Objects.equals(composanteDto, cmDto.composanteDto)
                && Objects.equals(repartitionSemaineDto, cmDto.repartitionSemaineDto);
    }
}
