package com.univtime.informatique.dto.composanteDto;

import com.univtime.informatique.dto.idsDto.CMIdDto;
import com.univtime.informatique.dto.idsDto.PromoEstComposeeIdDto;
import com.univtime.informatique.dto.idsDto.TDIdDto;
import com.univtime.informatique.dto.idsDto.TPIdDto;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

public class CMComposanteDto {
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
    private LocalDate debutS1Promo;
    private LocalDate finS1Promo;
    private LocalDate debutS2Promo;
    private LocalDate finS2Promo;
    private Set<PromoEstComposeeIdDto> promoEstComposeePromoIds;
    private Set<CMIdDto> cmPromoIds;
    private Set<Integer> groupePromoIds;

    private Integer idRepartitionSemaine;
    private Integer numSemaine;
    private Integer qteTypeCours;
    private Set<CMIdDto> cmRepartitionSemaineIds;
    private Set<TDIdDto> tdRepartitionSemaineIds;
    private Set<TPIdDto> tpRepartitionSemaineIds;


    public CMComposanteDto() {

    }

    public CMComposanteDto(Integer idProf,
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
                           LocalDate debutS1Promo,
                           LocalDate finS1Promo,
                           LocalDate debutS2Promo,
                           LocalDate finS2Promo,
                           Set<PromoEstComposeeIdDto> promoEstComposeePromoIds,
                           Set<CMIdDto> cmPromoIds,
                           Set<Integer> groupePromoIds,
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
        this.idPromo = idPromo;
        this.nomPromo = nomPromo;
        this.anneePromo = anneePromo;
        this.nbEtuPromo = nbEtuPromo;
        this.debutS1Promo = debutS1Promo;
        this.finS1Promo = finS1Promo;
        this.debutS2Promo = debutS2Promo;
        this.finS2Promo = finS2Promo;
        this.promoEstComposeePromoIds = promoEstComposeePromoIds;
        this.cmPromoIds = cmPromoIds;
        this.groupePromoIds = groupePromoIds;
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

    public LocalDate getDebutS1Promo() {
        return debutS1Promo;
    }

    public void setDebutS1Promo(LocalDate debutS1Promo) {
        this.debutS1Promo = debutS1Promo;
    }

    public LocalDate getFinS1Promo() {
        return finS1Promo;
    }

    public void setFinS1Promo(LocalDate finS1Promo) {
        this.finS1Promo = finS1Promo;
    }

    public LocalDate getDebutS2Promo() {
        return debutS2Promo;
    }

    public void setDebutS2Promo(LocalDate debutS2Promo) {
        this.debutS2Promo = debutS2Promo;
    }

    public LocalDate getFinS2Promo() {
        return finS2Promo;
    }

    public void setFinS2Promo(LocalDate finS2Promo) {
        this.finS2Promo = finS2Promo;
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
        CMComposanteDto that = (CMComposanteDto) o;
        return Objects.equals(idProf, that.idProf)
                && Objects.equals(idPromo, that.idPromo)
                && Objects.equals(idRepartitionSemaine, that.idRepartitionSemaine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProf, idPromo, idRepartitionSemaine);
    }
}
