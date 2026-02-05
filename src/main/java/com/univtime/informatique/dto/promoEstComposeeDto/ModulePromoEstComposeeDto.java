package com.univtime.informatique.dto.promoEstComposeeDto;

import com.univtime.informatique.dto.moduleDto.ComposanteModuleDto;

import java.util.Objects;
import java.util.Set;

public class ModulePromoEstComposeeDto {
    private Integer idModule;
    private String nomModule;
    private Set<ComposanteModuleDto> ComposanteDto;

    public ModulePromoEstComposeeDto() {

    }

    public ModulePromoEstComposeeDto(Integer idModule, String nomModule, Set<ComposanteModuleDto> composanteDto) {
        this.idModule = idModule;
        this.nomModule = nomModule;
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
        ModulePromoEstComposeeDto moduleDto = (ModulePromoEstComposeeDto) obj;
        return Objects.equals(idModule, moduleDto.idModule);
    }
}
