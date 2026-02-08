package com.univtime.informatique.dto.tpDto;

import com.univtime.informatique.dto.ids.CMIdDto;
import com.univtime.informatique.dto.ids.TDIdDto;

import java.util.Objects;
import java.util.Set;

public class RepartitionSemaineTPDto {
    private Integer idRepartitionSemaine;
    private Integer numSemaine;
    private Integer qteTypeCours;
    private Set<CMIdDto> cmIds;
    private Set<TDIdDto> tdIds;
    // private Set<TPIdDto> tpIds;

    public RepartitionSemaineTPDto() {

    }

    public RepartitionSemaineTPDto(Integer idRepartitionSemaine,
                                   Integer numSemaine,
                                   Integer qteTypeCours,
                                   Set<CMIdDto> cmIds,
                                   Set<TDIdDto> tdIds) {
        this.idRepartitionSemaine = idRepartitionSemaine;
        this.numSemaine = numSemaine;
        this.qteTypeCours = qteTypeCours;
        this.cmIds = cmIds;
        this.tdIds = tdIds;
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

    public Set<CMIdDto> getCmIds() {
        return cmIds;
    }

    public void setCmIds(Set<CMIdDto> cmIds) {
        this.cmIds = cmIds;
    }

    public Set<TDIdDto> getTdIds() {
        return tdIds;
    }

    public void setTdIds(Set<TDIdDto> tdIds) {
        this.tdIds = tdIds;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RepartitionSemaineTPDto that = (RepartitionSemaineTPDto) o;
        return Objects.equals(idRepartitionSemaine, that.idRepartitionSemaine);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idRepartitionSemaine);
    }
}
