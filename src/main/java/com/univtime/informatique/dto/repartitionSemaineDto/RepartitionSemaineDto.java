package com.univtime.informatique.dto.repartitionSemaineDto;

import java.util.Objects;
import java.util.Set;

public class RepartitionSemaineDto {
    private Integer idRepartitionSemaine;
    private Integer numSemaine;
    private Integer qteTypeCours;
    private Set<CMRepartitionSemaineDto> cmDto;
    private Set<TDRepartitionSemaineDto> tdDto;
    private Set<TPRepartitionSemaineDto> tpDto;

    public RepartitionSemaineDto() {

    }

    public RepartitionSemaineDto(Integer idRepartitionSemaine,
                                 Integer numSemaine,
                                 Integer qteTypeCours,
                                 Set<CMRepartitionSemaineDto> cmDto,
                                 Set<TDRepartitionSemaineDto> tdDto,
                                 Set<TPRepartitionSemaineDto> tpDto) {
        this.idRepartitionSemaine = idRepartitionSemaine;
        this.numSemaine = numSemaine;
        this.qteTypeCours = qteTypeCours;
        this.cmDto = cmDto;
        this.tdDto = tdDto;
        this.tpDto = tpDto;
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

    public Set<CMRepartitionSemaineDto> getCmDto() {
        return cmDto;
    }

    public void setCmDto(Set<CMRepartitionSemaineDto> cmDto) {
        this.cmDto = cmDto;
    }

    public Set<TDRepartitionSemaineDto> getTdDto() {
        return tdDto;
    }

    public void setTdDto(Set<TDRepartitionSemaineDto> tdDto) {
        this.tdDto = tdDto;
    }

    public Set<TPRepartitionSemaineDto> getTpDto() {
        return tpDto;
    }

    public void setTpDto(Set<TPRepartitionSemaineDto> tpDto) {
        this.tpDto = tpDto;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RepartitionSemaineDto that = (RepartitionSemaineDto) obj;
        return Objects.equals(idRepartitionSemaine, that.idRepartitionSemaine);
    }
}
