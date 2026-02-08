package com.univtime.informatique.mappers;

import com.univtime.informatique.dto.besoinSalleDto.*;
import com.univtime.informatique.entities.BesoinSalleEntity;
import com.univtime.informatique.entities.SalleEntity;
import com.univtime.informatique.entities.ComposanteEntity;

public class BesoinSalleMapper {

    private BesoinSalleMapper() {

    }

    public static BesoinSalleDto toDto(BesoinSalleEntity entity) {
        if (entity == null) return null;
        BesoinSalleDto dto = new BesoinSalleDto();
        dto.setSalleDto(salleToDto(entity.getSalle()));
        dto.setComposanteDto(composanteToDto(entity.getComposante()));
        dto.setTypeBesoin(entity.getTypeBesoin());
        return dto;
    }

    public static BesoinSalleEntity toEntity(BesoinSalleDto dto) {
        if (dto == null) {
            return null;
        }
        BesoinSalleEntity entity = new BesoinSalleEntity();
        entity.setTypeBesoin(dto.getTypeBesoin());
        return entity;
    }

    private static SalleBesoinSalleDto salleToDto(SalleEntity entity) {
        SalleBesoinSalleDto salle = new SalleBesoinSalleDto();
        if (entity != null ) {
            salle.setIdSalle(entity.getIdSalle());
            salle.setNbPlace(entity.getNbPlace());
            salle.setSalleMachine(entity.isSalleMachine());
            salle.setNbPC(entity.getNbPC());
        }
        return salle;
    }

    private static ComposanteBesoinSalleDto composanteToDto(ComposanteEntity entity) {
        ComposanteBesoinSalleDto comp = new ComposanteBesoinSalleDto();
        if (entity != null ) {
            comp.setIdComposante(entity.getIdComposante());
            comp.setNomComposante(entity.getNomCoposante());
            comp.setVolumeHoraireTotal(entity.getVolumeHoraireTotal());
            comp.setVolumeHoraireCM(entity.getVolumeHoraireCM());
            comp.setVolumeHoraireTD(entity.getVolumeHoraireTD());
            comp.setVolumeHoraireTP(entity.getVolumeHoraireTP());
            comp.setBlocHoraireCM(entity.getBlocHoraireCM());
            comp.setBlocHoraireTP(entity.getBlocHoraireTP());
            comp.setBlocHoraireTP(entity.getBlocHoraireTP());
        }
        return comp;
    }
}
