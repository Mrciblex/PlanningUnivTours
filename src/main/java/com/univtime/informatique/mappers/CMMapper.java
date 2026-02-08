package com.univtime.informatique.mappers;

import com.univtime.informatique.dto.cmDto.*;
import com.univtime.informatique.entities.CMEntity;
import com.univtime.informatique.entities.ProfesseurEntity;
import com.univtime.informatique.entities.PromoEntity;
import com.univtime.informatique.entities.ComposanteEntity;
import com.univtime.informatique.entities.RepartitionSemaineEntity;
import com.univtime.informatique.entities.ids.CMId;

public class CMMapper {

    private CMMapper() {

    }

    public static CMDto toDto(CMEntity entity) {
        if (entity == null) {
            return null;
        }
        CMDto dto = new CMDto();
        dto.setProfesseurDto(professeurToDto(entity.getProfesseur()));
        dto.setPromoDto(promoToDto(entity.getPromo()));
        dto.setComposanteDto(composanteToDto(entity.getComposante()));
        dto.setRepartitionSemaineDto(repartitionSemaineToDto(entity.getRepartitionSemaine()));
        return dto;
    }

    public static CMEntity toEntity(CMDto dto) {
        if (dto == null) {
            return null;
        }
        CMEntity entity = new CMEntity();

        ProfesseurEntity prof = new ProfesseurEntity();
        if (dto.getProfesseurDto() != null) {
            prof.setIdProf(dto.getProfesseurDto().getIdProf());
            entity.setProfesseur(prof);
        }

        PromoEntity promo = new PromoEntity();
        if (dto.getPromoDto() != null) {
            promo.setIdPromo(dto.getPromoDto().getIdPromo());
            entity.setPromo(promo);
        }

        ComposanteEntity comp = new ComposanteEntity();
        if (dto.getComposanteDto() != null) {
            comp.setIdComposante(dto.getComposanteDto().getIdComposante());
            entity.setComposante(comp);
        }

        RepartitionSemaineEntity rep = new RepartitionSemaineEntity();
        if (dto.getRepartitionSemaineDto() != null) {
            rep.setIdRepartitionSemaine(dto.getRepartitionSemaineDto().getIdRepartitionSemaine());
            entity.setRepartitionSemaine(rep);
        }

        entity.setIdCM(
                new CMId(
                        prof.getIdProf(),
                        promo.getIdPromo(),
                        comp.getIdComposante(),
                        rep.getIdRepartitionSemaine()
                )
        );

        return entity;
    }

    private static ProfesseurCMDto professeurToDto(ProfesseurEntity entity) {
        ProfesseurCMDto prof = new ProfesseurCMDto();
        if (entity != null) {
            prof.setIdProf(entity.getIdProf());
            prof.setNomProf(entity.getNomProf());
            prof.setPrenomProf(entity.getPrenomProf());
            prof.setIntervenantExterieur(entity.getIntervenantExterieur());
        }
        return prof;
    }

    private static PromoCMDto promoToDto(PromoEntity entity) {
        PromoCMDto promo = new PromoCMDto();
        if (entity != null) {
            promo.setIdPromo(entity.getIdPromo());
            promo.setNomPromo(entity.getNomPromo());
            promo.setAnneePromo(entity.getAnneePromo());
            promo.setNbEtuPromo(entity.getNbEtuPromo());
        }
        return promo;
    }

    private static ComposanteCMDto composanteToDto(ComposanteEntity entity) {
        ComposanteCMDto comp = new ComposanteCMDto();
        if (entity != null) {
            comp.setIdComposante(entity.getIdComposante());
            comp.setNomComposante(entity.getNomCoposante());
            comp.setVolumeHoraireTotal(entity.getVolumeHoraireTotal());
            comp.setVolumeHoraireCM(entity.getVolumeHoraireCM());
            comp.setVolumeHoraireTD(entity.getVolumeHoraireTD());
            comp.setVolumeHoraireTP(entity.getVolumeHoraireTP());
            comp.setBlocHoraireCM(entity.getBlocHoraireCM());
            comp.setBlocHoraireTP(entity.getBlocHoraireTP());
            comp.setBlocHoraireTP(entity.getBlocHoraireTP());
        }
        return comp;
    }

    private static RepartitionSemaineCMDto repartitionSemaineToDto(RepartitionSemaineEntity entity) {
        RepartitionSemaineCMDto rep = new RepartitionSemaineCMDto();
        if (entity != null) {
            rep.setIdRepartitionSemaine(entity.getIdRepartitionSemaine());
            rep.setNumSemaine(entity.getNumSemaine());
            rep.setQteTypeCours(entity.getQteTypeCours());
        }
        return rep;
    }
}
