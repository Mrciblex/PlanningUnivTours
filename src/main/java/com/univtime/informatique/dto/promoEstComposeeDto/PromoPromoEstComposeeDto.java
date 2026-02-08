package com.univtime.informatique.dto.promoEstComposeeDto;

import com.univtime.informatique.dto.ids.CMIdDto;

import java.util.Objects;
import java.util.Set;

public class PromoPromoEstComposeeDto {
    private Integer idPromo;
    private String nomPromo;
    private Integer anneePromo;
    private Integer nbEtuPromo;
    // private Set<PromoEstComposeeIdDto> promoEstComposeeIds;
    private Set<CMIdDto> cmIds;
    private Set<Integer> groupeIds;

    public PromoPromoEstComposeeDto() {

    }

    public PromoPromoEstComposeeDto(Integer idPromo,
                                    String nomPromo,
                                    Integer anneePromo,
                                    Integer nbEtuPromo,
                                    Set<CMIdDto> cmIds,
                                    Set<Integer> groupeIds) {
        this.idPromo = idPromo;
        this.nomPromo = nomPromo;
        this.anneePromo = anneePromo;
        this.nbEtuPromo = nbEtuPromo;
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
