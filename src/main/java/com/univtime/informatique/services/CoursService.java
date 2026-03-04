package com.univtime.informatique.services;

import com.univtime.informatique.dto.coursDto.CoursDto;
import com.univtime.informatique.dto.coursDto.ParticipeACoursDto;
import com.univtime.informatique.entities.*;
import com.univtime.informatique.entities.ids.ParticipeAId;
import com.univtime.informatique.exceptions.ResourceNotFoundException;
import com.univtime.informatique.mappers.CoursMapper;
import com.univtime.informatique.repositories.CoursRepository;
import com.univtime.informatique.repositories.ParticipeARepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CoursService {
    private final CoursRepository coursRepository;

    private final ComposanteService composanteService;

    private final ProfesseurService professeurService;

    private final SalleService salleService;

    private final ParticipeARepository participeARepository;

    private final SousGroupeService sousGroupeService;

    public CoursService(CoursRepository coursRepository,
                        ComposanteService composanteService,
                        ProfesseurService professeurService,
                        SalleService salleService,
                        ParticipeARepository participeARepository,
                        SousGroupeService sousGroupeService) {
        this.coursRepository = coursRepository;
        this.composanteService = composanteService;
        this.professeurService = professeurService;
        this.salleService = salleService;
        this.participeARepository = participeARepository;
        this.sousGroupeService = sousGroupeService;
    }

    public List<CoursDto> findAllCours() {
        List<CoursEntity> coursEntities = coursRepository.findAll();

        return coursEntities
                .stream()
                .map(CoursMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<CoursDto> findAllCoursByPromoIdBySemestre(Integer idPromo, Integer numSemestre) {
        List<CoursEntity> coursEntities = coursRepository.findByIdPromoBySemestre(idPromo, numSemestre);

        return coursEntities
                .stream()
                .map(CoursMapper::toDto)
                .collect(Collectors.toList());
    }

    public CoursDto findCoursDtoById(Integer id) {
        CoursEntity coursEntity = coursRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Le cours avec l'id n'est trouvé : " + id));

        return CoursMapper.toDto(coursEntity);
    }

    public CoursEntity findCoursEntityById(Integer id) {
        return coursRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Le cours avec l'id n'est trouvé : " + id));
    }

    public CoursDto createCours(CoursDto coursDto) {
        if (coursDto.getComposanteDto() == null || coursDto.getComposanteDto().getIdComposante() == null) {
            throw new ResourceNotFoundException("L'id de la composante est obligatoire");
        }

        if (coursDto.getProfesseurDto() == null || coursDto.getProfesseurDto().getIdProf() == null) {
            throw new ResourceNotFoundException("L'id du professeur est obligatoire");
        }

        /*
        if (coursDto.getSalleDto() == null || coursDto.getSalleDto().getIdSalle() == null) {
            throw new ResourceNotFoundException("L'id de la salle est obligatoire");
        }
        */

        CoursEntity coursEntity = CoursMapper.toEntity(coursDto);

        // Composante
        Integer idComposanteR = coursDto.getComposanteDto().getIdComposante();
        ComposanteEntity composanteEntity = composanteService.findComposanteEntityById(idComposanteR);
        coursEntity.setComposante(composanteEntity);

        // Professeur
        Integer idProfR = coursDto.getProfesseurDto().getIdProf();
        ProfesseurEntity professeurEntity = professeurService.findProfesseurEntityById(idProfR);
        coursEntity.setProfesseur(professeurEntity);

        // Salle
        Integer idSalleR = coursDto.getSalleDto() != null ? coursDto.getSalleDto().getIdSalle() : null;
        if (idSalleR != null){
            SalleEntity salleEntity = salleService.findSalleEntityById(idSalleR);
            coursEntity.setSalle(salleEntity);
        }else{
            coursEntity.setSalle(null);
        }

        CoursEntity savedCours = coursRepository.save(coursEntity);

        if (coursDto.getParticipeADto() != null && !coursDto.getParticipeADto().isEmpty()) {
            for (ParticipeACoursDto participant : coursDto.getParticipeADto()) {
                ParticipeAEntity participation = new ParticipeAEntity();

                // Création de l'ID composé (si tu utilises ParticipeAId)
                ParticipeAId partId = new ParticipeAId();
                partId.setIdCours(savedCours.getIdCours());
                partId.setIdSousGroupe(participant.getIdSousGroupe());

                participation.setIdParticipeA(partId);
                participation.setCours(savedCours);

                // Récupération de l'entité SousGroupe
                SousGroupeEntity sg = sousGroupeService.findSousGroupeEntityById(participant.getIdSousGroupe());
                participation.setSousGroupe(sg);

                // Sauvegarde de la relation
                participeARepository.save(participation);
            }
        }

        return CoursMapper.toDto(savedCours);
    }

    public CoursDto updateCours(CoursDto coursDto) {
        CoursEntity coursEntity = findCoursEntityById(coursDto.getIdCours());

        // CoursMapper.toEntity(coursDto);
        coursEntity.setHeureDebutCours(coursDto.getHeureDebutCours());
        coursEntity.setHeureFinCours(coursDto.getHeureFinCours());
        coursEntity.setTypeCours(coursDto.getTypeCoursEnum());

        // Composante
        if (coursDto.getComposanteDto() != null && coursDto.getComposanteDto().getIdComposante() != null) {
            Integer newIdComposante = coursDto.getComposanteDto().getIdComposante();
            Integer currentIdComposante = (coursEntity.getComposante() != null) ?
                    coursEntity.getComposante().getIdComposante() : null;

            if (!newIdComposante.equals(currentIdComposante)) {
                ComposanteEntity composanteEntity = composanteService.findComposanteEntityById(newIdComposante);
                coursEntity.setComposante(composanteEntity);
            }
        }

        // Professeur
        if (coursDto.getProfesseurDto() != null && coursDto.getProfesseurDto().getIdProf() != null) {
            Integer newIdProfesseur = coursDto.getProfesseurDto().getIdProf();
            Integer currentIdProfesseur = (coursEntity.getProfesseur() != null) ?
                    coursEntity.getProfesseur().getIdProf() : null;

            if (!newIdProfesseur.equals(currentIdProfesseur)) {
                ProfesseurEntity professeurEntity = professeurService.findProfesseurEntityById(newIdProfesseur);
                coursEntity.setProfesseur(professeurEntity);
            }
        }

        // Salle
        if (coursDto.getSalleDto() != null && coursDto.getSalleDto().getIdSalle() != null) {
            Integer newIdSalle = coursDto.getSalleDto().getIdSalle();
            Integer currentIdSalle = (coursEntity.getSalle() != null) ?
                    coursEntity.getSalle().getIdSalle() : null;

            if (!newIdSalle.equals(currentIdSalle)) {
                SalleEntity salleEntity = salleService.findSalleEntityById(newIdSalle);
                coursEntity.setSalle(salleEntity);
            }
        }

        CoursEntity updatedCours = coursRepository.save(coursEntity);

        return CoursMapper.toDto(updatedCours);
    }

    public void deleteCoursByIdWithRelations(Integer id) {
        findCoursEntityById(id);

        participeARepository.deleteAllByCoursIdCours(id);

        coursRepository.deleteById(id);
    }

    public void deleteCoursById(Integer id) {
        findCoursEntityById(id);
        coursRepository.deleteById(id);
    }
}
