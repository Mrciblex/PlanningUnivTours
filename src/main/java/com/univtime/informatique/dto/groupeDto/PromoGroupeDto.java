package com.univtime.informatique.dto.groupeDto;

import com.univtime.informatique.dto.ids.CMIdDto;
import com.univtime.informatique.dto.ids.PromoEstComposeeIdDto;

import java.util.Objects;
import java.util.Set;

public class PromoGroupeDto {
    private Integer idPromo;
    private String nomPromo;
    private Integer anneePromo;
    private Integer nbEtuPromo;

    private Set<PromoEstComposeeIdDto> promoEstComposeeIds;
    private Set<CMIdDto> cmIds;
    // private Set<Integer> groupeIds;

    public PromoGroupeDto() {

    }

    public PromoGroupeDto(Integer idPromo,
                          String nomPromo,
                          Integer anneePromo,
                          Integer nbEtuPromo,
                          Set<PromoEstComposeeIdDto> promoEstComposeeIds,
                          Set<CMIdDto> cmIds) {
        this.idPromo = idPromo;
        this.nomPromo = nomPromo;
        this.anneePromo = anneePromo;
        this.nbEtuPromo = nbEtuPromo;
        this.promoEstComposeeIds = promoEstComposeeIds;
        this.cmIds = cmIds;
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

    public Set<CMIdDto> getCmIds() {
        return cmIds;
    }

    public void setCmIds(Set<CMIdDto> cmIds) {
        this.cmIds = cmIds;
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
