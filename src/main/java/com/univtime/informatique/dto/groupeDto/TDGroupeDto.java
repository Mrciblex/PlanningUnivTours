package com.univtime.informatique.dto.groupeDto;

import com.univtime.informatique.dto.tdDto.ComposanteTDDto;
import com.univtime.informatique.dto.tdDto.ProfesseurTDDto;
import com.univtime.informatique.dto.tdDto.RepartitionSemaineTDDto;

import java.util.Objects;

public class TDGroupeDto {
    private ProfesseurTDDto professeurDto;
    private ComposanteTDDto composanteDto;
    private RepartitionSemaineTDDto repartitionSemaineDto;

    public TDGroupeDto() {

    }

    public TDGroupeDto(ProfesseurTDDto professeurDto, ComposanteTDDto composanteDto, RepartitionSemaineTDDto repartitionSemaineDto) {
        this.professeurDto = professeurDto;
        this.composanteDto = composanteDto;
        this.repartitionSemaineDto = repartitionSemaineDto;
    }

    public ProfesseurTDDto getProfesseurDto() {
        return professeurDto;
    }

    public void setProfesseurDto(ProfesseurTDDto professeurDto) {
        this.professeurDto = professeurDto;
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
        return Objects.hash(professeurDto, composanteDto, repartitionSemaineDto);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TDGroupeDto tdDto = (TDGroupeDto) obj;
        return Objects.equals(professeurDto, tdDto.professeurDto)
                && Objects.equals(composanteDto, tdDto.composanteDto)
                && Objects.equals(repartitionSemaineDto, tdDto.repartitionSemaineDto);
    }
}
