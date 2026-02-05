package com.univtime.informatique.mappers;

import com.univtime.informatique.dtos.coursDto.CoursDto;
import com.univtime.informatique.entities.CoursEntity;

public final class CoursMapper {

    private CoursMapper() {
        // Private constructor pour éviter l'instantiation
    }

    public static CoursDto toDto(CoursEntity entity) {
        return entity == null ? null : new CoursDto()
                .setIdCours(entity.getIdCours())
                .setHeureDebutCours(entity.getHeureDebutCours())
                .setHeureFinCours(entity.getHeureFinCours())
                .setTypeCoursEnum(entity.getTypeCoursEnum())
                .setIdComposante(entity.getIdComposante())
                .setIdProf(entity.getIdProf())
                .setIdSalle(entity.getIdSalle());
    }

    public static CoursEntity toEntity(CoursDto dto) {
        return dto == null ? null : new CoursEntity()
                .setIdCours(dto.getIdCours())
                .setHeureDebutCours(dto.getHeureDebutCours())
                .setHeureFinCours(dto.getHeureFinCours())
                .setTypeCoursEnum(dto.getTypeCoursEnum())
                .setIdComposante(dto.getIdComposante())
                .setIdProf(dto.getIdProf())
                .setIdSalle(dto.getIdSalle());
    }
}


