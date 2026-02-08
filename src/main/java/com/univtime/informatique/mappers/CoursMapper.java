package com.univtime.informatique.mappers;

import com.univtime.informatique.dto.coursDto.*;
import com.univtime.informatique.entities.*;
import com.univtime.informatique.entities.ids.ParticipeAId;

import java.util.stream.Collectors;

public class CoursMapper {

    private CoursMapper() {

    }

    public static CoursDto toDto(CoursEntity entity) {
        if (entity == null) {
            return null;
        }
        CoursDto dto = new CoursDto();
        dto.setIdCours(entity.getIdCours());
        dto.setHeureDebutCours(entity.getHeureDebutCours());
        dto.setHeureFinCours(entity.getHeureFinCours());
        dto.setTypeCoursEnum(entity.getTypeCours());
        dto.setComposanteDto(composanteToDto(entity.getComposante()));
        dto.setProfesseurDto(professeurToDto(entity.getProfesseur()));
        dto.setSalleDto(salleToDto(entity.getSalle()));
        dto.setParticipeADto(entity.getParticipeAEntities()
                .stream()
                .map(CoursMapper::participeAToDto)
                .collect(Collectors.toSet()));
        return dto;
    }

    public static CoursEntity toEntity(CoursDto dto) {
        if (dto == null) {
            return null;
        }
        CoursEntity entity = new CoursEntity();
        entity.setIdCours(dto.getIdCours());
        entity.setHeureDebutCours(dto.getHeureDebutCours());
        entity.setHeureFinCours(dto.getHeureFinCours());
        entity.setTypeCours(dto.getTypeCoursEnum());
        return entity;
    }

    private static ComposanteCoursDto composanteToDto(ComposanteEntity entity) {
        ComposanteCoursDto composante = new ComposanteCoursDto();
        if (entity != null) {
            composante.setIdComposante(entity.getIdComposante());
            composante.setNomComposante(entity.getNomCoposante());
            composante.setVolumeHoraireTotal(entity.getVolumeHoraireTotal());
            composante.setVolumeHoraireCM(entity.getVolumeHoraireCM());
            composante.setVolumeHoraireTD(entity.getVolumeHoraireTD());
            composante.setVolumeHoraireTP(entity.getVolumeHoraireTP());
            composante.setBlocHoraireCM(entity.getBlocHoraireCM());
            composante.setBlocHoraireTD(entity.getBlocHoraireTD());
            composante.setBlocHoraireTP(entity.getBlocHoraireTP());
        }
        return composante;
    }

    private static ProfesseurCoursDto professeurToDto(ProfesseurEntity entity) {
        ProfesseurCoursDto prof = new ProfesseurCoursDto();
        if (entity != null) {
            prof.setIdProf(entity.getIdProf());
            prof.setNomProf(entity.getNomProf());
            prof.setPrenomProf(entity.getPrenomProf());
            prof.setIntervenantExterieur(Boolean.TRUE.equals(entity.getIntervenantExterieur()));
        }
        return prof;
    }

    private static SalleCoursDto salleToDto(SalleEntity entity) {
        SalleCoursDto salle = new SalleCoursDto();
        if (entity != null) {
            salle.setIdSalle(entity.getIdSalle());
            salle.setNbPlace(entity.getNbPlace());
            salle.setSalleMachine(entity.isSalleMachine());
            salle.setNbPC(entity.getNbPC());
        }
        return salle;
    }

    private static ParticipeACoursDto participeAToDto(ParticipeAEntity entity) {
        ParticipeACoursDto participeA = new ParticipeACoursDto();
        if (entity != null) {
            participeA.setIdParticipeA(
                    new ParticipeAId(
                            entity.getSousGroupe().getIdSousGroupe(),
                            entity.getCours().getIdCours()
                    )
            );
        }
        return participeA;
    }
}
