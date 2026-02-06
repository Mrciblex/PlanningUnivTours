package com.univtime.informatique.mappers;

import com.univtime.informatique.dto.tdDto.*;
import com.univtime.informatique.entities.*;

public final class TDMapper {

    private TDMapper() {}

    public static TDDto toDto(TDEntity entity) {
        if (entity == null) return null;
        TDDto dto = new TDDto();
        if (entity.getProfesseur() != null) {
            ProfesseurTDDto p = new ProfesseurTDDto();
            p.setIdProf(entity.getProfesseur().getIdProf());
            p.setNomProf(entity.getProfesseur().getNomProf());
            p.setPrenomProf(entity.getProfesseur().getPrenomProf());
            dto.setProfesseurDto(p);
        }
        if (entity.getGroupe() != null) {
            GroupeTDDto sg = new GroupeTDDto();
            sg.setIdGroupe(entity.getGroupe().getIdGroupe());
            sg.setNomGroupe(entity.getGroupe().getNomGroupe());
            dto.setGroupeDto(sg);
        }
        if (entity.getComposante() != null) {
            ComposanteTDDto comp = new ComposanteTDDto();
            comp.setIdComposante(entity.getComposante().getIdComposante());
            comp.setNomComposante(entity.getComposante().getNomCoposante());
            dto.setComposanteDto(comp);
        }
        if (entity.getRepartitionSemaine() != null) {
            RepartitionSemaineTDDto rep = new RepartitionSemaineTDDto();
            rep.setIdRepartitionSemaine(entity.getRepartitionSemaine().getIdRepartitionSemaine());
            dto.setRepartitionSemaineDto(rep);
        }
        return dto;
    }

    public static TDEntity toEntity(TDDto dto) {
        if (dto == null) return null;
        TDEntity entity = new TDEntity();
        if (dto.getProfesseurDto() != null) {
            ProfesseurEntity p = new ProfesseurEntity();
            p.setIdProf(dto.getProfesseurDto().getIdProf());
            entity.setProfesseur(p);
        }
        if (dto.getGroupeDto() != null) {
            GroupeEntity sg = new GroupeEntity();
            sg.setIdGroupe(dto.getGroupeDto().getIdGroupe());
            entity.setGroupe(sg);
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
