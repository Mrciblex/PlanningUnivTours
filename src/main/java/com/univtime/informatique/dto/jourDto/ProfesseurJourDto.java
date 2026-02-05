package com.univtime.informatique.dto.jourDto;

import com.univtime.informatique.dto.professeurDto.CMProfesseurDto;
import com.univtime.informatique.dto.professeurDto.CoursProfesseurDto;
import com.univtime.informatique.dto.professeurDto.TDProfesseurDto;
import com.univtime.informatique.dto.professeurDto.TPProfesseurDto;

import java.util.Objects;
import java.util.Set;

public class ProfesseurJourDto {
    private Integer idProf;
    private String nomProf;
    private String prenomProf;
    private boolean intervenantExterieur;
    private Set<CMProfesseurDto> cmDto;
    private Set<TDProfesseurDto> tdDto;
    private Set<TPProfesseurDto> tpDto;
    private Set<CoursProfesseurDto> coursDto;

    public ProfesseurJourDto() {

    }

    public ProfesseurJourDto(Integer idProf, String nomProf, String prenomProf, boolean intervenantExterieur, Set<CMProfesseurDto> cmDto,
                         Set<TDProfesseurDto> tdDto, Set<TPProfesseurDto> tpDto, Set<CoursProfesseurDto> coursDto) {
        this.idProf = idProf;
        this.nomProf = nomProf;
        this.prenomProf = prenomProf;
        this.intervenantExterieur = intervenantExterieur;
        this.cmDto = cmDto;
        this.tdDto = tdDto;
        this.tpDto = tpDto;
        this.coursDto = coursDto;
    }

    // Getters et Setters
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

    public Set<CMProfesseurDto> getCmDto() {
        return cmDto;
    }

    public void setCmDto(Set<CMProfesseurDto> cmDto) {
        this.cmDto = cmDto;
    }

    public Set<TDProfesseurDto> getTdDto() {
        return tdDto;
    }

    public void setTdDto(Set<TDProfesseurDto> tdDto) {
        this.tdDto = tdDto;
    }

    public Set<TPProfesseurDto> getTpDto() {
        return tpDto;
    }

    public void setTpDto(Set<TPProfesseurDto> tpDto) {
        this.tpDto = tpDto;
    }

    public Set<CoursProfesseurDto> getCoursDto() {
        return coursDto;
    }

    public void setCoursDto(Set<CoursProfesseurDto> coursDto) {
        this.coursDto = coursDto;
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
        ProfesseurJourDto that = (ProfesseurJourDto) obj;
        return Objects.equals(idProf, that.idProf);
    }
}
