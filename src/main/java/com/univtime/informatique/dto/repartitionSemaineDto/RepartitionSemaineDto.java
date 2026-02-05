package com.univtime.informatique.dto.repartitionSemaineDto;

import java.util.Objects;
import java.util.Set;

public class RepartitionSemaineDto {
    private Integer idRepartitionSemaine;
    private Integer numSemaine;
    private Integer qteTypeCours;
    private Set<CMRepartitionSemaineDto> CMDto;
    private Set<TDRepartitionSemaineDto> TDDto;
    private Set<TPRepartitionSemaineDto> TPDto;

    public RepartitionSemaineDto() {

    }

    public RepartitionSemaineDto(Integer idRepartitionSemaine, Integer numSemaine, Integer qteTypeCours,
                                 Set<CMRepartitionSemaineDto> CMDto, Set<TDRepartitionSemaineDto> TDDto, Set<TPRepartitionSemaineDto> TPDto) {
        this.idRepartitionSemaine = idRepartitionSemaine;
        this.numSemaine = numSemaine;
        this.qteTypeCours = qteTypeCours;
        this.CMDto = CMDto;
        this.TDDto = TDDto;
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

    public Set<TDRepartitionSemaineDto> getTDDto() {
        return TDDto;
    }

    public void setTDDto(Set<TDRepartitionSemaineDto> TDDto) {
        this.TDDto = TDDto;
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
        RepartitionSemaineDto that = (RepartitionSemaineDto) obj;
        return Objects.equals(idRepartitionSemaine, that.idRepartitionSemaine);
    }
}
