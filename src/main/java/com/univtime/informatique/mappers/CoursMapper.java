package com.univtime.informatique.mappers;

import com.univtime.informatique.dto.coursDto.*;
import com.univtime.informatique.dto.ids.*;
import com.univtime.informatique.entities.*;

import java.util.stream.Collectors;

public class CoursMapper {

    private CoursMapper() {

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
        dto.setComposanteDto(composanteToDto(entity.getComposante()));
        dto.setProfesseurDto(professeurToDto(entity.getProfesseur()));
        dto.setSalleDto(salleToDto(entity.getSalle()));
        dto.setParticipeADto(entity.getParticipeAEntities()
                .stream()
                .map(CoursMapper::participeAToDto)
                .collect(Collectors.toSet()));
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
        return entity;
    }

    private static ComposanteCoursDto composanteToDto(ComposanteEntity entity) {
        ComposanteCoursDto composante = new ComposanteCoursDto();
        if (entity != null) {
            composante.setIdComposante(entity.getIdComposante());
            composante.setNomComposante(entity.getNomCoposante());
            composante.setVolumeHoraireTotal(entity.getVolumeHoraireTotal());
            composante.setVolumeHoraireCM(entity.getVolumeHoraireCM());
            composante.setVolumeHoraireTD(entity.getVolumeHoraireTD());
            composante.setVolumeHoraireTP(entity.getVolumeHoraireTP());
            composante.setBlocHoraireCM(entity.getBlocHoraireCM());
            composante.setBlocHoraireTD(entity.getBlocHoraireTD());
            composante.setBlocHoraireTP(entity.getBlocHoraireTP());
            composante.setModuleId(entity.getModule().getIdModule());
            composante.setCmIds(entity.getCmEntities()
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
            composante.setTdIds(entity.getTdEntities()
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
            composante.setTpIds(entity.getTpEntities()
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
            composante.setBesoinSalleIds(entity.getBesoinSalleEntities()
                    .stream()
                    .map(besoinSalleEntity -> {
                        return new BesoinSalleIdDto(
                                besoinSalleEntity.getIdBesoinSalle().getIdSalle(),
                                besoinSalleEntity.getIdBesoinSalle().getIdComposante()
                        );
                    })
                    .collect(Collectors.toSet()));
        }
        return composante;
    }

    private static ProfesseurCoursDto professeurToDto(ProfesseurEntity entity) {
        ProfesseurCoursDto prof = new ProfesseurCoursDto();
        if (entity != null) {
            prof.setIdProf(entity.getIdProf());
            prof.setNomProf(entity.getNomProf());
            prof.setPrenomProf(entity.getPrenomProf());
            prof.setIntervenantExterieur(entity.getIntervenantExterieur());
            prof.setCmIds(entity.getCmEntities()
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
            prof.setTdIds(entity.getTdEntities()
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
            prof.setTpIds(entity.getTpEntities()
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
            prof.setJourIds(entity.getJourEntities()
                    .stream()
                    .map(JourEntity::getIdJour)
                    .collect(Collectors.toSet()));
        }
        return prof;
    }

    private static SalleCoursDto salleToDto(SalleEntity entity) {
        SalleCoursDto salle = new SalleCoursDto();
        if (entity != null) {
            salle.setIdSalle(entity.getIdSalle());
            salle.setNbPlace(entity.getNbPlace());
            salle.setSalleMachine(entity.isSalleMachine());
            salle.setNbPC(entity.getNbPC());
            salle.setBesoinSalleIds(entity.getBesoinSalleEntities()
                    .stream()
                    .map(besoinSalleEntity -> {
                        return new BesoinSalleIdDto(
                                besoinSalleEntity.getIdBesoinSalle().getIdSalle(),
                                besoinSalleEntity.getIdBesoinSalle().getIdComposante()
                        );
                    })
                    .collect(Collectors.toSet()));
        }
        return salle;
    }

    private static ParticipeACoursDto participeAToDto(ParticipeAEntity entity) {
        ParticipeACoursDto participeA = new ParticipeACoursDto();
        if (entity != null) {
            participeA.setIdSousGroupe(entity.getSousGroupe().getIdSousGroupe());
            participeA.setNomSousGroupe(entity.getSousGroupe().getNomSousGroupe());
            participeA.setNbEtuSousGroupe(entity.getSousGroupe().getNbEtuSousGroupe());
            participeA.setGroupeSousGroupeId(entity.getSousGroupe().getIdSousGroupe());
            participeA.setTpSousGroupeIds(entity.getSousGroupe().getTpEntities()
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
            participeA.setParticipeASousGroupeIds(entity.getSousGroupe().getParticipeAEntities()
                    .stream()
                    .map(participeAEntity -> {
                        return new ParticipeAIdDto(
                                participeAEntity.getIdParticipeA().getIdSousGroupe(),
                                participeAEntity.getIdParticipeA().getIdCours()
                        );
                    })
                    .collect(Collectors.toSet()));
        }
        return participeA;
    }
}
