package com.univtime.informatique.dto.tpDto;

import com.univtime.informatique.dto.idsDto.CMIdDto;
import com.univtime.informatique.dto.idsDto.TDIdDto;

import java.util.Objects;
import java.util.Set;

public class ProfesseurTPDto {
    private Integer idProf;
    private String nomProf;
    private String prenomProf;
    private boolean intervenantExterieur;

    private Set<CMIdDto> cmIds;
    private Set<TDIdDto> tdIds;
    // private Set<TPIdDto> tpIds;
    private Set<Integer> coursIds;
    private Set<Integer> jourIds;

    public ProfesseurTPDto() {

    }

    public ProfesseurTPDto(Integer idProf,
                           String nomProf,
                           String prenomProf,
                           boolean intervenantExterieur,
                           Set<CMIdDto> cmIds,
                           Set<TDIdDto> tdIds,
                           Set<Integer> coursIds,
                           Set<Integer> jourIds) {
        this.idProf = idProf;
        this.nomProf = nomProf;
        this.prenomProf = prenomProf;
        this.intervenantExterieur = intervenantExterieur;
        this.cmIds = cmIds;
        this.tdIds = tdIds;
        this.coursIds = coursIds;
        this.jourIds = jourIds;
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

    public Set<Integer> getJourIds() {
        return jourIds;
    }

    public void setJourIds(Set<Integer> jourIds) {
        this.jourIds = jourIds;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idProf);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass())  {
            return false;
        }
        ProfesseurTPDto that = (ProfesseurTPDto) obj;
        return Objects.equals(idProf, that.idProf);
    }
}
