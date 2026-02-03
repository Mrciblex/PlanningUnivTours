package com.univtime.informatique.mappers;

import com.univtime.informatique.dtos.ProfesseurDto;
import com.univtime.informatique.entities.ProfesseurEntity;

public final class ProfesseurMapper {

    private ProfesseurMapper() {
        // Private constructor pour éviter l'instantiation
    }

    public static ProfesseurDto toDTO(ProfesseurEntity entity) {
        return entity == null ? null : new ProfesseurDto()
                .setIdProf(entity.getIdProf())
                .setNomProf(entity.getNomProf())
                .setPrenomProf(entity.getPrenomProf())
                .setIntervenantExterieur(entity.isIntervenantExterieur());
    }

    public static ProfesseurEntity toEntity(ProfesseurDto dto) {
        return dto == null ? null : new ProfesseurEntity()
                .setIdProf(dto.getIdProf())
                .setNomProf(dto.getNomProf())
                .setPrenomProf(dto.getPrenomProf())
                .setIntervenantExterieur(dto.isIntervenantExterieur());
    }
}
