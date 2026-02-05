package com.univtime.informatique.dto.moduleDto;

import com.univtime.informatique.dto.promoDto.CMPromoDto;
import com.univtime.informatique.dto.promoDto.GroupePromoDto;
import com.univtime.informatique.dto.promoDto.PromoDto;

import java.util.Objects;
import java.util.Set;

public class PromoEstComposeeModuleDto {
    private Integer idPromo;
    private String nomPromo;
    private Integer anneePromo;
    private Integer nbEtuPromo;
    private Set<CMPromoDto> cmDto;
    private Set<GroupePromoDto> groupeDto;

    public PromoEstComposeeModuleDto() {

    }

    public PromoEstComposeeModuleDto(Integer idPromo, String nomPromo, Integer anneePromo, Integer nbEtuPromo,
                    Set<CMPromoDto> cmDto, Set<GroupePromoDto> groupeDto) {
        this.idPromo = idPromo;
        this.nomPromo = nomPromo;
        this.anneePromo = anneePromo;
        this.nbEtuPromo = nbEtuPromo;
        this.cmDto = cmDto;
        this.groupeDto = groupeDto;
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
        return Objects.hashCode(idPromo);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PromoEstComposeeModuleDto promoDto = (PromoEstComposeeModuleDto) obj;
        return Objects.equals(idPromo, promoDto.idPromo);
    }
}
