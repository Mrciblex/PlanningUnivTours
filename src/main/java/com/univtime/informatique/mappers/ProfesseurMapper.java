package com.univtime.informatique.mappers;

import com.univtime.informatique.dto.professeurDto.ProfesseurDto;
import com.univtime.informatique.entities.ProfesseurEntity;

public final class ProfesseurMapper {

    private ProfesseurMapper() {
        // Private constructor pour éviter l'instantiation
    }

    public static ProfesseurDto toDTO(ProfesseurEntity entity) {
        if (entity == null) return null;
        ProfesseurDto dto = new ProfesseurDto();
        dto.setIdProf(entity.getIdProf());
        dto.setNomProf(entity.getNomProf());
        dto.setPrenomProf(entity.getPrenomProf());
        dto.setIntervenantExterieur(Boolean.TRUE.equals(entity.getIntervenantExterieur()));
        return dto;
    }

    public static ProfesseurEntity toEntity(ProfesseurDto dto) {
        if (dto == null) return null;
        ProfesseurEntity entity = new ProfesseurEntity();
        entity.setIdProf(dto.getIdProf());
        entity.setNomProf(dto.getNomProf());
        entity.setPrenomProf(dto.getPrenomProf());
        entity.setIntervenantExterieur(dto.isIntervenantExterieur());
        return entity;
    }
}
