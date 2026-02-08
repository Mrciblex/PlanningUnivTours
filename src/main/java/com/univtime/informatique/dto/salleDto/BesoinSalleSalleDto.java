package com.univtime.informatique.dto.salleDto;

import com.univtime.informatique.constants.TypeBesoin;
import com.univtime.informatique.dto.idsDto.BesoinSalleIdDto;
import com.univtime.informatique.dto.idsDto.CMIdDto;
import com.univtime.informatique.dto.idsDto.TDIdDto;
import com.univtime.informatique.dto.idsDto.TPIdDto;

import java.util.Objects;
import java.util.Set;

public class BesoinSalleSalleDto {
    private Integer idComposante;
    private String nomComposante;
    private Integer volumeHoraireTotalComposante;
    private Integer volumeHoraireCMComposante;
    private Integer volumeHoraireTDComposante;
    private Integer volumeHoraireTPComposante;
    private Integer blocHoraireCMComposante;
    private Integer blocHoraireTDComposante;
    private Integer blocHoraireTPComposante;
    private Integer moduleComposanteId;
    private Set<CMIdDto> cmComposanteIds;
    private Set<TDIdDto> tdComposanteIds;
    private Set<TPIdDto> tpComposanteIds;
    private Set<Integer> coursComposanteIds;
    private Set<BesoinSalleIdDto> besoinSalleComposanteIds;

    private TypeBesoin typeBesoin;

    public BesoinSalleSalleDto() {
    }

    public BesoinSalleSalleDto(Integer idComposante,
                               String nomComposante,
                               Integer volumeHoraireTotalComposante,
                               Integer volumeHoraireCMComposante,
                               Integer volumeHoraireTDComposante,
                               Integer volumeHoraireTPComposante,
                               Integer blocHoraireCMComposante,
                               Integer blocHoraireTDComposante,
                               Integer blocHoraireTPComposante,
                               Integer moduleComposanteId,
                               Set<CMIdDto> cmComposanteIds,
                               Set<TDIdDto> tdComposanteIds,
                               Set<TPIdDto> tpComposanteIds,
                               Set<Integer> coursComposanteIds,
                               Set<BesoinSalleIdDto> besoinSalleComposanteIds,
                               TypeBesoin typeBesoin) {
        this.idComposante = idComposante;
        this.nomComposante = nomComposante;
        this.volumeHoraireTotalComposante = volumeHoraireTotalComposante;
        this.volumeHoraireCMComposante = volumeHoraireCMComposante;
        this.volumeHoraireTDComposante = volumeHoraireTDComposante;
        this.volumeHoraireTPComposante = volumeHoraireTPComposante;
        this.blocHoraireCMComposante = blocHoraireCMComposante;
        this.blocHoraireTDComposante = blocHoraireTDComposante;
        this.blocHoraireTPComposante = blocHoraireTPComposante;
        this.moduleComposanteId = moduleComposanteId;
        this.cmComposanteIds = cmComposanteIds;
        this.tdComposanteIds = tdComposanteIds;
        this.tpComposanteIds = tpComposanteIds;
        this.coursComposanteIds = coursComposanteIds;
        this.besoinSalleComposanteIds = besoinSalleComposanteIds;
        this.typeBesoin = typeBesoin;
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

    public Integer getVolumeHoraireTotalComposante() {
        return volumeHoraireTotalComposante;
    }

    public void setVolumeHoraireTotalComposante(Integer volumeHoraireTotalComposante) {
        this.volumeHoraireTotalComposante = volumeHoraireTotalComposante;
    }

    public Integer getVolumeHoraireCMComposante() {
        return volumeHoraireCMComposante;
    }

    public void setVolumeHoraireCMComposante(Integer volumeHoraireCMComposante) {
        this.volumeHoraireCMComposante = volumeHoraireCMComposante;
    }

    public Integer getVolumeHoraireTDComposante() {
        return volumeHoraireTDComposante;
    }

    public void setVolumeHoraireTDComposante(Integer volumeHoraireTDComposante) {
        this.volumeHoraireTDComposante = volumeHoraireTDComposante;
    }

    public Integer getVolumeHoraireTPComposante() {
        return volumeHoraireTPComposante;
    }

    public void setVolumeHoraireTPComposante(Integer volumeHoraireTPComposante) {
        this.volumeHoraireTPComposante = volumeHoraireTPComposante;
    }

    public Integer getBlocHoraireCMComposante() {
        return blocHoraireCMComposante;
    }

    public void setBlocHoraireCMComposante(Integer blocHoraireCMComposante) {
        this.blocHoraireCMComposante = blocHoraireCMComposante;
    }

    public Integer getBlocHoraireTDComposante() {
        return blocHoraireTDComposante;
    }

    public void setBlocHoraireTDComposante(Integer blocHoraireTDComposante) {
        this.blocHoraireTDComposante = blocHoraireTDComposante;
    }

    public Integer getBlocHoraireTPComposante() {
        return blocHoraireTPComposante;
    }

    public void setBlocHoraireTPComposante(Integer blocHoraireTPComposante) {
        this.blocHoraireTPComposante = blocHoraireTPComposante;
    }

    public Integer getModuleComposanteId() {
        return moduleComposanteId;
    }

    public void setModuleComposanteId(Integer moduleComposanteId) {
        this.moduleComposanteId = moduleComposanteId;
    }

    public Set<CMIdDto> getCmComposanteIds() {
        return cmComposanteIds;
    }

    public void setCmComposanteIds(Set<CMIdDto> cmComposanteIds) {
        this.cmComposanteIds = cmComposanteIds;
    }

    public Set<TDIdDto> getTdComposanteIds() {
        return tdComposanteIds;
    }

    public void setTdComposanteIds(Set<TDIdDto> tdComposanteIds) {
        this.tdComposanteIds = tdComposanteIds;
    }

    public Set<TPIdDto> getTpComposanteIds() {
        return tpComposanteIds;
    }

    public void setTpComposanteIds(Set<TPIdDto> tpComposanteIds) {
        this.tpComposanteIds = tpComposanteIds;
    }

    public Set<Integer> getCoursComposanteIds() {
        return coursComposanteIds;
    }

    public void setCoursComposanteIds(Set<Integer> coursComposanteIds) {
        this.coursComposanteIds = coursComposanteIds;
    }

    public Set<BesoinSalleIdDto> getBesoinSalleComposanteIds() {
        return besoinSalleComposanteIds;
    }

    public void setBesoinSalleComposanteIds(Set<BesoinSalleIdDto> besoinSalleComposanteIds) {
        this.besoinSalleComposanteIds = besoinSalleComposanteIds;
    }

    public TypeBesoin getTypeBesoin() {
        return typeBesoin;
    }

    public void setTypeBesoin(TypeBesoin typeBesoin) {
        this.typeBesoin = typeBesoin;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BesoinSalleSalleDto that = (BesoinSalleSalleDto) o;
        return Objects.equals(idComposante, that.idComposante);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idComposante);
    }
}
