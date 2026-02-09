package com.univtime.informatique.mappers;

import com.univtime.informatique.dto.ids.*;
import com.univtime.informatique.dto.professeurDto.*;
import com.univtime.informatique.entities.*;

import java.util.stream.Collectors;

public class ProfesseurMapper {

    private ProfesseurMapper() {

    }

    public static ProfesseurDto toDTO(ProfesseurEntity entity) {
        if (entity == null) {
            return null;
        }
        ProfesseurDto dto = new ProfesseurDto();
        dto.setIdProf(entity.getIdProf());
        dto.setNomProf(entity.getNomProf());
        dto.setPrenomProf(entity.getPrenomProf());
        dto.setIntervenantExterieur(entity.getIntervenantExterieur());
        dto.setCmDto(entity.getCmEntities()
                .stream()
                .map(ProfesseurMapper::cmToDto)
                .collect(Collectors.toSet()));
        dto.setTdDto(entity.getTdEntities()
                .stream()
                .map(ProfesseurMapper::tdToDto)
                .collect(Collectors.toSet()));
        dto.setTpDto(entity.getTpEntities()
                .stream()
                .map(ProfesseurMapper::tpToDto)
                .collect(Collectors.toSet()));
        dto.setCoursDto(entity.getCoursEntities()
                .stream()
                .map(ProfesseurMapper::coursToDto)
                .collect(Collectors.toSet()));
        dto.setJourDto(entity.getJourEntities()
                .stream()
                .map(ProfesseurMapper::jourToDto)
                .collect(Collectors.toSet()));
        return dto;
    }

    public static ProfesseurEntity toEntity(ProfesseurDto dto) {
        if (dto == null) {
            return null;
        }
        ProfesseurEntity entity = new ProfesseurEntity();
        entity.setIdProf(dto.getIdProf());
        entity.setNomProf(dto.getNomProf());
        entity.setPrenomProf(dto.getPrenomProf());
        entity.setIntervenantExterieur(dto.isIntervenantExterieur());
        return entity;
    }

    private static CMProfesseurDto cmToDto(CMEntity entity) {
        CMProfesseurDto cm = new CMProfesseurDto();
        if (entity != null) {
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

    private static TDProfesseurDto tdToDto(TDEntity entity) {
        TDProfesseurDto td = new TDProfesseurDto();
        if (entity != null) {
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

    private static TPProfesseurDto tpToDto(TPEntity entity) {
        TPProfesseurDto tp = new TPProfesseurDto();
        if (entity != null) {
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

    private static CoursProfesseurDto coursToDto(CoursEntity entity) {
        CoursProfesseurDto cours = new CoursProfesseurDto();
        if (entity != null) {
            cours.setIdCours(entity.getIdCours());
            cours.setHeureDebutCours(entity.getHeureDebutCours());
            cours.setHeureFinCours(entity.getHeureFinCours());
            cours.setTypeCours(entity.getTypeCours());
            cours.setComposanteId(entity.getComposante().getIdComposante());
            cours.setSalleId(entity.getSalle().getIdSalle());
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

    private static JourProfesseurDto jourToDto(JourEntity entity) {
        JourProfesseurDto jour = new JourProfesseurDto();
        if (entity != null) {
            jour.setIdJour(entity.getIdJour());
            jour.setJourSemaine(entity.getJourSemaine());
            jour.setDisponibiliteIds(entity.getDisponibiliteEntities()
                    .stream()
                    .map(DisponibiliteEntity::getIdDispo)
                    .collect(Collectors.toSet()));
        }
        return jour;
    }
}
