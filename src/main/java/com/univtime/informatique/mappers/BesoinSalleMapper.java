package com.univtime.informatique.mappers;

import com.univtime.informatique.dto.besoinSalleDto.*;
import com.univtime.informatique.dto.ids.CMIdDto;
import com.univtime.informatique.dto.ids.TDIdDto;
import com.univtime.informatique.dto.ids.TPIdDto;
import com.univtime.informatique.entities.BesoinSalleEntity;
import com.univtime.informatique.entities.CoursEntity;
import com.univtime.informatique.entities.SalleEntity;
import com.univtime.informatique.entities.ComposanteEntity;

import java.util.stream.Collectors;

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
            salle.setCoursIds(entity.getCoursEntities()
                    .stream()
                    .map(CoursEntity::getIdCours)
                    .collect(Collectors.toSet()));
        }
        return salle;
    }

    private static ComposanteBesoinSalleDto composanteToDto(ComposanteEntity entity) {
        ComposanteBesoinSalleDto comp = new ComposanteBesoinSalleDto();
        if (entity != null ) {
            /*
                Composante
             */
            comp.setIdComposante(entity.getIdComposante());
            comp.setNomComposante(entity.getNomCoposante());
            comp.setVolumeHoraireTotal(entity.getVolumeHoraireTotal());
            comp.setVolumeHoraireCM(entity.getVolumeHoraireCM());
            comp.setVolumeHoraireTD(entity.getVolumeHoraireTD());
            comp.setVolumeHoraireTP(entity.getVolumeHoraireTP());
            comp.setBlocHoraireCM(entity.getBlocHoraireCM());
            comp.setBlocHoraireTP(entity.getBlocHoraireTP());
            comp.setBlocHoraireTP(entity.getBlocHoraireTP());
            comp.setModuleId(entity.getModule().getIdModule());
            comp.setCmIds(entity.getCmEntities()
                    .stream()
                    .map(cmEntity -> {
                        return new CMIdDto(
                                cmEntity.getIdCM().getIdProf(),
                                cmEntity.getIdCM().getIdPromo(),
                                cmEntity.getIdCM().getIdComposante(),
                                cmEntity.getIdCM().getIdRepartitionSemaine()
                        );
                    })
                    .collect(Collectors.toSet()));
            comp.setTdIds(entity.getTdEntities()
                    .stream()
                    .map(tdEntity -> {
                        return new TDIdDto(
                                tdEntity.getIdTD().getIdProf(),
                                tdEntity.getIdTD().getIdGroupe(),
                                tdEntity.getIdTD().getIdComposante(),
                                tdEntity.getIdTD().getIdRepartitionSemaine()
                        );
                    })
                    .collect(Collectors.toSet()));
            comp.setTpIds(entity.getTpEntities()
                    .stream()
                    .map(tpEntity -> {
                        return new TPIdDto(
                                tpEntity.getIdTP().getIdProf(),
                                tpEntity.getIdTP().getIdSousGroupe(),
                                tpEntity.getIdTP().getIdComposante(),
                                tpEntity.getIdTP().getIdRepartitionSemaine()
                        );
                    })
                    .collect(Collectors.toSet()));
            comp.setCoursIds(entity.getCoursEntities()
                    .stream()
                    .map(CoursEntity::getIdCours)
                    .collect(Collectors.toSet()));
        }
        return comp;
    }
}
