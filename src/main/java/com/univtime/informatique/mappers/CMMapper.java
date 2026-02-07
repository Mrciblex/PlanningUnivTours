package com.univtime.informatique.mappers;

import com.univtime.informatique.dto.cmDto.*;
import com.univtime.informatique.entities.CMEntity;
import com.univtime.informatique.entities.ProfesseurEntity;
import com.univtime.informatique.entities.PromoEntity;
import com.univtime.informatique.entities.ComposanteEntity;
import com.univtime.informatique.entities.RepartitionSemaineEntity;

public final class CMMapper {

    private CMMapper() {}

    public static CMDto toDto(CMEntity entity) {
        if (entity == null) return null;
        CMDto dto = new CMDto();
        if (entity.getProfesseur() != null) {
            ProfesseurCMDto p = new ProfesseurCMDto();
            p.setIdProf(entity.getProfesseur().getIdProf());
            p.setNomProf(entity.getProfesseur().getNomProf());
            p.setPrenomProf(entity.getProfesseur().getPrenomProf());
            dto.setProfesseurDto(p);
        }
        if (entity.getPromo() != null) {
            PromoCMDto promo = new PromoCMDto();
            promo.setIdPromo(entity.getPromo().getIdPromo());
            promo.setNomPromo(entity.getPromo().getNomPromo());
            dto.setPromoDto(promo);
        }
        if (entity.getComposante() != null) {
            ComposanteCMDto comp = new ComposanteCMDto();
            comp.setIdComposante(entity.getComposante().getIdComposante());
            comp.setNomComposante(entity.getComposante().getNomCoposante());
            dto.setComposanteDto(comp);
        }
        if (entity.getRepartitionSemaine() != null) {
            RepartitionSemaineCMDto rep = new RepartitionSemaineCMDto();
            rep.setIdRepartitionSemaine(entity.getRepartitionSemaine().getIdRepartitionSemaine());
            dto.setRepartitionSemaineDto(rep);
        }
        return dto;
    }

    public static CMEntity toEntity(CMDto dto) {
        if (dto == null) return null;
        CMEntity entity = new CMEntity();
        if (dto.getProfesseurDto() != null) {
            ProfesseurEntity p = new ProfesseurEntity();
            p.setIdProf(dto.getProfesseurDto().getIdProf());
            entity.setProfesseur(p);
        }
        if (dto.getPromoDto() != null) {
            PromoEntity promo = new PromoEntity();
            promo.setIdPromo(dto.getPromoDto().getIdPromo());
            entity.setPromo(promo);
        }
        if (dto.getComposanteDto() != null) {
            ComposanteEntity comp = new ComposanteEntity();
            comp.setIdComposante(dto.getComposanteDto().getIdComposante());
            entity.setComposante(comp);
        }
        if (dto.getRepartitionSemaineDto() != null) {
            RepartitionSemaineEntity rep = new RepartitionSemaineEntity();
            rep.setIdRepartitionSemaine(dto.getRepartitionSemaineDto().getIdRepartitionSemaine());
            entity.setRepartitionSemaine(rep);
        }
        return entity;
    }
}
