package com.univtime.informatique.mappers;

import com.univtime.informatique.dto.groupeDto.GroupeDto;
import com.univtime.informatique.dto.groupeDto.PromoGroupeDto;
import com.univtime.informatique.dto.groupeDto.SousGroupeGroupeDto;
import com.univtime.informatique.dto.groupeDto.TDGroupeDto;
import com.univtime.informatique.entities.GroupeEntity;
import com.univtime.informatique.entities.PromoEntity;
import com.univtime.informatique.entities.SousGroupeEntity;
import com.univtime.informatique.entities.TDEntity;
import com.univtime.informatique.entities.ids.TDId;

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
        }
        return promo;
    }

    private static TDGroupeDto tdToDto(TDEntity entity) {
        TDGroupeDto td = new TDGroupeDto();
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

    private static SousGroupeGroupeDto sousGroupeToDto(SousGroupeEntity entity) {
        SousGroupeGroupeDto sg = new SousGroupeGroupeDto();
        if (entity != null) {
            sg.setIdSousGroupe(entity.getIdSousGroupe());
            sg.setNomSousGroupe(entity.getNomSousGroupe());
            sg.setNbEtuSousGroupe(entity.getNbEtuSousGroupe());
        }
        return sg;
    }
}
