package com.univtime.informatique.dto.promoDto;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

public class PromoDto {
    private Integer idPromo;
    private String nomPromo;
    private Integer anneePromo;
    private Integer nbEtuPromo;
    private LocalDate debutS1Promo;
    private LocalDate finS1Promo;
    private LocalDate debutS2Promo;
    private LocalDate finS2Promo;
    private Set<PromoEstComposeePromoDto> promoEstComposeeDto;
    private Set<CMPromoDto> cmDto;
    private Set<GroupePromoDto> groupeDto;

    public PromoDto() {

    }

    public PromoDto(Integer idPromo,
                    String nomPromo,
                    Integer anneePromo,
                    Integer nbEtuPromo,
                    LocalDate debutS1Promo,
                    LocalDate finS1Promo,
                    LocalDate debutS2Promo,
                    LocalDate finS2Promo,
                    Set<PromoEstComposeePromoDto> promoEstComposeeDto,
                    Set<CMPromoDto> cmDto,
                    Set<GroupePromoDto> groupeDto) {
        this.idPromo = idPromo;
        this.nomPromo = nomPromo;
        this.anneePromo = anneePromo;
        this.nbEtuPromo = nbEtuPromo;
        this.debutS1Promo = debutS1Promo;
        this.finS1Promo = finS1Promo;
        this.debutS2Promo = debutS2Promo;
        this.finS2Promo = finS2Promo;
        this.promoEstComposeeDto = promoEstComposeeDto;
        this.cmDto = cmDto;
        this.groupeDto = groupeDto;
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

    public Set<PromoEstComposeePromoDto> getPromoEstComposeeDto() {
        return promoEstComposeeDto;
    }

    public void setPromoEstComposeeDto(Set<PromoEstComposeePromoDto> promoEstComposeeDto) {
        this.promoEstComposeeDto = promoEstComposeeDto;
    }

    public Set<CMPromoDto> getCmDto() {
        return cmDto;
    }

    public void setCmDto(Set<CMPromoDto> cmDto) {
        this.cmDto = cmDto;
    }

    public Set<GroupePromoDto> getGroupeDto() {
        return groupeDto;
    }

    public void setGroupeDto(Set<GroupePromoDto> groupeDto) {
        this.groupeDto = groupeDto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPromo);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PromoDto promoDto = (PromoDto) obj;
        return Objects.equals(idPromo, promoDto.idPromo);
    }
}
