package com.univtime.informatique.dto.tdDto;

import com.univtime.informatique.dto.ids.CMIdDto;
import com.univtime.informatique.dto.ids.TPIdDto;

import java.util.Objects;
import java.util.Set;

public class RepartitionSemaineTDDto {
    private Integer idRepartitionSemaine;
    private Integer numSemaine;
    private Integer qteTypeCours;
    private Set<CMIdDto> cmIds;
    // private Set<TDIdDto> tdIds;
    private Set<TPIdDto> tpIds;

    public RepartitionSemaineTDDto() {

    }

    public RepartitionSemaineTDDto(Integer idRepartitionSemaine,
                                   Integer numSemaine,
                                   Integer qteTypeCours,
                                   Set<CMIdDto> cmIds,
                                   Set<TPIdDto> tpIds) {
        this.idRepartitionSemaine = idRepartitionSemaine;
        this.numSemaine = numSemaine;
        this.qteTypeCours = qteTypeCours;
        this.cmIds = cmIds;
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

    public Set<CMIdDto> getCmIds() {
        return cmIds;
    }

    public void setCmIds(Set<CMIdDto> cmIds) {
        this.cmIds = cmIds;
    }

    public Set<TPIdDto> getTpIds() {
        return tpIds;
    }

    public void setTpIds(Set<TPIdDto> tpIds) {
        this.tpIds = tpIds;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RepartitionSemaineTDDto that = (RepartitionSemaineTDDto) o;
        return Objects.equals(idRepartitionSemaine, that.idRepartitionSemaine);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idRepartitionSemaine);
    }
}
