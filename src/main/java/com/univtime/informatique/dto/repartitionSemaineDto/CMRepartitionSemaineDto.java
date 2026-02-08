package com.univtime.informatique.dto.repartitionSemaineDto;

import com.univtime.informatique.dto.idsDto.*;

import java.util.Objects;
import java.util.Set;

public class CMRepartitionSemaineDto {
    private Integer idProf;
    private String nomProf;
    private String prenomProf;
    private boolean intervenantExterieur;
    private Set<CMIdDto> cmProfIds;
    private Set<TDIdDto> tdProfIds;
    private Set<TPIdDto> tpProfIds;
    private Set<Integer> coursProfIds;
    private Set<Integer> jourProfIds;

    private Integer idPromo;
    private String nomPromo;
    private Integer anneePromo;
    private Integer nbEtuPromo;
    private Set<PromoEstComposeeIdDto> promoEstComposeePromoIds;
    private Set<CMIdDto> cmPromoIds;
    private Set<Integer> groupePromoIds;

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

    public CMRepartitionSemaineDto() {

    }

    public CMRepartitionSemaineDto(Integer idProf,
                                   String nomProf,
                                   String prenomProf,
                                   boolean intervenantExterieur,
                                   Set<CMIdDto> cmProfIds,
                                   Set<TDIdDto> tdProfIds,
                                   Set<TPIdDto> tpProfIds,
                                   Set<Integer> coursProfIds,
                                   Set<Integer> jourProfIds,
                                   Integer idPromo,
                                   String nomPromo,
                                   Integer anneePromo,
                                   Integer nbEtuPromo,
                                   Set<PromoEstComposeeIdDto> promoEstComposeePromoIds,
                                   Set<CMIdDto> cmPromoIds,
                                   Set<Integer> groupePromoIds,
                                   Integer idComposante,
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
                                   Set<BesoinSalleIdDto> besoinSalleComposanteIds) {
        this.idProf = idProf;
        this.nomProf = nomProf;
        this.prenomProf = prenomProf;
        this.intervenantExterieur = intervenantExterieur;
        this.cmProfIds = cmProfIds;
        this.tdProfIds = tdProfIds;
        this.tpProfIds = tpProfIds;
        this.coursProfIds = coursProfIds;
        this.jourProfIds = jourProfIds;
        this.idPromo = idPromo;
        this.nomPromo = nomPromo;
        this.anneePromo = anneePromo;
        this.nbEtuPromo = nbEtuPromo;
        this.promoEstComposeePromoIds = promoEstComposeePromoIds;
        this.cmPromoIds = cmPromoIds;
        this.groupePromoIds = groupePromoIds;
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
    }

    public Integer getIdProf() {
        return idProf;
    }

    public void setIdProf(Integer idProf) {
        this.idProf = idProf;
    }

    public String getNomProf() {
        return nomProf;
    }

    public void setNomProf(String nomProf) {
        this.nomProf = nomProf;
    }

    public String getPrenomProf() {
        return prenomProf;
    }

    public void setPrenomProf(String prenomProf) {
        this.prenomProf = prenomProf;
    }

    public boolean isIntervenantExterieur() {
        return intervenantExterieur;
    }

    public void setIntervenantExterieur(boolean intervenantExterieur) {
        this.intervenantExterieur = intervenantExterieur;
    }

    public Set<CMIdDto> getCmProfIds() {
        return cmProfIds;
    }

    public void setCmProfIds(Set<CMIdDto> cmProfIds) {
        this.cmProfIds = cmProfIds;
    }

    public Set<TDIdDto> getTdProfIds() {
        return tdProfIds;
    }

    public void setTdProfIds(Set<TDIdDto> tdProfIds) {
        this.tdProfIds = tdProfIds;
    }

    public Set<TPIdDto> getTpProfIds() {
        return tpProfIds;
    }

    public void setTpProfIds(Set<TPIdDto> tpProfIds) {
        this.tpProfIds = tpProfIds;
    }

    public Set<Integer> getCoursProfIds() {
        return coursProfIds;
    }

    public void setCoursProfIds(Set<Integer> coursProfIds) {
        this.coursProfIds = coursProfIds;
    }

    public Set<Integer> getJourProfIds() {
        return jourProfIds;
    }

    public void setJourProfIds(Set<Integer> jourProfIds) {
        this.jourProfIds = jourProfIds;
    }

    public Integer getIdPromo() {
        return idPromo;
    }

    public void setIdPromo(Integer idPromo) {
        this.idPromo = idPromo;
    }

    public String getNomPromo() {
        return nomPromo;
    }

    public void setNomPromo(String nomPromo) {
        this.nomPromo = nomPromo;
    }

    public Integer getAnneePromo() {
        return anneePromo;
    }

    public void setAnneePromo(Integer anneePromo) {
        this.anneePromo = anneePromo;
    }

    public Integer getNbEtuPromo() {
        return nbEtuPromo;
    }

    public void setNbEtuPromo(Integer nbEtuPromo) {
        this.nbEtuPromo = nbEtuPromo;
    }

    public Set<PromoEstComposeeIdDto> getPromoEstComposeePromoIds() {
        return promoEstComposeePromoIds;
    }

    public void setPromoEstComposeePromoIds(Set<PromoEstComposeeIdDto> promoEstComposeePromoIds) {
        this.promoEstComposeePromoIds = promoEstComposeePromoIds;
    }

    public Set<CMIdDto> getCmPromoIds() {
        return cmPromoIds;
    }

    public void setCmPromoIds(Set<CMIdDto> cmPromoIds) {
        this.cmPromoIds = cmPromoIds;
    }

    public Set<Integer> getGroupePromoIds() {
        return groupePromoIds;
    }

    public void setGroupePromoIds(Set<Integer> groupePromoIds) {
        this.groupePromoIds = groupePromoIds;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CMRepartitionSemaineDto that = (CMRepartitionSemaineDto) o;
        return Objects.equals(idProf, that.idProf)
                && Objects.equals(idPromo, that.idPromo)
                && Objects.equals(idComposante, that.idComposante);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProf, idPromo, idComposante);
    }
}
