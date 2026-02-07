package com.univtime.informatique.mappers;

import com.univtime.informatique.dto.tdDto.*;
import com.univtime.informatique.entities.*;
import com.univtime.informatique.entities.ids.TDId;

public class TDMapper {

    private TDMapper() {

    }

    public static TDDto toDto(TDEntity entity) {
        if (entity == null) {
            return null;
        }
        TDDto dto = new TDDto();
        dto.setProfesseurDto(professeurToDto(entity.getProfesseur()));
        dto.setGroupeDto(groupeToDto(entity.getGroupe()));
        dto.setComposanteDto(composanteToDto(entity.getComposante()));
        dto.setRepartitionSemaineDto(repartitionSemaineToDto(entity.getRepartitionSemaine()));

        return dto;
    }

    public static TDEntity toEntity(TDDto dto) {
        if (dto == null) {
            return null;
        }
        TDEntity entity = new TDEntity();

        ProfesseurEntity prof = new ProfesseurEntity();
        if (dto.getProfesseurDto() != null) {
            prof.setIdProf(dto.getProfesseurDto().getIdProf());
            entity.setProfesseur(prof);
        }

        GroupeEntity groupe = new GroupeEntity();
        if (dto.getGroupeDto() != null) {
            groupe.setIdGroupe(dto.getGroupeDto().getIdGroupe());
            entity.setGroupe(groupe);
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

        entity.setIdTD(new TDId(prof.getIdProf(), groupe.getIdGroupe(), comp.getIdComposante(), rep.getIdRepartitionSemaine()));

        return entity;
    }

    private static ProfesseurTDDto professeurToDto(ProfesseurEntity entity) {
        ProfesseurTDDto prof = new ProfesseurTDDto();
        if (entity != null) {
            prof.setIdProf(entity.getIdProf());
            prof.setNomProf(entity.getNomProf());
            prof.setPrenomProf(entity.getPrenomProf());
            prof.setIntervenantExterieur(entity.getIntervenantExterieur());
        }
        return prof;
    }

    private static GroupeTDDto groupeToDto(GroupeEntity entity) {
        GroupeTDDto groupe = new GroupeTDDto();
        if (entity != null) {
            groupe.setIdGroupe(entity.getIdGroupe());
            groupe.setNomGroupe(entity.getNomGroupe());
            groupe.setNbEtuGroupe(entity.getNbEtuGroupe());
        }
        return groupe;
    }

    private static ComposanteTDDto composanteToDto(ComposanteEntity entity) {
        ComposanteTDDto comp = new ComposanteTDDto();
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

    private static RepartitionSemaineTDDto repartitionSemaineToDto(RepartitionSemaineEntity entity) {
        RepartitionSemaineTDDto rep = new RepartitionSemaineTDDto();
        if (entity != null) {
            rep.setIdRepartitionSemaine(entity.getIdRepartitionSemaine());
            rep.setNumSemaine(entity.getNumSemaine());
            rep.setQteTypeCours(entity.getQteTypeCours());
        }
        return rep;
    }
}
