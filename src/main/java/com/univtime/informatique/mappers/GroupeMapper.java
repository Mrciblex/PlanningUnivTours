package com.univtime.informatique.mappers;

import com.univtime.informatique.dto.groupeDto.GroupeDto;
import com.univtime.informatique.dto.groupeDto.PromoGroupeDto;
import com.univtime.informatique.entities.GroupeEntity;
import com.univtime.informatique.entities.PromoEntity;

public final class GroupeMapper {

    private GroupeMapper() {
        // Private constructor pour éviter l'instantiation
    }

    public static GroupeDto toDto(GroupeEntity entity) {
        if (entity == null) return null;
        GroupeDto dto = new GroupeDto();
        dto.setIdGroupe(entity.getIdGroupe());
        dto.setNomGroupe(entity.getNomGroupe());
        dto.setNbEtuGroupe(entity.getNbEtuGroupe());
        if (entity.getPromo() != null) {
            PromoGroupeDto p = new PromoGroupeDto();
            p.setIdPromo(entity.getPromo().getIdPromo());
            p.setNomPromo(entity.getPromo().getNomPromo());
            dto.setPromoDto(p);
        }
        return dto;
    }

    public static GroupeEntity toEntity(GroupeDto dto) {
        if (dto == null) return null;
        GroupeEntity entity = new GroupeEntity();
        entity.setIdGroupe(dto.getIdGroupe());
        entity.setNomGroupe(dto.getNomGroupe());
        entity.setNbEtuGroupe(dto.getNbEtuGroupe());
        if (dto.getPromoDto() != null) {
            PromoEntity p = new PromoEntity();
            p.setIdPromo(dto.getPromoDto().getIdPromo());
            entity.setPromo(p);
        }
        return entity;
    }
}
