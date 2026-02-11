package com.univtime.informatique.services;

import com.univtime.informatique.dto.coursDto.CoursDto;
import com.univtime.informatique.entities.ComposanteEntity;
import com.univtime.informatique.entities.CoursEntity;
import com.univtime.informatique.entities.ProfesseurEntity;
import com.univtime.informatique.entities.SalleEntity;
import com.univtime.informatique.exceptions.ResourceNotFoundException;
import com.univtime.informatique.mappers.CoursMapper;
import com.univtime.informatique.repositories.CoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CoursService {
    @Autowired
    private CoursRepository coursRepository;

    @Autowired
    private ComposanteService composanteService;

    @Autowired
    private ProfesseurService professeurService;

    @Autowired
    private SalleService salleService;

    public List<CoursDto> findAllCours() {
        List<CoursEntity> coursEntities = coursRepository.findAll();

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
        // Vérifie la clé étrangère de composante, professeur et salle
        if (coursDto.getComposanteDto().getIdComposante() == null) {
            throw new ResourceNotFoundException("L'id de la composante est obligatoire");
        }

        if (coursDto.getProfesseurDto().getIdProf() == null) {
            throw new ResourceNotFoundException("L'id du professeur est obligatoire");
        }

        if (coursDto.getSalleDto().getIdSalle() == null) {
            throw new ResourceNotFoundException("L'id de la salle est obligatoire");
        }

        CoursEntity coursEntity = CoursMapper.toEntity(coursDto);

        // Composante
        Integer idComposanteR = coursDto.getComposanteDto().getIdComposante();

        ComposanteEntity composanteEntity = composanteService.findComposanteEntityById(idComposanteR);
        composanteEntity.setIdComposante(composanteEntity.getIdComposante());

        // Professeur
        Integer idProfR = coursDto.getProfesseurDto().getIdProf();

        ProfesseurEntity professeurEntity = professeurService.findProfesseurEntityById(idProfR);
        professeurEntity.setIdProf(professeurEntity.getIdProf());

        // Salle
        Integer idSalleR = coursDto.getSalleDto().getIdSalle();

        SalleEntity salleEntity = salleService.findSalleEntityById(idSalleR);
        salleEntity.setIdSalle(salleEntity.getIdSalle());

        CoursEntity savedCours = coursRepository.save(coursEntity);

        return CoursMapper.toDto(savedCours);
    }

    public CoursDto updateCours(CoursDto coursDto) {
        CoursEntity coursEntity = findCoursEntityById(coursDto.getIdCours());

        CoursMapper.toEntity(coursDto);

        // Composante
        if (coursDto.getComposanteDto().getIdComposante() != null) {
            Integer currentIdComposante = coursDto.getComposanteDto().getIdComposante();

            if (currentIdComposante == null || !currentIdComposante.equals(coursDto.getComposanteDto().getIdComposante())) {
                ComposanteEntity composanteEntity = composanteService.findComposanteEntityById(coursDto.getComposanteDto().getIdComposante());
                coursEntity.setComposante(composanteEntity);
            }
        }

        // Professeur
        if (coursDto.getProfesseurDto().getIdProf() != null) {
            Integer currentIdProfesseur = coursDto.getProfesseurDto().getIdProf();

            if (currentIdProfesseur == null || !currentIdProfesseur.equals(coursDto.getProfesseurDto().getIdProf())) {
                ProfesseurEntity professeurEntity = professeurService.findProfesseurEntityById(coursDto.getProfesseurDto().getIdProf());
                coursEntity.setProfesseur(professeurEntity);
            }
        }

        // Salle
        if (coursDto.getSalleDto().getIdSalle() != null) {
            Integer currentIdSalle = coursDto.getSalleDto().getIdSalle();

            if (currentIdSalle == null || !currentIdSalle.equals(coursDto.getSalleDto().getIdSalle())) {
                SalleEntity salleEntity = salleService.findSalleEntityById(coursDto.getSalleDto().getIdSalle());
                coursEntity.setSalle(salleEntity);
            }
        }

        CoursEntity updatedCours = coursRepository.save(coursEntity);

        return CoursMapper.toDto(updatedCours);
    }

    public void deleteCours(Integer id) {
        findCoursEntityById(id);
        coursRepository.deleteById(id);
    }
}
