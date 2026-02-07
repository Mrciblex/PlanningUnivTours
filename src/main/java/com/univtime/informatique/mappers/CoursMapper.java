package com.univtime.informatique.mappers;

import com.univtime.informatique.dto.coursDto.CoursDto;
import com.univtime.informatique.dto.coursDto.ComposanteCoursDto;
import com.univtime.informatique.dto.coursDto.ProfesseurCoursDto;
import com.univtime.informatique.dto.coursDto.SalleCoursDto;
import com.univtime.informatique.entities.CoursEntity;
import com.univtime.informatique.entities.ComposanteEntity;
import com.univtime.informatique.entities.ProfesseurEntity;
import com.univtime.informatique.entities.SalleEntity;

public final class CoursMapper {

    private CoursMapper() {
        // Private constructor pour éviter l'instantiation
    }

    public static CoursDto toDto(CoursEntity entity) {
        if (entity == null) {
            return null;
        }
        CoursDto dto = new CoursDto();
        dto.setIdCours(entity.getIdCours());
        dto.setHeureDebutCours(entity.getHeureDebutCours());
        dto.setHeureFinCours(entity.getHeureFinCours());
        dto.setTypeCoursEnum(entity.getTypeCours());
        dto.setComposanteDto(mapComposanteToCoursDto(entity.getComposante()));
        dto.setProfesseurDto(mapProfesseurToCoursDto(entity.getProfesseur()));
        dto.setSalleDto(mapSalleToCoursDto(entity.getSalle()));
        return dto;
    }

    public static CoursEntity toEntity(CoursDto dto) {
        if (dto == null) {
            return null;
        }
        CoursEntity entity = new CoursEntity();
        entity.setIdCours(dto.getIdCours());
        entity.setHeureDebutCours(dto.getHeureDebutCours());
        entity.setHeureFinCours(dto.getHeureFinCours());
        entity.setTypeCours(dto.getTypeCoursEnum());
        entity.setComposante(mapComposanteFromCoursDto(dto.getComposanteDto()));
        entity.setProfesseur(mapProfesseurFromCoursDto(dto.getProfesseurDto()));
        entity.setSalle(mapSalleFromCoursDto(dto.getSalleDto()));
        return entity;
    }

    // Helpers pour mapper les objets liés vers les DTO spécifiques au contexte "Cours"
    private static ComposanteCoursDto mapComposanteToCoursDto(ComposanteEntity e) {
        if (e == null) return null;
        ComposanteCoursDto dto = new ComposanteCoursDto();
        dto.setIdComposante(e.getIdComposante());
        // Note: le nom dans l'entité a un petit typo "nomCoposante"
        dto.setNomComposante(e.getNomCoposante());
        return dto;
    }

    private static ProfesseurCoursDto mapProfesseurToCoursDto(ProfesseurEntity e) {
        if (e == null) return null;
        ProfesseurCoursDto dto = new ProfesseurCoursDto();
        dto.setIdProf(e.getIdProf());
        dto.setNomProf(e.getNomProf());
        dto.setPrenomProf(e.getPrenomProf());
        dto.setIntervenantExterieur(Boolean.TRUE.equals(e.getIntervenantExterieur()));
        return dto;
    }

    private static SalleCoursDto mapSalleToCoursDto(SalleEntity e) {
        if (e == null) return null;
        SalleCoursDto dto = new SalleCoursDto();
        dto.setIdSalle(e.getIdSalle());
        dto.setNbPlace(e.getNbPlace());
        dto.setSalleMachine(e.isSalleMachine());
        dto.setNbPC(e.getNbPC());
        return dto;
    }

    // Helpers inverses : du DTO cours vers une entité partielle (seulement l'id) — utile pour setRelation
    private static ComposanteEntity mapComposanteFromCoursDto(ComposanteCoursDto dto) {
        if (dto == null) return null;
        ComposanteEntity e = new ComposanteEntity();
        e.setIdComposante(dto.getIdComposante());
        return e;
    }

    private static ProfesseurEntity mapProfesseurFromCoursDto(ProfesseurCoursDto dto) {
        if (dto == null) return null;
        ProfesseurEntity e = new ProfesseurEntity();
        e.setIdProf(dto.getIdProf());
        return e;
    }

    private static SalleEntity mapSalleFromCoursDto(SalleCoursDto dto) {
        if (dto == null) return null;
        SalleEntity e = new SalleEntity();
        e.setIdSalle(dto.getIdSalle());
        return e;
    }
}
