package com.univtime.informatique.mappers;

import com.univtime.informatique.dto.repartitionSemaineDto.CMRepartitionSemaineDto;
import com.univtime.informatique.dto.repartitionSemaineDto.RepartitionSemaineDto;
import com.univtime.informatique.dto.repartitionSemaineDto.TDRepartitionSemaineDto;
import com.univtime.informatique.dto.repartitionSemaineDto.TPRepartitionSemaineDto;
import com.univtime.informatique.entities.CMEntity;
import com.univtime.informatique.entities.RepartitionSemaineEntity;
import com.univtime.informatique.entities.TDEntity;
import com.univtime.informatique.entities.TPEntity;
import com.univtime.informatique.entities.ids.CMId;
import com.univtime.informatique.entities.ids.TDId;
import com.univtime.informatique.entities.ids.TPId;

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

    private static TDRepartitionSemaineDto tdToDto(TDEntity entity) {
        TDRepartitionSemaineDto td = new TDRepartitionSemaineDto();
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

    private static TPRepartitionSemaineDto tpToDto(TPEntity entity) {
        TPRepartitionSemaineDto tp = new TPRepartitionSemaineDto();
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
}
