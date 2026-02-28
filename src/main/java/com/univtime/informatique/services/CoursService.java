package com.univtime.informatique.services;

import com.univtime.informatique.dto.coursDto.CoursDto;
import com.univtime.informatique.entities.ComposanteEntity;
import com.univtime.informatique.entities.CoursEntity;
import com.univtime.informatique.entities.ProfesseurEntity;
import com.univtime.informatique.entities.SalleEntity;
import com.univtime.informatique.exceptions.ResourceNotFoundException;
import com.univtime.informatique.mappers.CoursMapper;
import com.univtime.informatique.repositories.CoursRepository;
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

    public CoursService(CoursRepository coursRepository,
                        ComposanteService composanteService,
                        ProfesseurService professeurService,
                        SalleService salleService) {
        this.coursRepository = coursRepository;
        this.composanteService = composanteService;
        this.professeurService = professeurService;
        this.salleService = salleService;
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

        if (coursDto.getSalleDto() == null || coursDto.getSalleDto().getIdSalle() == null) {
            throw new ResourceNotFoundException("L'id de la salle est obligatoire");
        }

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
        Integer idSalleR = coursDto.getSalleDto().getIdSalle();
        SalleEntity salleEntity = salleService.findSalleEntityById(idSalleR);
        coursEntity.setSalle(salleEntity);

        CoursEntity savedCours = coursRepository.save(coursEntity);

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

    public void deleteCoursById(Integer id) {
        findCoursEntityById(id);
        coursRepository.deleteById(id);
    }
}
