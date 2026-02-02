package com.univtime.informatique.mappers;

import com.univtime.informatique.dtos.SousGroupeDto;
import com.univtime.informatique.entities.SousGroupeEntity;

public final class SousGroupeMapper {

    private SousGroupeMapper() {
        // Private constructor pour éviter l'instantiation
    }

    public static SousGroupeDto toDto(SousGroupeEntity entity) {
        return entity == null ? null : new SousGroupeDto()
                .setIdSousGroupe(entity.getIdSousGroupe())
                .setNomSousGroupe(entity.getNomSousGroupe())
                .setNbEtuSousGroupe(entity.getNbEtuSousGroupe())
                .setIdGroupe(entity.getIdGroupe());
    }

    public static SousGroupeEntity toEntity(SousGroupeDto dto) {
        return dto == null ? null : new SousGroupeEntity()
                .setIdSousGroupe(dto.getIdSousGroupe())
                .setNomSousGroupe(dto.getNomSousGroupe())
                .setNbEtuSousGroupe(dto.getNbEtuSousGroupe())
                .setIdGroupe(dto.getIdGroupe());
    }
}
