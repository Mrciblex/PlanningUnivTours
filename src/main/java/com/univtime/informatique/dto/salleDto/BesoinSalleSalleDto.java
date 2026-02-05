package com.univtime.informatique.dto.salleDto;

import com.univtime.informatique.constants.TypeBesoin;
import com.univtime.informatique.dto.besoinSalleDto.ComposanteBesoinSalleDto;
import com.univtime.informatique.dto.besoinSalleDto.SalleBesoinSalleDto;

import java.util.Objects;

public class BesoinSalleSalleDto {
    private ComposanteBesoinSalleDto composanteDto;
    private TypeBesoin typeBesoin;

    public BesoinSalleSalleDto() {
    }

    public BesoinSalleSalleDto(TypeBesoin typeBesoin, ComposanteBesoinSalleDto composanteDto) {
        this.typeBesoin = typeBesoin;
        this.composanteDto = composanteDto;
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
        return Objects.hash(composanteDto);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        BesoinSalleSalleDto that = (BesoinSalleSalleDto) obj;
        return Objects.equals(composanteDto, that.composanteDto);
    }
}
