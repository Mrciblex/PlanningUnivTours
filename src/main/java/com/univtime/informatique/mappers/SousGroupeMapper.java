package com.univtime.informatique.mappers;

import com.univtime.informatique.dto.idsDto.*;
import com.univtime.informatique.dto.sousGroupeDto.GroupeSousGroupeDto;
import com.univtime.informatique.dto.sousGroupeDto.ParticipeASousGroupeDto;
import com.univtime.informatique.dto.sousGroupeDto.SousGroupeDto;
import com.univtime.informatique.dto.sousGroupeDto.TPSousGroupeDto;
import com.univtime.informatique.entities.*;

import java.util.stream.Collectors;

public class SousGroupeMapper {

    private SousGroupeMapper() {

    }

    public static SousGroupeDto toDto(SousGroupeEntity entity) {
        if (entity == null) {
            return null;
        }
        SousGroupeDto dto = new SousGroupeDto();
        dto.setIdSousGroupe(entity.getIdSousGroupe());
        dto.setNomSousGroupe(entity.getNomSousGroupe());
        dto.setNbEtuSousGroupe(entity.getNbEtuSousGroupe());
        dto.setGroupeDto(groupeToDto(entity.getGroupe()));
        dto.setTpDto(entity.getTpEntities()
                .stream()
                .map(SousGroupeMapper::tpToDto)
                .collect(Collectors.toSet()));
        dto.setParticipeADto(entity.getParticipeAEntities()
                .stream()
                .map(SousGroupeMapper::participeAToDto)
                .collect(Collectors.toSet()));
        return dto;
    }

    public static SousGroupeEntity toEntity(SousGroupeDto dto) {
        if (dto == null) {
            return null;
        }
        SousGroupeEntity entity = new SousGroupeEntity();
        entity.setIdSousGroupe(dto.getIdSousGroupe());
        entity.setNomSousGroupe(dto.getNomSousGroupe());
        entity.setNbEtuSousGroupe(dto.getNbEtuSousGroupe());
        return entity;
    }

    private static GroupeSousGroupeDto groupeToDto(GroupeEntity entity) {
        GroupeSousGroupeDto groupe = new GroupeSousGroupeDto();
        if (entity != null) {
            groupe.setIdGroupe(entity.getIdGroupe());
            groupe.setNomGroupe(entity.getNomGroupe());
            groupe.setNbEtuGroupe(entity.getNbEtuGroupe());
            groupe.setPromoId(entity.getPromo().getIdPromo());
            groupe.setTdIds(entity.getTdEntities()
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
        }
        return groupe;
    }

    private static TPSousGroupeDto tpToDto(TPEntity entity) {
        TPSousGroupeDto tp = new TPSousGroupeDto();
        if (entity != null) {
            /*
                Professeur
             */
            tp.setIdProf(entity.getProfesseur().getIdProf());
            tp.setNomProf(entity.getProfesseur().getNomProf());
            tp.setPrenomProf(entity.getProfesseur().getPrenomProf());
            tp.setIntervenantExterieur(entity.getProfesseur().getIntervenantExterieur());
            tp.setCmProfIds(entity.getProfesseur().getCmEntities()
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
            tp.setTdProfIds(entity.getProfesseur().getTdEntities()
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
            tp.setTpProfIds(entity.getProfesseur().getTpEntities()
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
            tp.setCoursProfIds(entity.getProfesseur().getCoursEntities()
                    .stream()
                    .map(CoursEntity::getIdCours)
                    .collect(Collectors.toSet()));
            tp.setJourProfIds(entity.getProfesseur().getJourEntities()
                    .stream()
                    .map(JourEntity::getIdJour)
                    .collect(Collectors.toSet()));

            /*
                Composante
             */
            tp.setIdComposante(entity.getComposante().getIdComposante());
            tp.setNomComposante(entity.getComposante().getNomComposante());
            tp.setVolumeHoraireTotalComposante(entity.getComposante().getVolumeHoraireTotal());
            tp.setVolumeHoraireCMComposante(entity.getComposante().getVolumeHoraireCM());
            tp.setVolumeHoraireTDComposante(entity.getComposante().getVolumeHoraireTD());
            tp.setVolumeHoraireTPComposante(entity.getComposante().getVolumeHoraireTP());
            tp.setBlocHoraireCMComposante(entity.getComposante().getBlocHoraireCM());
            tp.setBlocHoraireTDComposante(entity.getComposante().getBlocHoraireTD());
            tp.setBlocHoraireTPComposante(entity.getComposante().getBlocHoraireTP());
            tp.setModuleComposanteId(entity.getComposante().getModule().getIdModule());
            tp.setCmComposanteIds(entity.getComposante().getCmEntities()
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
            tp.setTdComposanteIds(entity.getComposante().getTdEntities()
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
            tp.setTpComposanteIds(entity.getComposante().getTpEntities()
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
            tp.setCoursComposanteIds(entity.getComposante().getCoursEntities()
                    .stream()
                    .map(CoursEntity::getIdCours)
                    .collect(Collectors.toSet()));
            tp.setBesoinSalleComposanteIds(entity.getComposante().getBesoinSalleEntities()
                    .stream()
                    .map(besoinSalleEntity -> {
                        return new BesoinSalleIdDto(
                                besoinSalleEntity.getIdBesoinSalle().getIdSalle(),
                                besoinSalleEntity.getIdBesoinSalle().getIdComposante()
                        );
                    })
                    .collect(Collectors.toSet()));

            /*
                Repartition Semaine
             */
            tp.setIdRepartitionSemaine(entity.getRepartitionSemaine().getIdRepartitionSemaine());
            tp.setNumSemaine(entity.getRepartitionSemaine().getNumSemaine());
            tp.setQteTypeCours(entity.getRepartitionSemaine().getQteTypeCours());
            tp.setCmRepartitionSemaineIds(entity.getRepartitionSemaine().getCmEntities()
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
            tp.setTdRepartitionSemaineIds(entity.getRepartitionSemaine().getTdEntities()
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
            tp.setTpRepartitionSemaineIds(entity.getRepartitionSemaine().getTpEntities()
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
        }
        return tp;
    }

    private static ParticipeASousGroupeDto participeAToDto(ParticipeAEntity entity) {
        ParticipeASousGroupeDto part = new ParticipeASousGroupeDto();
        if (entity != null) {
            part.setIdCours(entity.getCours().getIdCours());
            part.setHeureDebutCours(entity.getCours().getHeureDebutCours());
            part.setHeureFinCours(entity.getCours().getHeureFinCours());
            part.setTypeCours(entity.getCours().getTypeCours());
            part.setComposanteCoursId(entity.getCours().getComposante().getIdComposante());
            part.setProfesseurCoursId(entity.getCours().getProfesseur().getIdProf());

            if (entity.getCours().getSalle() != null){
                part.setSalleCoursId(entity.getCours().getSalle().getIdSalle());
            }else{
                part.setSalleCoursId(null);
            }

            part.setParticipeACoursIds(entity.getCours().getParticipeAEntities()
                    .stream()
                    .map(participeAEntity -> {
                        return new ParticipeAIdDto(
                                participeAEntity.getIdParticipeA().getIdSousGroupe(),
                                participeAEntity.getIdParticipeA().getIdCours()
                        );
                    })
                    .collect(Collectors.toSet()));
        }
        return part;
    }
}
