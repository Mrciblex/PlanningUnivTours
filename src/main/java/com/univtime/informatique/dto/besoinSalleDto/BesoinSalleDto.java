package com.univtime.informatique.dto.besoinSalleDto;

import com.univtime.informatique.constants.TypeBesoin;

import java.util.Objects;

public class BesoinSalleDto {
    private SalleBesoinSalleDto salleDto;
    private ComposanteBesoinSalleDto composanteDto;
    private TypeBesoin typeBesoin;

    public BesoinSalleDto() {
    }

    public BesoinSalleDto(TypeBesoin typeBesoin,
                          ComposanteBesoinSalleDto composanteDto,
                          SalleBesoinSalleDto salleDto) {
        this.typeBesoin = typeBesoin;
        this.composanteDto = composanteDto;
        this.salleDto = salleDto;
    }

    public SalleBesoinSalleDto getSalleDto() {
        return salleDto;
    }

    public void setSalleDto(SalleBesoinSalleDto salleDto) {
        this.salleDto = salleDto;
    }

    public ComposanteBesoinSalleDto getComposanteDto() {
        return composanteDto;
    }

    public void setComposanteDto(ComposanteBesoinSalleDto composanteDto) {
        this.composanteDto = composanteDto;
    }

    public TypeBesoin getTypeBesoin() {
        return typeBesoin;
    }

    public void setTypeBesoin(TypeBesoin typeBesoin) {
        this.typeBesoin = typeBesoin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(salleDto, composanteDto);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        BesoinSalleDto that = (BesoinSalleDto) obj;
        return Objects.equals(salleDto, that.salleDto) && Objects.equals(composanteDto, that.composanteDto);
    }
}
