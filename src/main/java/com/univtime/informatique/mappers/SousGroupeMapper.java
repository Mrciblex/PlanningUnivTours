package com.univtime.informatique.mappers;

import com.univtime.informatique.dto.sousGroupeDto.GroupeSousGroupeDto;
import com.univtime.informatique.dto.sousGroupeDto.ParticipeASousGroupeDto;
import com.univtime.informatique.dto.sousGroupeDto.SousGroupeDto;
import com.univtime.informatique.dto.sousGroupeDto.TPSousGroupeDto;
import com.univtime.informatique.entities.GroupeEntity;
import com.univtime.informatique.entities.ParticipeAEntity;
import com.univtime.informatique.entities.SousGroupeEntity;
import com.univtime.informatique.entities.TPEntity;
import com.univtime.informatique.entities.ids.ParticipeAId;
import com.univtime.informatique.entities.ids.TPId;

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
        }
        return groupe;
    }

    private static TPSousGroupeDto tpToDto(TPEntity entity) {
        TPSousGroupeDto tp = new TPSousGroupeDto();
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

    private static ParticipeASousGroupeDto participeAToDto(ParticipeAEntity entity) {
        ParticipeASousGroupeDto part = new ParticipeASousGroupeDto();
        if (entity != null) {
            part.setIdParticipeA(
                    new ParticipeAId(
                                entity.getSousGroupe().getIdSousGroupe(),
                                entity.getCours().getIdCours()
                    )
            );
        }
        return part;
    }
}
