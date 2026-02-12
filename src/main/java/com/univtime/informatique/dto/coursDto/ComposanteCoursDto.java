package com.univtime.informatique.dto.coursDto;

import com.univtime.informatique.constants.TypeCours;
import com.univtime.informatique.dto.idsDto.BesoinSalleIdDto;
import com.univtime.informatique.dto.idsDto.CMIdDto;
import com.univtime.informatique.dto.idsDto.TDIdDto;
import com.univtime.informatique.dto.idsDto.TPIdDto;

import java.util.Objects;
import java.util.Set;

public class ComposanteCoursDto {
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
    private Set<TPIdDto> tpIds;
    private Set<BesoinSalleIdDto> besoinSalleIds;
    // private Set<Integer> coursIds;

    public ComposanteCoursDto(){

    }

    public ComposanteCoursDto(Integer idComposante,
                              Integer blocHoraireCM,
                              Integer blocHoraireTD,
                              Integer blocHoraireTP) {
        this.idComposante = idComposante;
        this.blocHoraireCM = blocHoraireCM;
        this.blocHoraireTD = blocHoraireTD;
        this.blocHoraireTP = blocHoraireTP;
    }

    public ComposanteCoursDto(Integer idComposante,
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
                              Set<TPIdDto> tpIds,
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
        this.tpIds = tpIds;
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

    public Set<TPIdDto> getTpIds() {
        return tpIds;
    }

    public void setTpIds(Set<TPIdDto> tpIds) {
        this.tpIds = tpIds;
    }

    public Set<BesoinSalleIdDto> getBesoinSalleIds() {
        return besoinSalleIds;
    }

    public void setBesoinSalleIds(Set<BesoinSalleIdDto> besoinSalleIds) {
        this.besoinSalleIds = besoinSalleIds;
    }

    public Integer getBlocHoraire(TypeCours typeCours){
        return switch (typeCours) {
            case CM -> blocHoraireCM;
            case TD -> blocHoraireTD;
            case TP -> blocHoraireTP;
            default -> 0;
        };
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idComposante);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        ComposanteCoursDto that = (ComposanteCoursDto) obj;
        return Objects.equals(idComposante, that.idComposante);
    }

}

