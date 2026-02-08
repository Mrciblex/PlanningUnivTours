package com.univtime.informatique.mappers;

import com.univtime.informatique.dto.promoDto.CMPromoDto;
import com.univtime.informatique.dto.promoDto.GroupePromoDto;
import com.univtime.informatique.dto.promoDto.PromoDto;
import com.univtime.informatique.dto.promoDto.PromoEstComposeePromoDto;
import com.univtime.informatique.entities.CMEntity;
import com.univtime.informatique.entities.GroupeEntity;
import com.univtime.informatique.entities.PromoEntity;
import com.univtime.informatique.entities.PromoEstComposeeEntity;
import com.univtime.informatique.entities.ids.CMId;

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
        return entity;
    }

    private static PromoEstComposeePromoDto promoEstComposeeToDto(PromoEstComposeeEntity entity) {
        PromoEstComposeePromoDto promoEstComposee = new PromoEstComposeePromoDto();
        if (entity != null) {
            promoEstComposee.set
        }
        return promoEstComposee;
    }

    private static CMPromoDto cmToDto(CMEntity entity) {
        CMPromoDto cm = new CMPromoDto();
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

    private static GroupePromoDto groupeToDto(GroupeEntity entity) {
        GroupePromoDto groupe = new GroupePromoDto();
        if (entity != null) {
            groupe.set
        }
        return groupe;
    }
}
