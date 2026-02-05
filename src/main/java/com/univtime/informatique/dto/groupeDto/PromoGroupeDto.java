package com.univtime.informatique.dto.groupeDto;

import com.univtime.informatique.dto.promoDto.CMPromoDto;
import com.univtime.informatique.dto.promoDto.PromoEstComposeePromoDto;

import java.util.Objects;
import java.util.Set;

public class PromoGroupeDto {
    private Integer idPromo;
    private String nomPromo;
    private Integer anneePromo;
    private Integer nbEtuPromo;
    private Set<PromoEstComposeePromoDto> promoEstComposeeDto;
    private Set<CMPromoDto> cmDto;

    public PromoGroupeDto() {

    }

    public PromoGroupeDto(Integer idPromo, String nomPromo, Integer anneePromo, Integer nbEtuPromo,
                    Set<PromoEstComposeePromoDto> promoEstComposeeDto, Set<CMPromoDto> cmDto) {
        this.idPromo = idPromo;
        this.nomPromo = nomPromo;
        this.anneePromo = anneePromo;
        this.nbEtuPromo = nbEtuPromo;
        this.promoEstComposeeDto = promoEstComposeeDto;
        this.cmDto = cmDto;
    }

    // Getters et Setters
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

    @Override
    public int hashCode() {
        return Objects.hashCode(idPromo);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PromoGroupeDto promoDto = (PromoGroupeDto) obj;
        return Objects.equals(idPromo, promoDto.idPromo);
    }
}
