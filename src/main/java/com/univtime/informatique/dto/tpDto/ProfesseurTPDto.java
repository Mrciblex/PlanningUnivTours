package com.univtime.informatique.dto.tpDto;

import com.univtime.informatique.dto.professeurDto.*;

import java.util.Objects;
import java.util.Set;

public class ProfesseurTPDto {
    private Integer idProf;
    private String nomProf;
    private String prenomProf;
    private boolean intervenantExterieur;

    public ProfesseurTPDto() {

    }

    public ProfesseurTPDto(
            Integer idProf,
            String nomProf,
            String prenomProf,
            boolean intervenantExterieur) {
        this.idProf = idProf;
        this.nomProf = nomProf;
        this.prenomProf = prenomProf;
        this.intervenantExterieur = intervenantExterieur;
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
