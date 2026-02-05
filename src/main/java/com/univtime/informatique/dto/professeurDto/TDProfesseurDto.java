package com.univtime.informatique.dto.professeurDto;

import com.univtime.informatique.dto.tdDto.*;

import java.util.Objects;

public class TDProfesseurDto {
    private GroupeTDDto groupeDto;
    private ComposanteTDDto composanteDto;
    private RepartitionSemaineTDDto repartitionSemaineDto;

    public TDProfesseurDto() {

    }

    public TDProfesseurDto(GroupeTDDto groupeDto, ComposanteTDDto composanteDto, RepartitionSemaineTDDto repartitionSemaineDto) {
        this.groupeDto = groupeDto;
        this.composanteDto = composanteDto;
        this.repartitionSemaineDto = repartitionSemaineDto;
    }

    public GroupeTDDto getGroupeDto() {
        return groupeDto;
    }

    public void setGroupeDto(GroupeTDDto groupeDto) {
        this.groupeDto = groupeDto;
    }

    public ComposanteTDDto getComposanteDto() {
        return composanteDto;
    }

    public void setComposanteDto(ComposanteTDDto composanteDto) {
        this.composanteDto = composanteDto;
    }

    public RepartitionSemaineTDDto getRepartitionSemaineDto() {
        return repartitionSemaineDto;
    }

    public void setRepartitionSemaineDto(RepartitionSemaineTDDto repartitionSemaineDto) {
        this.repartitionSemaineDto = repartitionSemaineDto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupeDto, composanteDto, repartitionSemaineDto);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TDProfesseurDto tdDto = (TDProfesseurDto) obj;
        return Objects.equals(groupeDto, tdDto.groupeDto)
                && Objects.equals(composanteDto, tdDto.composanteDto)
                && Objects.equals(repartitionSemaineDto, tdDto.repartitionSemaineDto);
    }
}
