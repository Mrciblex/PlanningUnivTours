package com.univtime.informatique.dto.cmDto;

import com.univtime.informatique.dto.ids.PromoEstComposeeIdDto;

import java.util.Objects;
import java.util.Set;

public class PromoCMDto {
    private Integer idPromo;
    private String nomPromo;
    private Integer anneePromo;
    private Integer nbEtuPromo;

    private Set<PromoEstComposeeIdDto> promoEstComposeeIds;
    // private Set<CMIdDto> cmIds;
    private Set<Integer> groupeIds;

    public PromoCMDto() {

    }

    public PromoCMDto(Integer idPromo,
                      String nomPromo,
                      Integer anneePromo,
                      Integer nbEtuPromo,
                      Set<PromoEstComposeeIdDto> promoEstComposeeIds,
                      Set<Integer> groupeIds) {
        this.idPromo = idPromo;
        this.nomPromo = nomPromo;
        this.anneePromo = anneePromo;
        this.nbEtuPromo = nbEtuPromo;
        this.promoEstComposeeIds = promoEstComposeeIds;
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

    public Set<PromoEstComposeeIdDto> getPromoEstComposeeIds() {
        return promoEstComposeeIds;
    }

    public void setPromoEstComposeeIds(Set<PromoEstComposeeIdDto> promoEstComposeeIds) {
        this.promoEstComposeeIds = promoEstComposeeIds;
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
        PromoCMDto promoDto = (PromoCMDto) obj;
        return Objects.equals(idPromo, promoDto.idPromo);
    }
}
