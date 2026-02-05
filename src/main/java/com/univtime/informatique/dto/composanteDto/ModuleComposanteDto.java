package com.univtime.informatique.dto.composanteDto;

import com.univtime.informatique.dto.moduleDto.PromoEstComposeeModuleDto;

import java.util.Objects;
import java.util.Set;

public class ModuleComposanteDto {
    private Integer idModule;
    private String nomModule;
    private Set<PromoEstComposeeModuleDto> promoEstComposeeDto;

    public ModuleComposanteDto() {

    }

    public ModuleComposanteDto(Integer idModule, String nomModule, Set<PromoEstComposeeModuleDto> promoEstComposeeDto) {
        this.idModule = idModule;
        this.nomModule = nomModule;
        this.promoEstComposeeDto = promoEstComposeeDto;
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

    @Override
    public int hashCode() {
        return Objects.hashCode(idModule);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        ModuleComposanteDto moduleDto = (ModuleComposanteDto) obj;
        return Objects.equals(idModule, moduleDto.idModule);
    }
}

