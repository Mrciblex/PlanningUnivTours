package com.univtime.informatique.mappers;

import com.univtime.informatique.dto.tpDto.*;
import com.univtime.informatique.entities.TPEntity;
import com.univtime.informatique.entities.ProfesseurEntity;
import com.univtime.informatique.entities.SousGroupeEntity;
import com.univtime.informatique.entities.ComposanteEntity;
import com.univtime.informatique.entities.RepartitionSemaineEntity;
import com.univtime.informatique.entities.ids.TPId;

public class TPMapper {

    private TPMapper() {

    }

    public static TPDto toDto(TPEntity entity) {
        if (entity == null) {
            return null;
        }
        TPDto dto = new TPDto();
        dto.setProfesseurDto(professeurToDto(entity.getProfesseur()));
        dto.setSousGroupeDto(sousGroupeToDto(entity.getSousGroupe()));
        dto.setComposanteDto(composanteToDto(entity.getComposante()));
        dto.setRepartitionSemaineDto(repartitionSemaineToDto(entity.getRepartitionSemaine()));

        return dto;
    }

    public static TPEntity toEntity(TPDto dto) {
        if (dto == null) {
            return null;
        }
        TPEntity entity = new TPEntity();

        ProfesseurEntity prof = new ProfesseurEntity();
        if (dto.getProfesseurDto() != null) {
            prof.setIdProf(dto.getProfesseurDto().getIdProf());
            entity.setProfesseur(prof);
        }

        SousGroupeEntity sg = new SousGroupeEntity();
        if (dto.getSousGroupeDto() != null) {
            sg.setIdSousGroupe(dto.getSousGroupeDto().getIdSousGroupe());
            entity.setSousGroupe(sg);
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

        entity.setIdTP(
                new TPId(
                        prof.getIdProf(),
                        sg.getIdSousGroupe(),
                        comp.getIdComposante(),
                        rep.getIdRepartitionSemaine()));

        return entity;
    }

    private static ProfesseurTPDto professeurToDto(ProfesseurEntity entity) {
        ProfesseurTPDto prof = new ProfesseurTPDto();
        if (entity != null) {
            prof.setIdProf(entity.getIdProf());
            prof.setNomProf(entity.getNomProf());
            prof.setPrenomProf(entity.getPrenomProf());
            prof.setIntervenantExterieur(entity.getIntervenantExterieur());
        }
        return prof;
    }

    private static SousGroupeTPDto sousGroupeToDto(SousGroupeEntity entity) {
        SousGroupeTPDto sg = new SousGroupeTPDto();
        if (entity != null) {
            sg.setIdSousGroupe(entity.getIdSousGroupe());
            sg.setNomSousGroupe(entity.getNomSousGroupe());
            sg.setNbEtuSousGroupe(entity.getNbEtuSousGroupe());
        }
        return sg;
    }

    private static ComposanteTPDto composanteToDto(ComposanteEntity entity) {
        ComposanteTPDto comp = new ComposanteTPDto();
        if (entity != null) {
            comp.setIdComposante(entity.getIdComposante());
            comp.setNomComposante(entity.getNomCoposante());
            comp.setVolumeHoraireTotal(entity.getVolumeHoraireTotal());
            comp.setVolumeHoraireCM(entity.getVolumeHoraireCM());
            comp.setVolumeHoraireTD(entity.getVolumeHoraireTD());
            comp.setVolumeHoraireTP(entity.getVolumeHoraireTP());
            comp.setBlocHoraireCM(entity.getBlocHoraireCM());
            comp.setBlocHoraireTD(entity.getBlocHoraireTD());
            comp.setBlocHoraireTP(entity.getBlocHoraireTP());
        }
        return comp;
    }

    private static RepartitionSemaineTPDto repartitionSemaineToDto(RepartitionSemaineEntity entity) {
        RepartitionSemaineTPDto rep = new RepartitionSemaineTPDto();
        if (entity != null) {
            rep.setIdRepartitionSemaine(entity.getIdRepartitionSemaine());
            rep.setNumSemaine(entity.getNumSemaine());
            rep.setQteTypeCours(entity.getQteTypeCours());
        }
        return rep;
    }
}
