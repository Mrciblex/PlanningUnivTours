package com.univtime.informatique.dto.repartitionSemaineDto;

import com.univtime.informatique.dto.tdDto.*;

import java.util.Objects;

public class TDRepartitionSemaineDto {
    private ProfesseurTDDto professeurDto;
    private GroupeTDDto groupeDto;
    private ComposanteTDDto composanteDto;

    public TDRepartitionSemaineDto() {

    }

    public TDRepartitionSemaineDto(ProfesseurTDDto professeurDto, GroupeTDDto groupeDto, ComposanteTDDto composanteDto) {
        this.professeurDto = professeurDto;
        this.groupeDto = groupeDto;
        this.composanteDto = composanteDto;
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

    @Override
    public int hashCode() {
        return Objects.hash(professeurDto, groupeDto, composanteDto);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TDRepartitionSemaineDto tdDto = (TDRepartitionSemaineDto) obj;
        return Objects.equals(professeurDto, tdDto.professeurDto)
                && Objects.equals(groupeDto, tdDto.groupeDto)
                && Objects.equals(composanteDto, tdDto.composanteDto);
    }
}
