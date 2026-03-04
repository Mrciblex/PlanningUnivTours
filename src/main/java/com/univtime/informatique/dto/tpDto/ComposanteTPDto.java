package com.univtime.informatique.dto.tpDto;

import com.univtime.informatique.dto.idsDto.BesoinSalleIdDto;
import com.univtime.informatique.dto.idsDto.CMIdDto;
import com.univtime.informatique.dto.idsDto.TDIdDto;

import java.util.Objects;
import java.util.Set;

public class ComposanteTPDto {
    private Integer idComposante;
    private String nomComposante;
    private Integer volumeHoraireTotal;
    private Integer volumeHoraireCM;
    private Integer volumeHoraireTD;
    private Integer volumeHoraireTP;
    private Integer blocHoraireCM;
    private Integer blocHoraireTD;
    private Integer blocHoraireTP;

    private Integer moduleId;
    private Set<CMIdDto> cmIds;
    private Set<TDIdDto> tdIds;
    // private Set<TPIdDto> tpIds;
    private Set<Integer> coursIds;
    private Set<BesoinSalleIdDto> besoinSalleIds;

    public ComposanteTPDto(){

    }

    public ComposanteTPDto(Integer idComposante){
        this.idComposante = idComposante;
    }

    public ComposanteTPDto(Integer idComposante,
                           String nomComposante,
                           Integer volumeHoraireTotal,
                           Integer volumeHoraireCM,
                           Integer volumeHoraireTD,
                           Integer volumeHoraireTP,
                           Integer blocHoraireCM,
                           Integer blocHoraireTD,
                           Integer blocHoraireTP,
                           Integer moduleId,
                           Set<CMIdDto> cmIds,
                           Set<TDIdDto> tdIds,
                           Set<Integer> coursIds,
                           Set<BesoinSalleIdDto> besoinSalleIds) {
        this.idComposante = idComposante;
        this.nomComposante = nomComposante;
        this.volumeHoraireTotal = volumeHoraireTotal;
        this.volumeHoraireCM = volumeHoraireCM;
        this.volumeHoraireTD = volumeHoraireTD;
        this.volumeHoraireTP = volumeHoraireTP;
        this.blocHoraireCM = blocHoraireCM;
        this.blocHoraireTD = blocHoraireTD;
        this.blocHoraireTP = blocHoraireTP;
        this.moduleId = moduleId;
        this.cmIds = cmIds;
        this.tdIds = tdIds;
        this.coursIds = coursIds;
        this.besoinSalleIds = besoinSalleIds;
    }

    public Integer getIdComposante() {
        return idComposante;
    }

    public void setIdComposante(Integer idComposante) {
        this.idComposante = idComposante;
    }

    public String getNomComposante() {
        return nomComposante;
    }

    public void setNomComposante(String nomComposante) {
        this.nomComposante = nomComposante;
    }

    public Integer getVolumeHoraireTotal() {
        return volumeHoraireTotal;
    }

    public void setVolumeHoraireTotal(Integer volumeHoraireTotal) {
        this.volumeHoraireTotal = volumeHoraireTotal;
    }

    public Integer getVolumeHoraireCM() {
        return volumeHoraireCM;
    }

    public void setVolumeHoraireCM(Integer volumeHoraireCM) {
        this.volumeHoraireCM = volumeHoraireCM;
    }

    public Integer getVolumeHoraireTD() {
        return volumeHoraireTD;
    }

    public void setVolumeHoraireTD(Integer volumeHoraireTD) {
        this.volumeHoraireTD = volumeHoraireTD;
    }

    public Integer getVolumeHoraireTP() {
        return volumeHoraireTP;
    }

    public void setVolumeHoraireTP(Integer volumeHoraireTP) {
        this.volumeHoraireTP = volumeHoraireTP;
    }

    public Integer getBlocHoraireCM() {
        return blocHoraireCM;
    }

    public void setBlocHoraireCM(Integer blocHoraireCM) {
        this.blocHoraireCM = blocHoraireCM;
    }

    public Integer getBlocHoraireTD() {
        return blocHoraireTD;
    }

    public void setBlocHoraireTD(Integer blocHoraireTD) {
        this.blocHoraireTD = blocHoraireTD;
    }

    public Integer getBlocHoraireTP() {
        return blocHoraireTP;
    }

    public void setBlocHoraireTP(Integer blocHoraireTP) {
        this.blocHoraireTP = blocHoraireTP;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
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

    public Set<Integer> getCoursIds() {
        return coursIds;
    }

    public void setCoursIds(Set<Integer> coursIds) {
        this.coursIds = coursIds;
    }

    public Set<BesoinSalleIdDto> getBesoinSalleIds() {
        return besoinSalleIds;
    }

    public void setBesoinSalleIds(Set<BesoinSalleIdDto> besoinSalleIds) {
        this.besoinSalleIds = besoinSalleIds;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idComposante);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        ComposanteTPDto that = (ComposanteTPDto) obj;
        return Objects.equals(idComposante, that.idComposante);
    }
}
