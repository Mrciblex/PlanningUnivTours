package com.univtime.informatique.mappers;

import com.univtime.informatique.dto.participeADto.*;
import com.univtime.informatique.entities.ParticipeAEntity;
import com.univtime.informatique.entities.SousGroupeEntity;
import com.univtime.informatique.entities.CoursEntity;
import com.univtime.informatique.dto.coursDto.CoursDto;

public final class ParticipeAMapper {

    private ParticipeAMapper() {}

    public static ParticipeADto toDto(ParticipeAEntity entity) {
        if (entity == null) return null;
        ParticipeADto dto = new ParticipeADto();
        if (entity.getSousGroupe() != null) {
            SousGroupeParticipeADto sg = new SousGroupeParticipeADto();
            sg.setIdSousGroupe(entity.getSousGroupe().getIdSousGroupe());
            sg.setNomSousGroupe(entity.getSousGroupe().getNomSousGroupe());
            dto.setSousGroupeDto(sg);
        }
        if (entity.getCours() != null) {
            CoursParticipeADto c = new CoursParticipeADto();
            c.setIdCours(entity.getCours().getIdCours());
            dto.setCoursDto(c);
        }
        return dto;
    }

    public static ParticipeAEntity toEntity(ParticipeADto dto) {
        if (dto == null) return null;
        ParticipeAEntity entity = new ParticipeAEntity();
        if (dto.getSousGroupeDto() != null) {
            SousGroupeEntity sg = new SousGroupeEntity();
            sg.setIdSousGroupe(dto.getSousGroupeDto().getIdSousGroupe());
            entity.setSousGroupe(sg);
        }
        if (dto.getCoursDto() != null) {
            CoursEntity c = new CoursEntity();
            c.setIdCours(dto.getCoursDto().getIdCours());
            entity.setCours(c);
        }
        return entity;
    }
}
