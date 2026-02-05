package com.univtime.informatique.dto.composanteDto;

import java.util.Objects;
import java.util.Set;

public class ComposanteDto {
    private Integer idComposante;
    private String nomComposante;
    private Integer volumeHoraireTotal;
    private Integer volumeHoraireCM;
    private Integer volumeHoraireTD;
    private Integer volumeHoraireTP;
    private Integer blocHoraireCM;
    private Integer blocHoraireTD;
    private Integer blocHoraireTP;
    private ModuleComposanteDto moduleDto;
    private Set<CMComposanteDto> cmDto;
    private Set<TDComposanteDto> tdDto;
    private Set<TPComposanteDto> tpDto;
    private Set<CoursComposanteDto> coursDto;
    private Set<BesoinSalleComposanteDto> besoinSalleDto;

    public ComposanteDto(){

    }

    public ComposanteDto(Integer idComposante, String nomComposante, Integer volumeHoraireTotal, Integer volumeHoraireCM, Integer volumeHoraireTD, Integer volumeHoraireTP, Integer blocHoraireCM, Integer blocHoraireTD, Integer blocHoraireTP, ModuleComposanteDto moduleDto, Set<CMComposanteDto> cmDto, Set<TDComposanteDto> tdDto, Set<TPComposanteDto> tpDto, Set<CoursComposanteDto> coursDto, Set<BesoinSalleComposanteDto> besoinSalleDto) {
        this.idComposante = idComposante;
        this.nomComposante = nomComposante;
        this.volumeHoraireTotal = volumeHoraireTotal;
        this.volumeHoraireCM = volumeHoraireCM;
        this.volumeHoraireTD = volumeHoraireTD;
        this.volumeHoraireTP = volumeHoraireTP;
        this.blocHoraireCM = blocHoraireCM;
        this.blocHoraireTD = blocHoraireTD;
        this.blocHoraireTP = blocHoraireTP;
        this.moduleDto = moduleDto;
        this.cmDto = cmDto;
        this.tdDto = tdDto;
        this.tpDto = tpDto;
        this.coursDto = coursDto;
        this.besoinSalleDto = besoinSalleDto;
    }

    // Getters et Setters
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

    public ModuleComposanteDto getModuleDto() { return moduleDto; }

    public void setModuleDto(ModuleComposanteDto moduleComposanteDto) {
        this.moduleDto = moduleComposanteDto;
    }

    public Set<CMComposanteDto> getCmDto() {
        return cmDto;
    }

    public void setCmDto(Set<CMComposanteDto> cmDto) {
        this.cmDto = cmDto;
    }

    public Set<TDComposanteDto> getTdDto() {
        return tdDto;
    }

    public void setTdDto(Set<TDComposanteDto> tdDto) {
        this.tdDto = tdDto;
    }

    public Set<TPComposanteDto> getTpDto() {
        return tpDto;
    }

    public void setTpDto(Set<TPComposanteDto> tpDto) {
        this.tpDto = tpDto;
    }

    public Set<CoursComposanteDto> getCoursDto() {
        return coursDto;
    }

    public void setCoursDto(Set<CoursComposanteDto> coursDto) {
        this.coursDto = coursDto;
    }

    public Set<BesoinSalleComposanteDto> getBesoinSalleDto() {
        return besoinSalleDto;
    }

    public void setBesoinSalleDto(Set<BesoinSalleComposanteDto> besoinSalleDto) {
        this.besoinSalleDto = besoinSalleDto;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idComposante);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        ComposanteDto that = (ComposanteDto) obj;
        return Objects.equals(idComposante, that.idComposante);
    }

}

