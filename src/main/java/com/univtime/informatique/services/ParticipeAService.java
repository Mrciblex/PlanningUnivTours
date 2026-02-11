package com.univtime.informatique.services;

import com.univtime.informatique.dto.participeADto.ParticipeADto;
import com.univtime.informatique.entities.ParticipeAEntity;
import com.univtime.informatique.exceptions.ResourceNotFoundException;
import com.univtime.informatique.mappers.ParticipeAMapper;
import com.univtime.informatique.repositories.ParticipeARepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ParticipeAService {
    @Autowired
    private ParticipeARepository participeARepository;

    public List<ParticipeADto> findAllParticipeAs() {
        List<ParticipeAEntity> participeAEntities = participeARepository.findAll();

        return participeAEntities
                .stream()
                .map(ParticipeAMapper::toDto)
                .collect(Collectors.toList());
    }

    public ParticipeADto findParticipeADtoById(Integer id) {
        ParticipeAEntity participeAEntity = participeARepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Le participe a avec l'id n'est trouvé : " + id));

        return ParticipeAMapper.toDto(participeAEntity);
    }

    public ParticipeAEntity findParticipeAEntityById(Integer id) {
        return participeARepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Le participe a avec l'id n'est trouvé : " + id));
    }

    public void deleteParticipeAById(Integer id) {
        findParticipeAEntityById(id);
        participeARepository.deleteById(id);
    }
}
