package com.univtime.informatique.dto.promoEstComposeeDto;

import java.util.Objects;
import java.util.Set;

public class ModulePromoEstComposeeDto {
    private Integer idModule;
    private String nomModule;
    // private Set<PromoEstComposeeIdDto> promoEstComposeeIds;
    private Set<Integer> composanteIds;

    public ModulePromoEstComposeeDto() {

    }

    public ModulePromoEstComposeeDto(Integer idModule,
                                     String nomModule,
                                     Set<Integer> composanteIds) {
        this.idModule = idModule;
        this.nomModule = nomModule;
        this.composanteIds = composanteIds;
    }

    public Integer getIdModule() {
        return idModule;
    }

    public void setIdModule(Integer idModule) {
        this.idModule = idModule;
    }

    public String getNomModule() {
        return nomModule;
    }

    public void setNomModule(String nomModule) {
        this.nomModule = nomModule;
    }

    public Set<Integer> getComposanteIds() {
        return composanteIds;
    }

    public void setComposanteIds(Set<Integer> composanteIds) {
        this.composanteIds = composanteIds;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idModule);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ModulePromoEstComposeeDto moduleDto = (ModulePromoEstComposeeDto) obj;
        return Objects.equals(idModule, moduleDto.idModule);
    }
}
