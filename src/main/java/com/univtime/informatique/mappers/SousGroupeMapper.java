package com.univtime.informatique.mappers;

import com.univtime.informatique.dto.sousGroupeDto.SousGroupeDto;
import com.univtime.informatique.entities.SousGroupeEntity;

public final class SousGroupeMapper {

    private SousGroupeMapper() {
        // Private constructor pour éviter l'instantiation
    }

    public static SousGroupeDto toDto(SousGroupeEntity entity) {
        if (entity == null) {
            return null;
        }
        SousGroupeDto dto = new SousGroupeDto();
        dto.setIdSousGroupe(entity.getIdSousGroupe());
        dto.setNomSousGroupe(entity.getNomSousGroupe());
        dto.setNbEtuSousGroupe(entity.getNbEtuSousGroupe());
        dto.setIdSousGroupe(entity.getIdSousGroupe());
        return dto;
    }

    public static SousGroupeEntity toEntity(SousGroupeDto dto) {
        if (dto == null) {
            return null;
        }
        SousGroupeEntity entity = new SousGroupeEntity();
        entity.setIdSousGroupe(dto.getIdSousGroupe());
        entity.setNomSousGroupe(dto.getNomSousGroupe());
        entity.setNbEtuSousGroupe(dto.getNbEtuSousGroupe());
        entity.setIdSousGroupe(dto.getIdSousGroupe());
        return entity;
    }
}
