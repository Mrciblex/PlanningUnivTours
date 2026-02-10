package com.univtime.informatique.mappers;

import com.univtime.informatique.dto.composanteDto.*;
import com.univtime.informatique.dto.idsDto.*;
import com.univtime.informatique.entities.*;

import java.util.stream.Collectors;

public class ComposanteMapper {

    private ComposanteMapper() {

    }

    public static ComposanteDto toDto(ComposanteEntity entity) {
        if (entity == null) return null;
        ComposanteDto dto = new ComposanteDto();
        dto.setIdComposante(entity.getIdComposante());
        dto.setNomComposante(entity.getNomCoposante());
        dto.setVolumeHoraireTotal(entity.getVolumeHoraireTotal());
        dto.setVolumeHoraireCM(entity.getVolumeHoraireCM());
        dto.setVolumeHoraireTD(entity.getVolumeHoraireTD());
        dto.setVolumeHoraireTP(entity.getVolumeHoraireTP());
        dto.setBlocHoraireCM(entity.getBlocHoraireCM());
        dto.setBlocHoraireTD(entity.getBlocHoraireTD());
        dto.setBlocHoraireTP(entity.getBlocHoraireTP());
        dto.setModuleDto(moduleToDto(entity.getModule()));
        dto.setCmDto(entity.getCmEntities()
                .stream()
                .map(ComposanteMapper::cmToDto)
                .collect(Collectors.toSet()));
        dto.setTdDto(entity.getTdEntities()
                .stream()
                .map(ComposanteMapper::tdToDto)
                .collect(Collectors.toSet()));
        dto.setTpDto(entity.getTpEntities()
                .stream()
                .map(ComposanteMapper::tpToDto)
                .collect(Collectors.toSet()));
        dto.setCoursDto(entity.getCoursEntities()
                .stream()
                .map(ComposanteMapper::coursToDto)
                .collect(Collectors.toSet()));
        dto.setBesoinSalleDto(entity.getBesoinSalleEntities()
                .stream()
                .map(ComposanteMapper::besoinSalleToDto)
                .collect(Collectors.toSet()));
        return dto;
    }

    public static ComposanteEntity toEntity(ComposanteDto dto) {
        if (dto == null) {
            return null;
        }
        ComposanteEntity entity = new ComposanteEntity();
        entity.setIdComposante(dto.getIdComposante());
        entity.setNomCoposante(dto.getNomComposante());
        entity.setVolumeHoraireTotal(dto.getVolumeHoraireTotal());
        entity.setVolumeHoraireCM(dto.getVolumeHoraireCM());
        entity.setVolumeHoraireTD(dto.getVolumeHoraireTD());
        entity.setVolumeHoraireTP(dto.getVolumeHoraireTP());
        entity.setBlocHoraireCM(dto.getBlocHoraireCM());
        entity.setBlocHoraireTD(dto.getBlocHoraireTD());
        entity.setBlocHoraireTP(dto.getBlocHoraireTP());
        return entity;
    }

    private static ModuleComposanteDto moduleToDto(ModuleEntity entity) {
        ModuleComposanteDto module = new ModuleComposanteDto();
        if (entity != null) {
            module.setIdModule(entity.getIdModule());
            module.setNomModule(entity.getNomModule());
            module.setPromoEstComposeeIds(entity.getPromoEstComposeeEntities()
                    .stream()
                    .map(promoEstComposeeEntity -> {
                        return new PromoEstComposeeIdDto(
                                promoEstComposeeEntity.getIdPromoEstComposee().getIdPromo(),
                                promoEstComposeeEntity.getIdPromoEstComposee().getIdModule()
                        );
                    })
                    .collect(Collectors.toSet()));
        }
        return module;
    }

    private static CMComposanteDto cmToDto(CMEntity entity) {
        CMComposanteDto cm = new CMComposanteDto();
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

    private static TDComposanteDto tdToDto(TDEntity entity) {
        TDComposanteDto td = new TDComposanteDto();
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

    private static TPComposanteDto tpToDto(TPEntity entity) {
        TPComposanteDto tp = new TPComposanteDto();
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

    private static CoursComposanteDto coursToDto(CoursEntity entity) {
        CoursComposanteDto cours = new CoursComposanteDto();
        if (entity != null) {
            cours.setIdCours(entity.getIdCours());
            cours.setHeureDebutCours(entity.getHeureDebutCours());
            cours.setHeureFinCours(entity.getHeureFinCours());
            cours.setTypeCours(entity.getTypeCours());
            cours.setProfesseurId(entity.getProfesseur().getIdProf());
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

    private static BesoinSalleComposanteDto besoinSalleToDto(BesoinSalleEntity entity) {
        BesoinSalleComposanteDto besoinSalle = new BesoinSalleComposanteDto();
        if (entity != null) {
            besoinSalle.setTypeBesoin(entity.getTypeBesoin());

            /*
                Besoin Salle
             */
            besoinSalle.setIdSalle(entity.getSalle().getIdSalle());
            besoinSalle.setNbPlace(entity.getSalle().getNbPlace());
            besoinSalle.setSalleMachine(entity.getSalle().isSalleMachine());
            besoinSalle.setNbPC(entity.getSalle().getNbPC());
            besoinSalle.setCoursSalleIds(entity.getSalle().getCoursEntities()
                    .stream()
                    .map(CoursEntity::getIdCours)
                    .collect(Collectors.toSet()));
            besoinSalle.setBesoinSalleSalleIds(entity.getSalle().getBesoinSalleEntities()
                    .stream()
                    .map(besoinSalleEntity -> {
                        return new BesoinSalleIdDto(
                                besoinSalleEntity.getIdBesoinSalle().getIdSalle(),
                                besoinSalleEntity.getIdBesoinSalle().getIdComposante()
                        );
                    })
                    .collect(Collectors.toSet()));
        }
        return besoinSalle;
    }
}
