package com.univtime.informatique.mappers;

import com.univtime.informatique.dtos.groupeDto.GroupeDto;
import com.univtime.informatique.entities.GroupeEntity;

public final class GroupeMapper {

    private GroupeMapper() {
        // Private constructor pour éviter l'instantiation
    }

    public static GroupeDto toDto(GroupeEntity entity) {
        return entity == null ? null : new GroupeDto()
                .setIdGroupe(entity.getIdGroupe())
                .setNomGroupe(entity.getNomGroupe())
                .setNbEtuGroupe(entity.getNbEtuGroupe())
                .setIdPromo(entity.getIdPromo());
    }

    public static GroupeEntity toEntity(GroupeDto dto) {
        return dto == null ? null : new GroupeEntity()
                .setIdGroupe(dto.getIdGroupe())
                .setNomGroupe(dto.getNomGroupe())
                .setNbEtuGroupe(dto.getNbEtuGroupe())
                .setIdPromo(dto.getIdPromo());
    }
}
