package com.univtime.informatique.dto.promoEstComposeeDto;

import java.util.Objects;

public class ModulePromoEstComposeeDto {
    private Integer idModule;
    private String nomModule;

    public ModulePromoEstComposeeDto() {

    }

    public ModulePromoEstComposeeDto(
            Integer idModule,
            String nomModule) {
        this.idModule = idModule;
        this.nomModule = nomModule;
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
