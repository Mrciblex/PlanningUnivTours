package com.univtime.informatique.mappers;

import com.univtime.informatique.dto.jourDto.DisponibiliteJourDto;
import com.univtime.informatique.dto.jourDto.JourDto;
import com.univtime.informatique.dto.jourDto.ProfesseurJourDto;
import com.univtime.informatique.entities.DisponibiliteEntity;
import com.univtime.informatique.entities.JourEntity;
import com.univtime.informatique.entities.ProfesseurEntity;

import java.util.stream.Collectors;

public class JourMapper {

    private JourMapper() {

    }

    public static JourDto toDto(JourEntity entity) {
        if (entity == null) {
            return null;
        }
        JourDto dto = new JourDto();
        dto.setIdJour(entity.getIdJour());
        dto.setJourSemaine(entity.getJourSemaine());
        dto.setProfesseurDto(professeurToDto(entity.getProfesseur()));
        dto.setDisponibiliteDto(entity.getDisponibiliteEntities()
                .stream()
                .map(JourMapper::disponibiliteToDto)
                .collect(Collectors.toSet()));
        return dto;
    }

    public static JourEntity toEntity(JourDto dto) {
        if (dto == null) {
            return null;
        }
        JourEntity entity = new JourEntity();
        entity.setIdJour(dto.getIdJour());
        entity.setJourSemaine(dto.getJourSemaine());
        return entity;
    }

    private static ProfesseurJourDto professeurToDto(ProfesseurEntity entity) {
        ProfesseurJourDto professeur = new ProfesseurJourDto();
        if (entity !=  null) {
            professeur.setIdProf(entity.getIdProf());
            professeur.setNomProf(entity.getNomProf());
            professeur.setPrenomProf(entity.getPrenomProf());
            professeur.setIntervenantExterieur(entity.getIntervenantExterieur());
        }
        return professeur;
    }

    private static DisponibiliteJourDto disponibiliteToDto(DisponibiliteEntity entity) {
        DisponibiliteJourDto disponibilite = new DisponibiliteJourDto();
        if (entity !=  null) {
            disponibilite.
        }
        return disponibilite;
    }
}
