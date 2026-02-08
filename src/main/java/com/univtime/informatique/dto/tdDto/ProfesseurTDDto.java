package com.univtime.informatique.dto.tdDto;

import com.univtime.informatique.dto.ids.CMIdDto;
import com.univtime.informatique.dto.ids.TPIdDto;
import com.univtime.informatique.dto.professeurDto.*;

import java.util.Objects;
import java.util.Set;

public class ProfesseurTDDto {
    private Integer idProf;
    private String nomProf;
    private String prenomProf;
    private boolean intervenantExterieur;

    private Set<CMIdDto> cmIds;
    // private Set<TDIdDto> tdIds;
    private Set<TPIdDto> tpIds;
    private Set<Integer> coursIds;
    private Set<Integer> jourIds;

    public ProfesseurTDDto() {

    }

    public ProfesseurTDDto(Integer idProf,
                           String nomProf,
                           String prenomProf,
                           boolean intervenantExterieur,
                           Set<CMIdDto> cmIds,
                           Set<TPIdDto> tpIds,
                           Set<Integer> coursIds,
                           Set<Integer> jourIds) {
        this.idProf = idProf;
        this.nomProf = nomProf;
        this.prenomProf = prenomProf;
        this.intervenantExterieur = intervenantExterieur;
        this.cmIds = cmIds;
        this.tpIds = tpIds;
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

    public Set<TPIdDto> getTpIds() {
        return tpIds;
    }

    public void setTpIds(Set<TPIdDto> tpIds) {
        this.tpIds = tpIds;
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
        ProfesseurTDDto that = (ProfesseurTDDto) obj;
        return Objects.equals(idProf, that.idProf);
    }
}
