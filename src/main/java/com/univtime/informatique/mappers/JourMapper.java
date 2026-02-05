package com.univtime.informatique.mappers;

import com.univtime.informatique.dtos.jourDto.JourDto;
import com.univtime.informatique.entities.JourEntity;

public class JourMapper {
    public static JourDto toDto(Jour jour) {
        JourDto dto = new JourDto();
        dto.setIdJour(jour.getIdJour());
        dto.setJourSemaine(jour.getJourSemaine());
        dto.setIdProf(jour.getIdProf());
        return dto;
    }

    public static JourEntity toEntity(JourDto dto) {
        JourEntity jour = new Entity();
        jour.setIdJour(dto.getIdJour());
        jour.setJourSemaine(dto.getJourSemaine());
        jour.setIdProf(dto.getIdProf());
        return jour;
    }
}
