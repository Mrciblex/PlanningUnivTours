package com.univtime.informatique.mappers;

import com.univtime.informatique.dto.idsDto.*;
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
                .map(SalleMapper::besoinSalleToDto)
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
            cours.setComposanteId(entity.getComposante().getIdComposante());
            cours.setProfesseurId(entity.getProfesseur().getIdProf());
            cours.setParticipeAIds(entity.getParticipeAEntities()
                    .stream()
                    .map(participeAEntity -> {
                        return new ParticipeAIdDto(
                                participeAEntity.getIdParticipeA().getIdSousGroupe(),
                                participeAEntity.getIdParticipeA().getIdCours()
                        );
                    })
                    .collect(Collectors.toSet()));
        }
        return cours;
    }

    private static BesoinSalleSalleDto besoinSalleToDto(BesoinSalleEntity entity) {
        BesoinSalleSalleDto besoin = new BesoinSalleSalleDto();
        if(entity != null) {
            besoin.setTypeBesoin(entity.getTypeBesoin());

            /*
                Composante
             */
            besoin.setIdComposante(entity.getComposante().getIdComposante());
            besoin.setNomComposante(entity.getComposante().getNomCoposante());
            besoin.setVolumeHoraireTotalComposante(entity.getComposante().getVolumeHoraireTotal());
            besoin.setVolumeHoraireCMComposante(entity.getComposante().getVolumeHoraireCM());
            besoin.setVolumeHoraireTDComposante(entity.getComposante().getVolumeHoraireTD());
            besoin.setVolumeHoraireTPComposante(entity.getComposante().getVolumeHoraireTP());
            besoin.setBlocHoraireCMComposante(entity.getComposante().getBlocHoraireCM());
            besoin.setBlocHoraireTDComposante(entity.getComposante().getBlocHoraireTD());
            besoin.setBlocHoraireTPComposante(entity.getComposante().getBlocHoraireTP());
            besoin.setModuleComposanteId(entity.getComposante().getModule().getIdModule());
            besoin.setCmComposanteIds(entity.getComposante().getCmEntities()
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
            besoin.setTdComposanteIds(entity.getComposante().getTdEntities()
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
            besoin.setTpComposanteIds(entity.getComposante().getTpEntities()
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
            besoin.setCoursComposanteIds(entity.getComposante().getCoursEntities()
                    .stream()
                    .map(CoursEntity::getIdCours)
                    .collect(Collectors.toSet()));
            besoin.setBesoinSalleComposanteIds(entity.getComposante().getBesoinSalleEntities()
                    .stream()
                    .map(besoinSalleEntity -> {
                        return new BesoinSalleIdDto(
                                besoinSalleEntity.getIdBesoinSalle().getIdSalle(),
                                besoinSalleEntity.getIdBesoinSalle().getIdComposante()
                        );
                    })
                    .collect(Collectors.toSet()));
        }
        return besoin;
    }
}
