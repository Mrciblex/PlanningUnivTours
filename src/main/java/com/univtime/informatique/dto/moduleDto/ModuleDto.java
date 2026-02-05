package com.univtime.informatique.dto.moduleDto;

import java.util.Objects;
import java.util.Set;

public class ModuleDto {
    private Integer idModule;
    private String nomModule;
    private Set<PromoEstComposeeModuleDto> promoEstComposeeDto;
    private Set<ComposanteModuleDto> ComposanteDto;

    public ModuleDto() {

    }

    public ModuleDto(Integer idModule, String nomModule, Set<PromoEstComposeeModuleDto> promoEstComposeeDto, Set<ComposanteModuleDto> composanteDto) {
        this.idModule = idModule;
        this.nomModule = nomModule;
        this.promoEstComposeeDto = promoEstComposeeDto;
        ComposanteDto = composanteDto;
    }

    // Getters et Setters
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

    public Set<PromoEstComposeeModuleDto> getPromoEstComposeeDto() {
        return promoEstComposeeDto;
    }

    public void setPromoEstComposeeDto(Set<PromoEstComposeeModuleDto> promoEstComposeeDto) {
        this.promoEstComposeeDto = promoEstComposeeDto;
    }

    public Set<ComposanteModuleDto> getComposanteDto() {
        return ComposanteDto;
    }

    public void setComposanteDto(Set<ComposanteModuleDto> composanteDto) {
        ComposanteDto = composanteDto;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idModule);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        ModuleDto moduleDto = (ModuleDto) obj;
        return Objects.equals(idModule, moduleDto.idModule);
    }
}

