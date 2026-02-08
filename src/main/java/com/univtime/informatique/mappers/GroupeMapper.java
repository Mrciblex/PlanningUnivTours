package com.univtime.informatique.mappers;

import com.univtime.informatique.dto.groupeDto.GroupeDto;
import com.univtime.informatique.dto.groupeDto.PromoGroupeDto;
import com.univtime.informatique.dto.groupeDto.SousGroupeGroupeDto;
import com.univtime.informatique.dto.groupeDto.TDGroupeDto;
import com.univtime.informatique.dto.ids.CMIdDto;
import com.univtime.informatique.dto.ids.PromoEstComposeeIdDto;
import com.univtime.informatique.entities.*;

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
            promo.setPromoEstComposeeIds(entity.getPromoEstComposeeEntities()
                    .stream()
                    .map(promoEstComposee -> {
                        PromoEstComposeeIdDto promoEstComposeeId = new PromoEstComposeeIdDto(
                                promoEstComposee.getPromo().getIdPromo(),
                                promoEstComposee.getModule().getIdModule()
                        );
                        return promoEstComposeeId;
                    })
                    .collect(Collectors.toSet()));
            promo.setCmIds(entity.getCmEntities()
                    .stream()
                    .map(cm -> {
                        CMIdDto cmId = new CMIdDto(
                                cm.getIdCM().getIdProf(),
                                cm.getIdCM().getIdPromo(),
                                cm.getIdCM().getIdComposante(),
                                cm.getIdCM().getIdRepartitionSemaine()
                        );
                        return cmId;
                    })
                    .collect(Collectors.toSet()));
        }
        return promo;
    }

    private static TDGroupeDto tdToDto(TDEntity entity) {
        TDGroupeDto td = new TDGroupeDto();
        if (entity != null) {
            td.setIdProf(entity.getProfesseur().getIdProf());
            td.setNomProf(entity.getProfesseur().getNomProf());
            td.setPrenomProf(entity.getProfesseur().getPrenomProf());
            td.setIntervenantExterieur(entity.getProfesseur().getIntervenantExterieur());
            td.setCmProfIds(entity.getProfesseur().getCmEntities()
                    .stream()
                    .map(cm -> {
                        CMIdDto cmId = new CMIdDto(
                                cm.getIdCM().getIdProf(),
                                cm.getIdCM().getIdPromo(),
                                cm.getIdCM().getIdComposante(),
                                cm.getIdCM().getIdRepartitionSemaine()
                        );
                        return cmId;
                    })
                    .collect(Collectors.toSet()));
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
