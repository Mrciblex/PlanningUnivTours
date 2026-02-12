package com.univtime.informatique.dto.promoEstComposeeDto;

import com.univtime.informatique.dto.idsDto.CMIdDto;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

public class PromoPromoEstComposeeDto {
    private Integer idPromo;
    private String nomPromo;
    private Integer anneePromo;
    private Integer nbEtuPromo;
    private LocalDate debutS1Promo;
    private LocalDate finS1Promo;
    private LocalDate debutS2Promo;
    private LocalDate finS2Promo;
    // private Set<PromoEstComposeeIdDto> promoEstComposeeIds;
    private Set<CMIdDto> cmIds;
    private Set<Integer> groupeIds;

    public PromoPromoEstComposeeDto() {

    }

    public PromoPromoEstComposeeDto(Integer idPromo,
                                    String nomPromo,
                                    Integer anneePromo,
                                    Integer nbEtuPromo,
                                    LocalDate debutS1Promo,
                                    LocalDate finS1Promo,
                                    LocalDate debutS2Promo,
                                    LocalDate finS2Promo,
                                    Set<CMIdDto> cmIds,
                                    Set<Integer> groupeIds) {
        this.idPromo = idPromo;
        this.nomPromo = nomPromo;
        this.anneePromo = anneePromo;
        this.nbEtuPromo = nbEtuPromo;
        this.debutS1Promo = debutS1Promo;
        this.finS1Promo = finS1Promo;
        this.debutS2Promo = debutS2Promo;
        this.finS2Promo = finS2Promo;
        this.cmIds = cmIds;
        this.groupeIds = groupeIds;
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

    public Set<CMIdDto> getCmIds() {
        return cmIds;
    }

    public void setCmIds(Set<CMIdDto> cmIds) {
        this.cmIds = cmIds;
    }

    public Set<Integer> getGroupeIds() {
        return groupeIds;
    }

    public void setGroupeIds(Set<Integer> groupeIds) {
        this.groupeIds = groupeIds;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idPromo);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PromoPromoEstComposeeDto promoDto = (PromoPromoEstComposeeDto) obj;
        return Objects.equals(idPromo, promoDto.idPromo);
    }
}
