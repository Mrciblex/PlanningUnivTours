package com.univtime.informatique.dto.cmDto;

import com.univtime.informatique.dto.idsDto.TDIdDto;
import com.univtime.informatique.dto.idsDto.TPIdDto;

import java.util.Objects;
import java.util.Set;

public class RepartitionSemaineCMDto {
    private Integer idRepartitionSemaine;
    private Integer numSemaine;
    private Integer qteTypeCours;

    // private Set<CMIdDto> cmIds;
    private Set<TDIdDto> tdIds;
    private Set<TPIdDto> tpIds;

    public RepartitionSemaineCMDto() {

    }

    public RepartitionSemaineCMDto(Integer idRepartitionSemaine) {
        this.idRepartitionSemaine = idRepartitionSemaine;
    }

    public RepartitionSemaineCMDto(Integer idRepartitionSemaine,
                                   Integer numSemaine,
                                   Integer qteTypeCours,
                                   Set<TDIdDto> tdIds,
                                   Set<TPIdDto> tpIds) {
        this.idRepartitionSemaine = idRepartitionSemaine;
        this.numSemaine = numSemaine;
        this.qteTypeCours = qteTypeCours;
        this.tdIds = tdIds;
        this.tpIds = tpIds;
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

    public Set<TDIdDto> getTdIds() {
        return tdIds;
    }

    public void setTdIds(Set<TDIdDto> tdIds) {
        this.tdIds = tdIds;
    }

    public Set<TPIdDto> getTpIds() {
        return tpIds;
    }

    public void setTpIds(Set<TPIdDto> tpIds) {
        this.tpIds = tpIds;
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
        RepartitionSemaineCMDto that = (RepartitionSemaineCMDto) obj;
        return Objects.equals(idRepartitionSemaine, that.idRepartitionSemaine);
    }
}
