package com.univtime.informatique.mappers;

import com.univtime.informatique.dto.groupeDto.GroupeDto;
import com.univtime.informatique.dto.groupeDto.PromoGroupeDto;
import com.univtime.informatique.dto.groupeDto.SousGroupeGroupeDto;
import com.univtime.informatique.dto.groupeDto.TDGroupeDto;
import com.univtime.informatique.dto.idsDto.*;
import com.univtime.informatique.entities.*;

import java.util.stream.Collectors;

public class GroupeMapper {

    private GroupeMapper() {

    }

    public static GroupeDto toDto(GroupeEntity entity) {
        if (entity == null) {
            return null;
        }
        GroupeDto dto = new GroupeDto();
        dto.setIdGroupe(entity.getIdGroupe());
        dto.setNomGroupe(entity.getNomGroupe());
        dto.setNbEtuGroupe(entity.getNbEtuGroupe());
        dto.setPromoDto(promoToDto(entity.getPromo()));
        dto.setTdDto(entity.getTdEntities()
                .stream()
                .map(GroupeMapper::tdToDto)
                .collect(Collectors.toSet()));
        dto.setSousGroupeDto(entity.getSousGroupeEntities()
                .stream()
                .map(GroupeMapper::sousGroupeToDto)
                .collect(Collectors.toSet()));
        return dto;
    }

    public static GroupeEntity toEntity(GroupeDto dto) {
        if (dto == null) {
            return null;
        }
        GroupeEntity entity = new GroupeEntity();
        entity.setIdGroupe(dto.getIdGroupe());
        entity.setNomGroupe(dto.getNomGroupe());
        entity.setNbEtuGroupe(dto.getNbEtuGroupe());
        return entity;
    }

    private static PromoGroupeDto promoToDto(PromoEntity entity) {
        PromoGroupeDto promo = new PromoGroupeDto();
        if (entity != null) {
            promo.setIdPromo(entity.getIdPromo());
            promo.setNomPromo(entity.getNomPromo());
            promo.setAnneePromo(entity.getAnneePromo());
            promo.setNbEtuPromo(entity.getNbEtuPromo());
            promo.setPromoEstComposeeIds(entity.getPromoEstComposeeEntities()
                    .stream()
                    .map(promoEstComposeeEntity -> {
                        return new PromoEstComposeeIdDto(
                                promoEstComposeeEntity.getPromo().getIdPromo(),
                                promoEstComposeeEntity.getModule().getIdModule()
                        );
                    })
                    .collect(Collectors.toSet()));
            promo.setCmIds(entity.getCmEntities()
                    .stream()
                    .map(cm -> {
                        return new CMIdDto(
                                cm.getIdCM().getIdProf(),
                                cm.getIdCM().getIdPromo(),
                                cm.getIdCM().getIdComposante(),
                                cm.getIdCM().getIdRepartitionSemaine()
                        );
                    })
                    .collect(Collectors.toSet()));
        }
        return promo;
    }

    private static TDGroupeDto tdToDto(TDEntity entity) {
        TDGroupeDto td = new TDGroupeDto();
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
                Composante
             */
            td.setIdComposante(entity.getComposante().getIdComposante());
            td.setNomComposante(entity.getComposante().getNomComposante());
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

            /*
                Repartition Semaine
             */
            td.setIdRepartitionSemaine(entity.getRepartitionSemaine().getIdRepartitionSemaine());
            td.setNumSemaine(entity.getRepartitionSemaine().getNumSemaine());
            td.setQteTypeCours(entity.getRepartitionSemaine().getQteTypeCours());
            td.setCmRepartitionSemaineIds(entity.getRepartitionSemaine().getCmEntities()
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
            td.setTdRepartitionSemaineIds(entity.getRepartitionSemaine().getTdEntities()
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
            td.setTpRepartitionSemaineIds(entity.getRepartitionSemaine().getTpEntities()
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
        return td;
    }

    private static SousGroupeGroupeDto sousGroupeToDto(SousGroupeEntity entity) {
        SousGroupeGroupeDto sousGroupe = new SousGroupeGroupeDto();
        if (entity != null) {
            sousGroupe.setIdSousGroupe(entity.getIdSousGroupe());
            sousGroupe.setNomSousGroupe(entity.getNomSousGroupe());
            sousGroupe.setNbEtuSousGroupe(entity.getNbEtuSousGroupe());
            sousGroupe.setTpIds(entity.getTpEntities()
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
            sousGroupe.setParticipeAIds(entity.getParticipeAEntities()
                    .stream()
                    .map(participeAEntity -> {
                        return new ParticipeAIdDto(
                                participeAEntity.getIdParticipeA().getIdSousGroupe(),
                                participeAEntity.getIdParticipeA().getIdCours()
                        );
                    })
                    .collect(Collectors.toSet()));
        }
        return sousGroupe;
    }
}
