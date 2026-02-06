package com.univtime.informatique.mappers;

import com.univtime.informatique.dto.besoinSalleDto.*;
import com.univtime.informatique.entities.BesoinSalleEntity;
import com.univtime.informatique.entities.SalleEntity;
import com.univtime.informatique.entities.ComposanteEntity;

public final class BesoinSalleMapper {

    private BesoinSalleMapper() {}

    public static BesoinSalleDto toDto(BesoinSalleEntity entity) {
        if (entity == null) return null;
        BesoinSalleDto dto = new BesoinSalleDto();
        dto.setTypeBesoin(entity.getTypeBesoin());
        if (entity.getSalle() != null) {
            SalleBesoinSalleDto s = new SalleBesoinSalleDto();
            s.setIdSalle(entity.getSalle().getIdSalle());
            s.setNbPlace(entity.getSalle().getNbPlace());
            s.setSalleMachine(entity.getSalle().isSalleMachine());
            dto.setSalleDto(s);
        }
        if (entity.getComposante() != null) {
            ComposanteBesoinSalleDto c = new ComposanteBesoinSalleDto();
            c.setIdComposante(entity.getComposante().getIdComposante());
            c.setNomComposante(entity.getComposante().getNomCoposante());
            dto.setComposanteDto(c);
        }
        return dto;
    }

    public static BesoinSalleEntity toEntity(BesoinSalleDto dto) {
        if (dto == null) return null;
        BesoinSalleEntity entity = new BesoinSalleEntity();
        entity.setTypeBesoin(dto.getTypeBesoin());
        if (dto.getSalleDto() != null) {
            SalleEntity s = new SalleEntity();
            s.setIdSalle(dto.getSalleDto().getIdSalle());
            entity.setSalle(s);
        }
        if (dto.getComposanteDto() != null) {
            ComposanteEntity c = new ComposanteEntity();
            c.setIdComposante(dto.getComposanteDto().getIdComposante());
            entity.setComposante(c);
        }
        return entity;
    }
}
