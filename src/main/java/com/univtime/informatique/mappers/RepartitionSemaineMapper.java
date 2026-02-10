package com.univtime.informatique.mappers;

import com.univtime.informatique.dto.idsDto.*;
import com.univtime.informatique.dto.repartitionSemaineDto.CMRepartitionSemaineDto;
import com.univtime.informatique.dto.repartitionSemaineDto.RepartitionSemaineDto;
import com.univtime.informatique.dto.repartitionSemaineDto.TDRepartitionSemaineDto;
import com.univtime.informatique.dto.repartitionSemaineDto.TPRepartitionSemaineDto;
import com.univtime.informatique.entities.*;

import java.util.stream.Collectors;

public class RepartitionSemaineMapper {

    private RepartitionSemaineMapper() {

    }

    public static RepartitionSemaineDto toDto(RepartitionSemaineEntity entity) {
        if (entity == null) {
            return null;
        }
        RepartitionSemaineDto dto = new RepartitionSemaineDto();
        dto.setIdRepartitionSemaine(entity.getIdRepartitionSemaine());
        dto.setNumSemaine(entity.getNumSemaine());
        dto.setQteTypeCours(entity.getQteTypeCours());
        dto.setCmDto(entity.getCmEntities()
                .stream()
                .map(RepartitionSemaineMapper::cmToDto)
                .collect(Collectors.toSet()));
        dto.setTdDto(entity.getTdEntities()
                .stream()
                .map(RepartitionSemaineMapper::tdToDto)
                .collect(Collectors.toSet()));
        dto.setTpDto(entity.getTpEntities()
                .stream()
                .map(RepartitionSemaineMapper::tpToDto)
                .collect(Collectors.toSet()));
        return dto;
    }

    public static RepartitionSemaineEntity toEntity(RepartitionSemaineDto dto) {
        if (dto == null) {
            return null;
        }
        RepartitionSemaineEntity entity = new RepartitionSemaineEntity();
        entity.setIdRepartitionSemaine(dto.getIdRepartitionSemaine());
        entity.setNumSemaine(dto.getNumSemaine());
        entity.setQteTypeCours(dto.getQteTypeCours());
        return entity;
    }

    private static CMRepartitionSemaineDto cmToDto(CMEntity entity) {
        CMRepartitionSemaineDto cm = new CMRepartitionSemaineDto();
        if (entity != null) {
            /*
                Professeur
             */
            cm.setIdProf(entity.getProfesseur().getIdProf());
            cm.setNomProf(entity.getProfesseur().getNomProf());
            cm.setPrenomProf(entity.getProfesseur().getPrenomProf());
            cm.setIntervenantExterieur(entity.getProfesseur().getIntervenantExterieur());
            cm.setCmProfIds(entity.getProfesseur().getCmEntities()
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
            cm.setTdProfIds(entity.getProfesseur().getTdEntities()
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
            cm.setTpProfIds(entity.getProfesseur().getTpEntities()
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
            cm.setCoursProfIds(entity.getProfesseur().getCoursEntities()
                    .stream()
                    .map(CoursEntity::getIdCours)
                    .collect(Collectors.toSet()));
            cm.setJourProfIds(entity.getProfesseur().getJourEntities()
                    .stream()
                    .map(JourEntity::getIdJour)
                    .collect(Collectors.toSet()));

            /*
                Promo
             */
            /*
                Promo
             */
            cm.setIdPromo(entity.getPromo().getIdPromo());
            cm.setNomPromo(entity.getPromo().getNomPromo());
            cm.setAnneePromo(entity.getPromo().getAnneePromo());
            cm.setNbEtuPromo(entity.getPromo().getNbEtuPromo());
            cm.setPromoEstComposeePromoIds(entity.getPromo().getPromoEstComposeeEntities()
                    .stream()
                    .map(promoEstComposeeEntity -> {
                        return new PromoEstComposeeIdDto(
                                promoEstComposeeEntity.getIdPromoEstComposee().getIdPromo(),
                                promoEstComposeeEntity.getIdPromoEstComposee().getIdModule()
                        );
                    })
                    .collect(Collectors.toSet()));
            cm.setCmPromoIds(entity.getPromo().getCmEntities()
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
            cm.setGroupePromoIds(entity.getPromo().getGroupeEntities()
                    .stream()
                    .map(GroupeEntity::getIdGroupe)
                    .collect(Collectors.toSet()));

            /*
                Composante
             */
            cm.setIdComposante(entity.getComposante().getIdComposante());
            cm.setNomComposante(entity.getComposante().getNomCoposante());
            cm.setVolumeHoraireTotalComposante(entity.getComposante().getVolumeHoraireTotal());
            cm.setVolumeHoraireCMComposante(entity.getComposante().getVolumeHoraireCM());
            cm.setVolumeHoraireTDComposante(entity.getComposante().getVolumeHoraireTD());
            cm.setVolumeHoraireTPComposante(entity.getComposante().getVolumeHoraireTP());
            cm.setBlocHoraireCMComposante(entity.getComposante().getBlocHoraireCM());
            cm.setBlocHoraireTDComposante(entity.getComposante().getBlocHoraireTD());
            cm.setBlocHoraireTPComposante(entity.getComposante().getBlocHoraireTP());
            cm.setModuleComposanteId(entity.getComposante().getModule().getIdModule());
            cm.setCmComposanteIds(entity.getComposante().getCmEntities()
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
            cm.setTdComposanteIds(entity.getComposante().getTdEntities()
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
            cm.setTpComposanteIds(entity.getComposante().getTpEntities()
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
            cm.setCoursComposanteIds(entity.getComposante().getCoursEntities()
                    .stream()
                    .map(CoursEntity::getIdCours)
                    .collect(Collectors.toSet()));
            cm.setBesoinSalleComposanteIds(entity.getComposante().getBesoinSalleEntities()
                    .stream()
                    .map(besoinSalleEntity -> {
                        return new BesoinSalleIdDto(
                                besoinSalleEntity.getIdBesoinSalle().getIdSalle(),
                                besoinSalleEntity.getIdBesoinSalle().getIdComposante()
                        );
                    })
                    .collect(Collectors.toSet()));
        }
        return cm;
    }

    private static TDRepartitionSemaineDto tdToDto(TDEntity entity) {
        TDRepartitionSemaineDto td = new TDRepartitionSemaineDto();
        if (entity != null) {
            /*
                Professeur
             */
            td.setIdProf(entity.getProfesseur().getIdProf());
            td.setNomProf(entity.getProfesseur().getNomProf());
            td.setPrenomProf(entity.getProfesseur().getPrenomProf());
            td.setIntervenantExterieur(entity.getProfesseur().getIntervenantExterieur());
            td.setCmProfIds(entity.getProfesseur().getCmEntities()
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
            td.setTdProfIds(entity.getProfesseur().getTdEntities()
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
            td.setTpProfIds(entity.getProfesseur().getTpEntities()
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
            td.setCoursProfIds(entity.getProfesseur().getCoursEntities()
                    .stream()
                    .map(CoursEntity::getIdCours)
                    .collect(Collectors.toSet()));
            td.setJourProfIds(entity.getProfesseur().getJourEntities()
                    .stream()
                    .map(JourEntity::getIdJour)
                    .collect(Collectors.toSet()));

            /*
                Groupe
             */
            td.setIdGroupe(entity.getGroupe().getIdGroupe());
            td.setNomGroupe(entity.getGroupe().getNomGroupe());
            td.setNbEtuGroupe(entity.getGroupe().getNbEtuGroupe());
            td.setPromoGroupeId(entity.getGroupe().getPromo().getIdPromo());
            td.setTdGroupeIds(entity.getGroupe().getTdEntities()
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
            td.setSousGroupeGroupeIds(entity.getGroupe().getSousGroupeEntities()
                    .stream()
                    .map(SousGroupeEntity::getIdSousGroupe)
                    .collect(Collectors.toSet()));

            /*
                Composante
             */
            td.setIdComposante(entity.getComposante().getIdComposante());
            td.setNomComposante(entity.getComposante().getNomCoposante());
            td.setVolumeHoraireTotalComposante(entity.getComposante().getVolumeHoraireTotal());
            td.setVolumeHoraireCMComposante(entity.getComposante().getVolumeHoraireCM());
            td.setVolumeHoraireTDComposante(entity.getComposante().getVolumeHoraireTD());
            td.setVolumeHoraireTPComposante(entity.getComposante().getVolumeHoraireTP());
            td.setBlocHoraireCMComposante(entity.getComposante().getBlocHoraireCM());
            td.setBlocHoraireTDComposante(entity.getComposante().getBlocHoraireTD());
            td.setBlocHoraireTPComposante(entity.getComposante().getBlocHoraireTP());
            td.setModuleComposanteId(entity.getComposante().getModule().getIdModule());
            td.setCmComposanteIds(entity.getComposante().getCmEntities()
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
            td.setTdComposanteIds(entity.getComposante().getTdEntities()
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
            td.setTpComposanteIds(entity.getComposante().getTpEntities()
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
            td.setCoursComposanteIds(entity.getComposante().getCoursEntities()
                    .stream()
                    .map(CoursEntity::getIdCours)
                    .collect(Collectors.toSet()));
            td.setBesoinSalleComposanteIds(entity.getComposante().getBesoinSalleEntities()
                    .stream()
                    .map(besoinSalleEntity -> {
                        return new BesoinSalleIdDto(
                                besoinSalleEntity.getIdBesoinSalle().getIdSalle(),
                                besoinSalleEntity.getIdBesoinSalle().getIdComposante()
                        );
                    })
                    .collect(Collectors.toSet()));
        }
        return td;
    }

    private static TPRepartitionSemaineDto tpToDto(TPEntity entity) {
        TPRepartitionSemaineDto tp = new TPRepartitionSemaineDto();
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
                Sous Groupe
             */
            tp.setIdSousGroupe(entity.getSousGroupe().getIdSousGroupe());
            tp.setNomSousGroupe(entity.getSousGroupe().getNomSousGroupe());
            tp.setNbEtuSousGroupe(entity.getSousGroupe().getNbEtuSousGroupe());
            tp.setGroupeSousGroupeId(entity.getSousGroupe().getGroupe().getIdGroupe());
            tp.setTpSousGroupeIds(entity.getSousGroupe().getTpEntities()
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
            tp.setParticipeASousGroupeIds(entity.getSousGroupe().getParticipeAEntities()
                    .stream()
                    .map(participeAEntity -> {
                        return new ParticipeAIdDto(
                                participeAEntity.getIdParticipeA().getIdSousGroupe(),
                                participeAEntity.getIdParticipeA().getIdCours()
                        );
                    })
                    .collect(Collectors.toSet()));

            /*
                Composante
             */
            tp.setIdComposante(entity.getComposante().getIdComposante());
            tp.setNomComposante(entity.getComposante().getNomCoposante());
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
        }
        return tp;
    }
}
