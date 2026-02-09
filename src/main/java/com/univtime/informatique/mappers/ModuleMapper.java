package com.univtime.informatique.mappers;

import com.univtime.informatique.dto.ids.*;
import com.univtime.informatique.dto.moduleDto.ComposanteModuleDto;
import com.univtime.informatique.dto.moduleDto.ModuleDto;
import com.univtime.informatique.dto.moduleDto.PromoEstComposeeModuleDto;
import com.univtime.informatique.entities.*;

import java.util.stream.Collectors;

public class ModuleMapper {

    private ModuleMapper() {

    }

    public static ModuleDto toDto(ModuleEntity entity) {
        if (entity == null) {
            return null;
        }
        ModuleDto dto = new ModuleDto();
        dto.setIdModule(entity.getIdModule());
        dto.setNomModule(entity.getNomModule());
        dto.setPromoEstComposeeDto(entity.getPromoEstComposeeEntities()
                .stream()
                .map(ModuleMapper::promoEstComposeeToDto)
                .collect(Collectors.toSet()));
        dto.setComposanteDto(entity.getComposanteEntities()
                .stream()
                .map(ModuleMapper::composanteToDto)
                .collect(Collectors.toSet()));
        return dto;
    }

    public static ModuleEntity toEntity(ModuleDto dto) {
        if (dto == null) {
            return null;
        }
        ModuleEntity entity = new ModuleEntity();
        entity.setIdModule(dto.getIdModule());
        entity.setNomModule(dto.getNomModule());
        return entity;
    }

    private static PromoEstComposeeModuleDto promoEstComposeeToDto(PromoEstComposeeEntity entity) {
        PromoEstComposeeModuleDto promoEstComposee = new PromoEstComposeeModuleDto();
        if (entity != null) {
            promoEstComposee.setIdPromo(entity.getPromo().getIdPromo());
            promoEstComposee.setNomPromo(entity.getPromo().getNomPromo());
            promoEstComposee.setAnneePromo(entity.getPromo().getAnneePromo());
            promoEstComposee.setNbEtuPromo(entity.getPromo().getNbEtuPromo());
            promoEstComposee.setPromoEstComposeePromoIds(entity.getPromo().getPromoEstComposeeEntities()
                    .stream()
                    .map(promoEstComposeeEntity -> {
                        return new PromoEstComposeeIdDto(
                                promoEstComposeeEntity.getIdPromoEstComposee().getIdPromo(),
                                promoEstComposeeEntity.getIdPromoEstComposee().getIdModule()
                        );
                    })
                    .collect(Collectors.toSet()));
            promoEstComposee.setCmPromoIds(entity.getPromo().getCmEntities()
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
            promoEstComposee.setGroupePromoIds(entity.getPromo().getGroupeEntities()
                    .stream()
                    .map(GroupeEntity::getIdGroupe)
                    .collect(Collectors.toSet()));
        }
        return promoEstComposee;
    }

    private static ComposanteModuleDto composanteToDto(ComposanteEntity entity) {
        ComposanteModuleDto composante = new ComposanteModuleDto();
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
            composante.setCoursIds(entity.getCoursEntities()
                    .stream()
                    .map(CoursEntity::getIdCours)
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
}
