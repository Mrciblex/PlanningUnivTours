package com.univtime.informatique.dto.composanteDto;

import com.univtime.informatique.dto.tdDto.GroupeTDDto;
import com.univtime.informatique.dto.tdDto.ProfesseurTDDto;
import com.univtime.informatique.dto.tdDto.RepartitionSemaineTDDto;

import java.util.Objects;

public class TDComposanteDto {
    private ProfesseurTDDto professeurDto;
    private GroupeTDDto groupeDto;
    private RepartitionSemaineTDDto repartitionSemaineDto;

    public TDComposanteDto() {

    }

    public TDComposanteDto(ProfesseurTDDto professeurDto, GroupeTDDto groupeDto, RepartitionSemaineTDDto repartitionSemaineDto) {
        this.professeurDto = professeurDto;
        this.groupeDto = groupeDto;
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

    public RepartitionSemaineTDDto getRepartitionSemaineDto() {
        return repartitionSemaineDto;
    }

    public void setRepartitionSemaineDto(RepartitionSemaineTDDto repartitionSemaineDto) {
        this.repartitionSemaineDto = repartitionSemaineDto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(professeurDto, groupeDto, repartitionSemaineDto);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TDComposanteDto tdDto = (TDComposanteDto) obj;
        return Objects.equals(professeurDto, tdDto.professeurDto)
                && Objects.equals(groupeDto, tdDto.groupeDto)
                && Objects.equals(repartitionSemaineDto, tdDto.repartitionSemaineDto);
    }
}
