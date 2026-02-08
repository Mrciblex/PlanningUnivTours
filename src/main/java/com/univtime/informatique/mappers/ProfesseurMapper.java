package com.univtime.informatique.mappers;

import com.univtime.informatique.dto.professeurDto.*;
import com.univtime.informatique.entities.*;
import com.univtime.informatique.entities.ids.CMId;
import com.univtime.informatique.entities.ids.TDId;
import com.univtime.informatique.entities.ids.TPId;

import java.util.stream.Collectors;

public class ProfesseurMapper {

    private ProfesseurMapper() {

    }

    public static ProfesseurDto toDTO(ProfesseurEntity entity) {
        if (entity == null) {
            return null;
        }
        ProfesseurDto dto = new ProfesseurDto();
        dto.setIdProf(entity.getIdProf());
        dto.setNomProf(entity.getNomProf());
        dto.setPrenomProf(entity.getPrenomProf());
        dto.setIntervenantExterieur(Boolean.TRUE.equals(entity.getIntervenantExterieur()));
        dto.setCmDto(entity.getCmEntities()
                .stream()
                .map(ProfesseurMapper::cmToDto)
                .collect(Collectors.toSet()));
        dto.setTdDto(entity.getTdEntities()
                .stream()
                .map(ProfesseurMapper::tdToDto)
                .collect(Collectors.toSet()));
        dto.setTpDto(entity.getTpEntities()
                .stream()
                .map(ProfesseurMapper::tpToDto)
                .collect(Collectors.toSet()));
        dto.setCoursDto(entity.getCoursEntities()
                .stream()
                .map(ProfesseurMapper::coursToDto)
                .collect(Collectors.toSet()));
        dto.setJourDto(entity.getJourEntities()
                .stream()
                .map(ProfesseurMapper::jourToDto)
                .collect(Collectors.toSet()));
        return dto;
    }

    public static ProfesseurEntity toEntity(ProfesseurDto dto) {
        if (dto == null) {
            return null;
        }
        ProfesseurEntity entity = new ProfesseurEntity();
        entity.setIdProf(dto.getIdProf());
        entity.setNomProf(dto.getNomProf());
        entity.setPrenomProf(dto.getPrenomProf());
        entity.setIntervenantExterieur(dto.isIntervenantExterieur());
        return entity;
    }

    private static CMProfesseurDto cmToDto(CMEntity entity) {
        CMProfesseurDto cm = new CMProfesseurDto();
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

    private static TDProfesseurDto tdToDto(TDEntity entity) {
        TDProfesseurDto td = new TDProfesseurDto();
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

    private static TPProfesseurDto tpToDto(TPEntity entity) {
        TPProfesseurDto tp = new TPProfesseurDto();
        if (entity != null) {
            tp.setIdTP(
                    new TPId(
                            entity.getProfesseur().getIdProf(),
                            entity.getSousGroupe().getIdSousGroupe(),
                            entity.getComposante().getIdComposante(),
                            entity.getRepartitionSemaine().getIdRepartitionSemaine()
                    )
            );
        }
        return tp;
    }

    private static CoursProfesseurDto coursToDto(CoursEntity entity) {
        CoursProfesseurDto cours = new CoursProfesseurDto();
        if (entity != null) {
            cours.setIdCours(entity.getIdCours());
        }
        return cours;
    }

    private static JourProfesseurDto jourToDto(JourEntity entity) {
        JourProfesseurDto jour = new JourProfesseurDto();
        if (entity != null) {
            jour.setIdJour(entity.getIdJour());
        }
        return jour;
    }
}
