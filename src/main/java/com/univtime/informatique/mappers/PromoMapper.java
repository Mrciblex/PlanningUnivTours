package com.univtime.informatique.mappers;

import com.univtime.informatique.dto.idsDto.*;
import com.univtime.informatique.dto.promoDto.CMPromoDto;
import com.univtime.informatique.dto.promoDto.GroupePromoDto;
import com.univtime.informatique.dto.promoDto.PromoDto;
import com.univtime.informatique.dto.promoDto.PromoEstComposeePromoDto;
import com.univtime.informatique.entities.*;
import com.univtime.informatique.entities.ids.CMId;
import com.univtime.informatique.entities.ids.PromoEstComposeeId;

import java.util.stream.Collectors;

public class PromoMapper {

    private PromoMapper() {

    }

    public static PromoDto toDto(PromoEntity entity) {
        if (entity == null) {
            return null;
        }
        PromoDto dto = new PromoDto();
        dto.setIdPromo(entity.getIdPromo());
        dto.setNomPromo(entity.getNomPromo());
        dto.setAnneePromo(entity.getAnneePromo());
        dto.setNbEtuPromo(entity.getNbEtuPromo());
        dto.setDebutS1Promo(entity.getDebutS1Promo());
        dto.setFinS1Promo(entity.getFinS1Promo());
        dto.setDebutS2Promo(entity.getDebutS2Promo());
        dto.setFinS2Promo(entity.getFinS2Promo());
        dto.setPromoEstComposeeDto(entity.getPromoEstComposeeEntities()
                .stream()
                .map(PromoMapper::promoEstComposeeToDto)
                .collect(Collectors.toSet()));
        dto.setCmDto(entity.getCmEntities()
                .stream()
                .map(PromoMapper::cmToDto)
                .collect(Collectors.toSet()));
        dto.setGroupeDto(entity.getGroupeEntities()
                .stream()
                .map(PromoMapper::groupeToDto)
                .collect(Collectors.toSet()));
        return dto;
    }

    public static PromoEntity toEntity(PromoDto dto) {
        if (dto == null) {
            return null;
        }
        PromoEntity entity = new PromoEntity();
        entity.setIdPromo(dto.getIdPromo());
        entity.setNomPromo(dto.getNomPromo());
        entity.setAnneePromo(dto.getAnneePromo());
        entity.setNbEtuPromo(dto.getNbEtuPromo());
        entity.setDebutS1Promo(dto.getDebutS1Promo());
        entity.setFinS1Promo(dto.getFinS1Promo());
        entity.setDebutS2Promo(dto.getDebutS2Promo());
        entity.setFinS2Promo(dto.getFinS2Promo());
        return entity;
    }


    private static PromoEstComposeePromoDto promoEstComposeeToDto(PromoEstComposeeEntity entity) {
        PromoEstComposeePromoDto promoEstComposee = new PromoEstComposeePromoDto();
        if (entity != null) {
            /*
                Module
             */
            promoEstComposee.setIdModule(entity.getModule().getIdModule());
            promoEstComposee.setNomModule(entity.getModule().getNomModule());
            promoEstComposee.setPromoEstComposeeModuleIds(entity.getModule().getPromoEstComposeeEntities()
                    .stream()
                    .map(promoEstComposeeEntity -> {
                        return new PromoEstComposeeIdDto(
                                promoEstComposeeEntity.getIdPromoEstComposee().getIdPromo(),
                                promoEstComposeeEntity.getIdPromoEstComposee().getIdModule()
                        );
                    })
                    .collect(Collectors.toSet()));
            promoEstComposee.setComposanteModuleIds(entity.getModule().getComposanteEntities()
                    .stream()
                    .map(ComposanteEntity::getIdComposante)
                    .collect(Collectors.toSet()));
        }
        return promoEstComposee;
    }

    private static CMPromoDto cmToDto(CMEntity entity) {
        CMPromoDto cm = new CMPromoDto();
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
                Composante
             */
            cm.setIdComposante(entity.getComposante().getIdComposante());
            cm.setNomComposante(entity.getComposante().getNomComposante());
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

            /*
                Repartition Semaine
             */
            cm.setIdRepartitionSemaine(entity.getRepartitionSemaine().getIdRepartitionSemaine());
            cm.setNumSemaine(entity.getRepartitionSemaine().getNumSemaine());
            cm.setQteTypeCours(entity.getRepartitionSemaine().getQteTypeCours());
            cm.setCmRepartitionSemaineIds(entity.getRepartitionSemaine().getCmEntities()
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
            cm.setTdRepartitionSemaineIds(entity.getRepartitionSemaine().getTdEntities()
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
            cm.setTpRepartitionSemaineIds(entity.getRepartitionSemaine().getTpEntities()
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
        return cm;
    }

    private static GroupePromoDto groupeToDto(GroupeEntity entity) {
        GroupePromoDto groupe = new GroupePromoDto();
        if (entity != null) {
            groupe.setIdGroupe(entity.getIdGroupe());
            groupe.setNomGroupe(entity.getNomGroupe());
            groupe.setNbEtuGroupe(entity.getNbEtuGroupe());
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
            groupe.setSousGroupeIds(entity.getSousGroupeEntities()
                    .stream()
                    .map(SousGroupeEntity::getIdSousGroupe)
                    .collect(Collectors.toSet()));
        }
        return groupe;
    }
}
