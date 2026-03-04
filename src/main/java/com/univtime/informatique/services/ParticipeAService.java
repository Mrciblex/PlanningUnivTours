package com.univtime.informatique.services;

import com.univtime.informatique.dto.participeADto.ParticipeADto;
import com.univtime.informatique.entities.*;
import com.univtime.informatique.entities.ParticipeAEntity;
import com.univtime.informatique.entities.ids.ParticipeAId;
import com.univtime.informatique.exceptions.ResourceNotFoundException;
import com.univtime.informatique.mappers.ParticipeAMapper;
import com.univtime.informatique.repositories.ParticipeARepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ParticipeAService {
    private final ParticipeARepository participeARepository;

    private final SousGroupeService sousGroupeService;

    private final CoursService coursService;

    public ParticipeAService(ParticipeARepository participeARepository,
                             SousGroupeService sousGroupeService,
                             CoursService coursService) {
        this.participeARepository = participeARepository;
        this.sousGroupeService = sousGroupeService;
        this.coursService = coursService;
    }

    public List<ParticipeADto> findAllParticipeAs() {
        List<ParticipeAEntity> participeAEntities = participeARepository.findAll();

        return participeAEntities
                .stream()
                .map(ParticipeAMapper::toDto)
                .collect(Collectors.toList());
    }

    public ParticipeADto findParticipeADtoById(Integer idSousGroupe, Integer idCours) {
        ParticipeAId id = new ParticipeAId(
                idSousGroupe,
                idCours
        );
        ParticipeAEntity participeAEntity = participeARepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Le participe a avec l'id n'est trouvé : " + id));

        return ParticipeAMapper.toDto(participeAEntity);
    }

    public ParticipeADto findParticipeADtoById(ParticipeAId participeAId) {
        ParticipeAEntity participeAEntity = participeARepository.findById(participeAId)
                .orElseThrow(() -> new ResourceNotFoundException("Le participe a avec l'id n'existe pas : " + participeAId));

        return ParticipeAMapper.toDto(participeAEntity);
    }

    public ParticipeAEntity findParticipeAEntityById(Integer idSousGroupe, Integer idCours) {
        ParticipeAId id = new ParticipeAId(
            idSousGroupe,
            idCours
        );
        return participeARepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Le participe a avec l'id n'est trouvé : " + id));
    }

    public ParticipeAEntity findParticipeAEntityById(ParticipeAId participeAId) {
        return participeARepository.findById(participeAId)
                .orElseThrow(() -> new ResourceNotFoundException("Le participe a avec l'id n'existe pas : " + participeAId));
    }
    
    public ParticipeADto createParticipeA(ParticipeADto participeADto) {
        // Vérifie la clé étrangère de sousGroupe et cours
        if (participeADto.getSousGroupeDto() == null || participeADto.getSousGroupeDto().getIdSousGroupe() == null) {
            throw new ResourceNotFoundException("L'id du sous groupe est obligatoire");
        }

        if (participeADto.getCoursDto() == null || participeADto.getCoursDto().getIdCours() == null) {
            throw new ResourceNotFoundException("L'id du cours est obligatoire");
        }

        ParticipeAEntity participeAEntity = ParticipeAMapper.toEntity(participeADto);

        // SousGroupe
        Integer idSousGroupeR = participeADto.getSousGroupeDto().getIdSousGroupe();
        SousGroupeEntity sousGroupeEntity = sousGroupeService.findSousGroupeEntityById(idSousGroupeR);
        participeAEntity.setSousGroupe(sousGroupeEntity);

        // Cours
        Integer idCoursR = participeADto.getCoursDto().getIdCours();
        CoursEntity coursEntity = coursService.findCoursEntityById(idCoursR);
        participeAEntity.setCours(coursEntity);

        ParticipeAEntity savedParticipeA = participeARepository.save(participeAEntity);

        return ParticipeAMapper.toDto(savedParticipeA);
    }
    
    public ParticipeADto updateParticipeA(ParticipeAId oldParticipeAId, ParticipeADto newParticipeADto) {
        if (!oldParticipeAId.equals(newParticipeADto.getParticipeAId())){
            deleteParticipeAById(oldParticipeAId);
            return createParticipeA(newParticipeADto);
        }

        return newParticipeADto;
    }

    public void deleteParticipeAById(ParticipeAId participeAId) {
        findParticipeAEntityById(participeAId);
        participeARepository.deleteById(participeAId);
    }
}
