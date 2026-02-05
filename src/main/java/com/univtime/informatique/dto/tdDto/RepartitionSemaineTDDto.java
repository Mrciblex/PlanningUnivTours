package com.univtime.informatique.dto.tdDto;

import com.univtime.informatique.dto.repartitionSemaineDto.CMRepartitionSemaineDto;
import com.univtime.informatique.dto.repartitionSemaineDto.RepartitionSemaineDto;
import com.univtime.informatique.dto.repartitionSemaineDto.TPRepartitionSemaineDto;

import java.util.Objects;
import java.util.Set;

public class RepartitionSemaineTDDto {
    private Integer idRepartitionSemaine;
    private Integer numSemaine;
    private Integer qteTypeCours;
    private Set<CMRepartitionSemaineDto> CMDto;
    private Set<TPRepartitionSemaineDto> TPDto;

    public RepartitionSemaineTDDto() {

    }

    public RepartitionSemaineTDDto(Integer idRepartitionSemaine, Integer numSemaine, Integer qteTypeCours,
                                 Set<CMRepartitionSemaineDto> CMDto, Set<TPRepartitionSemaineDto> TPDto) {
        this.idRepartitionSemaine = idRepartitionSemaine;
        this.numSemaine = numSemaine;
        this.qteTypeCours = qteTypeCours;
        this.CMDto = CMDto;
        this.TPDto = TPDto;
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

    public Set<TPRepartitionSemaineDto> getTPDto() {
        return TPDto;
    }

    public void setTPDto(Set<TPRepartitionSemaineDto> TPDto) {
        this.TPDto = TPDto;
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
        RepartitionSemaineTDDto that = (RepartitionSemaineTDDto) obj;
        return Objects.equals(idRepartitionSemaine, that.idRepartitionSemaine);
    }
}
