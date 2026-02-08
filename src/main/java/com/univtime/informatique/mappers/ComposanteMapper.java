package com.univtime.informatique.mappers;

import com.univtime.informatique.dto.composanteDto.*;
import com.univtime.informatique.entities.*;
import com.univtime.informatique.entities.ids.CMId;
import com.univtime.informatique.entities.ids.TDId;
import com.univtime.informatique.entities.ids.TPId;

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
        }
        return module;
    }

    private static CMComposanteDto cmToDto(CMEntity entity) {
        CMComposanteDto cm = new CMComposanteDto();
        if (entity != null) {
            cm.setIdCM(
                    new CMId(
                            entity.getProfesseur().getIdProf(),
                            entity.getPromo().getIdPromo(),
                            entity.getComposante().getIdComposante(),
                            entity.getRepartitionSemaine().getIdRepartitionSemaine()
                    )
            );
        }
        return cm;
    }

    private static TDComposanteDto tdToDto(TDEntity entity) {
        TDComposanteDto td = new TDComposanteDto();
        if (entity != null) {
            td.setIdTD(
                    new TDId(
                            entity.getProfesseur().getIdProf(),
                            entity.getGroupe().getIdGroupe(),
                            entity.getComposante().getIdComposante(),
                            entity.getRepartitionSemaine().getIdRepartitionSemaine()
                    )
            );
        }
        return td;
    }

    private static TPComposanteDto tpToDto(TPEntity entity) {
        TPComposanteDto tp = new TPComposanteDto();
        if (entity != null) {
            tp.setIdTP(
                    new TPId(
                            entity.getProfesseur().getIdProf(),
                            entity.getSousGroupe().getIdSousGroupe(),
                            entity.getComposante().getIdComposante(),
                            entity.getRepartitionSemaine().getIdRepartitionSemaine()
                    )
            );
        }
        return tp;
    }

    private static CoursComposanteDto coursToDto(CoursEntity entity) {
        CoursComposanteDto cours = new CoursComposanteDto();
        if (entity != null) {
            cours.setIdCours(entity.getIdCours());
        }
        return cours;
    }

    private static BesoinSalleComposanteDto besoinSalleToDto(BesoinSalleEntity entity) {
        BesoinSalleComposanteDto besoinSalle = new BesoinSalleComposanteDto();
        if (entity != null) {
            besoinSalle.setIdBesoinSalle(entity.getIdBesoinSalle());
        }
        return besoinSalle;
    }
}
