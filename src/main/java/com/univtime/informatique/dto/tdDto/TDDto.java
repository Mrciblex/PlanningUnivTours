package com.univtime.informatique.dto.tdDto;

import java.util.Objects;

public class TDDto {
    private ProfesseurTDDto professeurDto;
    private GroupeTDDto groupeDto;
    private ComposanteTDDto composanteDto;
    private RepartitionSemaineTDDto repartitionSemaineDto;

    public TDDto() {

    }

    public TDDto(ProfesseurTDDto professeurDto,
                 GroupeTDDto groupeDto,
                 ComposanteTDDto composanteDto,
                 RepartitionSemaineTDDto repartitionSemaineDto) {
        this.professeurDto = professeurDto;
        this.groupeDto = groupeDto;
        this.composanteDto = composanteDto;
        this.repartitionSemaineDto = repartitionSemaineDto;
    }

    public ProfesseurTDDto getProfesseurDto() {
        return professeurDto;
    }

    public void setProfesseurDto(ProfesseurTDDto professeurDto) {
        this.professeurDto = professeurDto;
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
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TDDto tdDto = (TDDto) o;
        return Objects.equals(professeurDto, tdDto.professeurDto)
                && Objects.equals(groupeDto, tdDto.groupeDto)
                && Objects.equals(composanteDto, tdDto.composanteDto)
                && Objects.equals(repartitionSemaineDto, tdDto.repartitionSemaineDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(professeurDto, groupeDto, composanteDto, repartitionSemaineDto);
    }
}
