package com.univtime.informatique.mappers;

import com.univtime.informatique.dto.jourDto.JourDto;
import com.univtime.informatique.dto.jourDto.ProfesseurJourDto;
import com.univtime.informatique.entities.JourEntity;
import com.univtime.informatique.entities.ProfesseurEntity;

public final class JourMapper {

    private JourMapper() {}

    public static JourDto toDto(JourEntity entity) {
        if (entity == null) return null;
        JourDto dto = new JourDto();
        dto.setIdJour(entity.getIdJour());
        dto.setJourSemaine(entity.getJourSemaine());
        if (entity.getProfesseur() != null) {
            ProfesseurJourDto p = new ProfesseurJourDto();
            p.setIdProf(entity.getProfesseur().getIdProf());
            p.setNomProf(entity.getProfesseur().getNomProf());
            p.setPrenomProf(entity.getProfesseur().getPrenomProf());
            dto.setProfesseurDto(p);
        }
        return dto;
    }

    public static JourEntity toEntity(JourDto dto) {
        if (dto == null) return null;
        JourEntity e = new JourEntity();
        e.setIdJour(dto.getIdJour());
        e.setJourSemaine(dto.getJourSemaine());
        if (dto.getProfesseurDto() != null) {
            ProfesseurEntity p = new ProfesseurEntity();
            p.setIdProf(dto.getProfesseurDto().getIdProf());
            e.setProfesseur(p);
        }
        return e;
    }
}
