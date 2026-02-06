package com.univtime.informatique.mappers;

import com.univtime.informatique.dto.tpDto.*;
import com.univtime.informatique.entities.TPEntity;
import com.univtime.informatique.entities.ProfesseurEntity;
import com.univtime.informatique.entities.SousGroupeEntity;
import com.univtime.informatique.entities.ComposanteEntity;
import com.univtime.informatique.entities.RepartitionSemaineEntity;

public final class TPMapper {

    private TPMapper() {}

    public static TPDto toDto(TPEntity entity) {
        if (entity == null) return null;
        TPDto dto = new TPDto();
        if (entity.getProfesseur() != null) {
            ProfesseurTPDto p = new ProfesseurTPDto();
            p.setIdProf(entity.getProfesseur().getIdProf());
            p.setNomProf(entity.getProfesseur().getNomProf());
            p.setPrenomProf(entity.getProfesseur().getPrenomProf());
            dto.setProfesseurDto(p);
        }
        if (entity.getSousGroupe() != null) {
            SousGroupeTPDto sg = new SousGroupeTPDto();
            sg.setIdSousGroupe(entity.getSousGroupe().getIdSousGroupe());
            sg.setNomSousGroupe(entity.getSousGroupe().getNomSousGroupe());
            dto.setSousGroupeDto(sg);
        }
        if (entity.getComposante() != null) {
            ComposanteTPDto comp = new ComposanteTPDto();
            comp.setIdComposante(entity.getComposante().getIdComposante());
            comp.setNomComposante(entity.getComposante().getNomCoposante());
            dto.setComposanteDto(comp);
        }
        if (entity.getRepartitionSemaine() != null) {
            RepartitionSemaineTPDto rep = new RepartitionSemaineTPDto();
            rep.setIdRepartitionSemaine(entity.getRepartitionSemaine().getIdRepartitionSemaine());
            dto.setRepartitionSemaineDto(rep);
        }
        return dto;
    }

    public static TPEntity toEntity(TPDto dto) {
        if (dto == null) return null;
        TPEntity entity = new TPEntity();
        if (dto.getProfesseurDto() != null) {
            ProfesseurEntity p = new ProfesseurEntity();
            p.setIdProf(dto.getProfesseurDto().getIdProf());
            entity.setProfesseur(p);
        }
        if (dto.getSousGroupeDto() != null) {
            SousGroupeEntity sg = new SousGroupeEntity();
            sg.setIdSousGroupe(dto.getSousGroupeDto().getIdSousGroupe());
            entity.setSousGroupe(sg);
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
