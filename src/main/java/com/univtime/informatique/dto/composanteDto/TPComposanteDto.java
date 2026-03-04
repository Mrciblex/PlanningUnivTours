package com.univtime.informatique.dto.composanteDto;

import com.univtime.informatique.dto.idsDto.CMIdDto;
import com.univtime.informatique.dto.idsDto.ParticipeAIdDto;
import com.univtime.informatique.dto.idsDto.TDIdDto;
import com.univtime.informatique.dto.idsDto.TPIdDto;

import java.util.Objects;
import java.util.Set;

public class TPComposanteDto {
    private Integer idProf;
    private String nomProf;
    private String prenomProf;
    private boolean intervenantExterieur;
    private Set<CMIdDto> cmProfIds;
    private Set<TDIdDto> tdProfIds;
    private Set<TPIdDto> tpProfIds;
    private Set<Integer> coursProfIds;
    private Set<Integer> jourProfIds;

    private Integer idSousGroupe;
    private String nomSousGroupe;
    private Integer nbEtuSousGroupe;
    private Integer groupeSousGroupeId;
    private Set<TPIdDto> tpSousGroupeIds;
    private Set<ParticipeAIdDto> participeASousGroupeIds;

    private Integer idRepartitionSemaine;
    private Integer numSemaine;
    private Integer qteTypeCours;
    private Set<CMIdDto> cmRepartitionSemaineIds;
    private Set<TDIdDto> tdRepartitionSemaineIds;
    private Set<TPIdDto> tpRepartitionSemaineIds;

    public TPComposanteDto() {

    }

    public TPComposanteDto(Integer idProf,
                           String nomProf,
                           String prenomProf,
                           boolean intervenantExterieur,
                           Set<CMIdDto> cmProfIds,
                           Set<TDIdDto> tdProfIds,
                           Set<TPIdDto> tpProfIds,
                           Set<Integer> coursProfIds,
                           Set<Integer> jourProfIds,
                           Integer idSousGroupe,
                           String nomSousGroupe,
                           Integer nbEtuSousGroupe,
                           Integer groupeSousGroupeId,
                           Set<TPIdDto> tpSousGroupeIds,
                           Set<ParticipeAIdDto> participeASousGroupeIds,
                           Integer idRepartitionSemaine,
                           Integer numSemaine,
                           Integer qteTypeCours,
                           Set<CMIdDto> cmRepartitionSemaineIds,
                           Set<TDIdDto> tdRepartitionSemaineIds,
                           Set<TPIdDto> tpRepartitionSemaineIds) {
        this.idProf = idProf;
        this.nomProf = nomProf;
        this.prenomProf = prenomProf;
        this.intervenantExterieur = intervenantExterieur;
        this.cmProfIds = cmProfIds;
        this.tdProfIds = tdProfIds;
        this.tpProfIds = tpProfIds;
        this.coursProfIds = coursProfIds;
        this.jourProfIds = jourProfIds;
        this.idSousGroupe = idSousGroupe;
        this.nomSousGroupe = nomSousGroupe;
        this.nbEtuSousGroupe = nbEtuSousGroupe;
        this.groupeSousGroupeId = groupeSousGroupeId;
        this.tpSousGroupeIds = tpSousGroupeIds;
        this.participeASousGroupeIds = participeASousGroupeIds;
        this.idRepartitionSemaine = idRepartitionSemaine;
        this.numSemaine = numSemaine;
        this.qteTypeCours = qteTypeCours;
        this.cmRepartitionSemaineIds = cmRepartitionSemaineIds;
        this.tdRepartitionSemaineIds = tdRepartitionSemaineIds;
        this.tpRepartitionSemaineIds = tpRepartitionSemaineIds;
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
        TPComposanteDto that = (TPComposanteDto) o;
        return Objects.equals(idProf, that.idProf)
                && Objects.equals(idSousGroupe, that.idSousGroupe)
                && Objects.equals(idRepartitionSemaine, that.idRepartitionSemaine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProf, idSousGroupe, idRepartitionSemaine);
    }
}
