package com.univtime.informatique.mappers;

import com.univtime.informatique.dto.participeADto.*;
import com.univtime.informatique.entities.ParticipeAEntity;
import com.univtime.informatique.entities.SousGroupeEntity;
import com.univtime.informatique.entities.CoursEntity;
import com.univtime.informatique.entities.ids.ParticipeAId;

public class ParticipeAMapper {

    private ParticipeAMapper() {

    }

    public static ParticipeADto toDto(ParticipeAEntity entity) {
        if (entity == null) {
            return null;
        }
        ParticipeADto dto = new ParticipeADto();
        dto.setSousGroupeDto(sousGroupeToDto(entity.getSousGroupe()));
        dto.setCoursDto(coursToDto(entity.getCours()));

        return dto;
    }

    public static ParticipeAEntity toEntity(ParticipeADto dto) {
        if (dto == null) {
            return null;
        }
        ParticipeAEntity entity = new ParticipeAEntity();

        SousGroupeEntity sg = new SousGroupeEntity();
        if (dto.getSousGroupeDto() != null) {
            sg.setIdSousGroupe(dto.getSousGroupeDto().getIdSousGroupe());
            entity.setSousGroupe(sg);
        }

        CoursEntity cours = new CoursEntity();
        if (dto.getCoursDto() != null) {
            cours.setIdCours(dto.getCoursDto().getIdCours());
            entity.setCours(cours);
        }

        entity.setIdParticipeA(
                new ParticipeAId(
                        sg.getIdSousGroupe(),
                        cours.getIdCours()
                )
        );

        return entity;
    }

    private static SousGroupeParticipeADto sousGroupeToDto(SousGroupeEntity entity) {
        SousGroupeParticipeADto sg = new SousGroupeParticipeADto();
        if (entity != null) {
            sg.setIdSousGroupe(entity.getIdSousGroupe());
            sg.setNomSousGroupe(entity.getNomSousGroupe());
            sg.setNbEtuSousGroupe(entity.getNbEtuSousGroupe());
        }
        return sg;
    }

    private static CoursParticipeADto coursToDto(CoursEntity entity) {
        CoursParticipeADto cours = new CoursParticipeADto();
        if (entity != null) {
            cours.setIdCours(entity.getIdCours());
            cours.setHeureDebutCours(entity.getHeureDebutCours());
            cours.setHeureFinCours(entity.getHeureFinCours());
            cours.setTypeCours(entity.getTypeCours());
        }
        return cours;
    }
}
