package com.univtime.informatique.mappers;

import com.univtime.informatique.dto.salleDto.BesoinSalleSalleDto;
import com.univtime.informatique.dto.salleDto.CoursSalleDto;
import com.univtime.informatique.dto.salleDto.SalleDto;
import com.univtime.informatique.entities.BesoinSalleEntity;
import com.univtime.informatique.entities.CoursEntity;
import com.univtime.informatique.entities.SalleEntity;

import java.util.stream.Collectors;

public class SalleMapper {

    private SalleMapper() {

    }

    public static SalleDto toDto(SalleEntity entity) {
        if (entity == null) {
            return null;
        }
        SalleDto dto = new SalleDto();
        dto.setIdSalle(entity.getIdSalle());
        dto.setNbPlace(entity.getNbPlace());
        dto.setSalleMachine(entity.isSalleMachine());
        dto.setNbPC(entity.getNbPC());
        dto.setCoursDto(entity.getCoursEntities()
                .stream()
                .map(SalleMapper::coursToDto)
                .collect(Collectors.toSet()));
        dto.setBesoinSalleDto(entity.getBesoinSalleEntities()
                .stream()
                .map(BesoinSalleMapper::besoinSalleToDto)
                .collect(Collectors.toSet()));
        return dto;
    }

    public static SalleEntity toEntity(SalleDto dto) {
        if (dto == null) {
            return null;
        }
        SalleEntity entity = new SalleEntity();
        entity.setIdSalle(dto.getIdSalle());
        entity.setNbPlace(dto.getNbPlace());
        entity.setSalleMachine(dto.isSalleMachine());
        entity.setNbPC(dto.getNbPC());
        return entity;
    }

    private static CoursSalleDto coursToDto(CoursEntity entity) {
        CoursSalleDto cours = new CoursSalleDto();
        if(entity != null) {
            cours.setIdCours(entity.getIdCours());
            cours.setHeureDebutCours(entity.getHeureDebutCours());
            cours.setHeureFinCours(entity.getHeureFinCours());
            cours.setTypeCours(entity.getTypeCours());
        }
        return cours;
    }

    private static BesoinSalleSalleDto besoinSalleToDto(BesoinSalleEntity entity) {
        BesoinSalleSalleDto besoin = new BesoinSalleSalleDto();
        if(entity != null) {
            besoin.set
        }
        return besoin;
    }
}
