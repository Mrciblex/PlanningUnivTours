package com.univtime.informatique.dto.tpDto;

import com.univtime.informatique.dto.repartitionSemaineDto.CMRepartitionSemaineDto;
import com.univtime.informatique.dto.repartitionSemaineDto.RepartitionSemaineDto;
import com.univtime.informatique.dto.repartitionSemaineDto.TDRepartitionSemaineDto;

import java.util.Objects;
import java.util.Set;

public class RepartitionSemaineTPDto {
    private Integer idRepartitionSemaine;
    private Integer numSemaine;
    private Integer qteTypeCours;
    private Set<CMRepartitionSemaineDto> CMDto;
    private Set<TDRepartitionSemaineDto> TDDto;

    public RepartitionSemaineTPDto() {

    }

    public RepartitionSemaineTPDto(Integer idRepartitionSemaine, Integer numSemaine, Integer qteTypeCours,
                                 Set<CMRepartitionSemaineDto> CMDto, Set<TDRepartitionSemaineDto> TDDto) {
        this.idRepartitionSemaine = idRepartitionSemaine;
        this.numSemaine = numSemaine;
        this.qteTypeCours = qteTypeCours;
        this.CMDto = CMDto;
        this.TDDto = TDDto;
    }

    public Integer getIdRepartitionSemaine() {
        return idRepartitionSemaine;
    }

    public void setIdRepartitionSemaine(Integer idRepartitionSemaine) {
        this.idRepartitionSemaine = idRepartitionSemaine;
    }

    public Integer getNumSemaine() {
        return numSemaine;
    }

    public void setNumSemaine(Integer numSemaine) {
        this.numSemaine = numSemaine;
    }

    public Integer getQteTypeCours() {
        return qteTypeCours;
    }

    public void setQteTypeCours(Integer qteTypeCours) {
        this.qteTypeCours = qteTypeCours;
    }

    public Set<CMRepartitionSemaineDto> getCMDto() {
        return CMDto;
    }

    public void setCMDto(Set<CMRepartitionSemaineDto> CMDto) {
        this.CMDto = CMDto;
    }

    public Set<TDRepartitionSemaineDto> getTDDto() {
        return TDDto;
    }

    public void setTDDto(Set<TDRepartitionSemaineDto> TDDto) {
        this.TDDto = TDDto;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RepartitionSemaineTPDto that = (RepartitionSemaineTPDto) obj;
        return Objects.equals(idRepartitionSemaine, that.idRepartitionSemaine);
    }
}
