package com.univtime.informatique.dto.professeurDto;

import com.univtime.informatique.dto.ids.*;
import com.univtime.informatique.dto.tpDto.*;
import com.univtime.informatique.entities.ids.TPId;

import java.util.Objects;
import java.util.Set;

public class TPProfesseurDto {
    private Integer idSousGroupe;
    private String nomSousGroupe;
    private Integer nbEtuSousGroupe;
    private Integer groupeSousGroupeId;
    private Set<TPIdDto> tpSousGroupeIds;
    private Set<ParticipeAIdDto> participeASousGroupeIds;

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

    private Integer idRepartitionSemaine;
    private Integer numSemaine;
    private Integer qteTypeCours;
    private Set<CMIdDto> cmRepartitionSemaineIds;
    private Set<TDIdDto> tdRepartitionSemaineIds;
    private Set<TPIdDto> tpRepartitionSemaineIds;

    public TPProfesseurDto() {

    }

    public TPProfesseurDto(Integer idSousGroupe,
                           String nomSousGroupe,
                           Integer nbEtuSousGroupe,
                           Integer groupeSousGroupeId,
                           Set<TPIdDto> tpSousGroupeIds,
                           Set<ParticipeAIdDto> participeASousGroupeIds,
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
                           Set<BesoinSalleIdDto> besoinSalleComposanteIds,
                           Integer idRepartitionSemaine,
                           Integer numSemaine,
                           Integer qteTypeCours,
                           Set<CMIdDto> cmRepartitionSemaineIds,
                           Set<TDIdDto> tdRepartitionSemaineIds,
                           Set<TPIdDto> tpRepartitionSemaineIds) {
        this.idSousGroupe = idSousGroupe;
        this.nomSousGroupe = nomSousGroupe;
        this.nbEtuSousGroupe = nbEtuSousGroupe;
        this.groupeSousGroupeId = groupeSousGroupeId;
        this.tpSousGroupeIds = tpSousGroupeIds;
        this.participeASousGroupeIds = participeASousGroupeIds;
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
        this.idRepartitionSemaine = idRepartitionSemaine;
        this.numSemaine = numSemaine;
        this.qteTypeCours = qteTypeCours;
        this.cmRepartitionSemaineIds = cmRepartitionSemaineIds;
        this.tdRepartitionSemaineIds = tdRepartitionSemaineIds;
        this.tpRepartitionSemaineIds = tpRepartitionSemaineIds;
    }

    public Integer getIdSousGroupe() {
        return idSousGroupe;
    }

    public void setIdSousGroupe(Integer idSousGroupe) {
        this.idSousGroupe = idSousGroupe;
    }

    public String getNomSousGroupe() {
        return nomSousGroupe;
    }

    public void setNomSousGroupe(String nomSousGroupe) {
        this.nomSousGroupe = nomSousGroupe;
    }

    public Integer getNbEtuSousGroupe() {
        return nbEtuSousGroupe;
    }

    public void setNbEtuSousGroupe(Integer nbEtuSousGroupe) {
        this.nbEtuSousGroupe = nbEtuSousGroupe;
    }

    public Integer getGroupeSousGroupeId() {
        return groupeSousGroupeId;
    }

    public void setGroupeSousGroupeId(Integer groupeSousGroupeId) {
        this.groupeSousGroupeId = groupeSousGroupeId;
    }

    public Set<TPIdDto> getTpSousGroupeIds() {
        return tpSousGroupeIds;
    }

    public void setTpSousGroupeIds(Set<TPIdDto> tpSousGroupeIds) {
        this.tpSousGroupeIds = tpSousGroupeIds;
    }

    public Set<ParticipeAIdDto> getParticipeASousGroupeIds() {
        return participeASousGroupeIds;
    }

    public void setParticipeASousGroupeIds(Set<ParticipeAIdDto> participeASousGroupeIds) {
        this.participeASousGroupeIds = participeASousGroupeIds;
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

    public Set<CMIdDto> getCmRepartitionSemaineIds() {
        return cmRepartitionSemaineIds;
    }

    public void setCmRepartitionSemaineIds(Set<CMIdDto> cmRepartitionSemaineIds) {
        this.cmRepartitionSemaineIds = cmRepartitionSemaineIds;
    }

    public Set<TDIdDto> getTdRepartitionSemaineIds() {
        return tdRepartitionSemaineIds;
    }

    public void setTdRepartitionSemaineIds(Set<TDIdDto> tdRepartitionSemaineIds) {
        this.tdRepartitionSemaineIds = tdRepartitionSemaineIds;
    }

    public Set<TPIdDto> getTpRepartitionSemaineIds() {
        return tpRepartitionSemaineIds;
    }

    public void setTpRepartitionSemaineIds(Set<TPIdDto> tpRepartitionSemaineIds) {
        this.tpRepartitionSemaineIds = tpRepartitionSemaineIds;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TPProfesseurDto that = (TPProfesseurDto) o;
        return Objects.equals(idSousGroupe, that.idSousGroupe)
                && Objects.equals(idComposante, that.idComposante)
                && Objects.equals(idRepartitionSemaine, that.idRepartitionSemaine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSousGroupe, idComposante, idRepartitionSemaine);
    }
}
