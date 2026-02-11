package com.univtime.informatique.services;

import com.univtime.informatique.dto.professeurDto.ProfesseurDto;
import com.univtime.informatique.entities.ProfesseurEntity;
import com.univtime.informatique.exceptions.ResourceNotFoundException;
import com.univtime.informatique.mappers.ProfesseurMapper;
import com.univtime.informatique.repositories.ProfesseurRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProfesseurService {
    @Autowired
    private ProfesseurRepository professeurRepository;

    public List<ProfesseurDto> findAllProfesseurs() {
        List<ProfesseurEntity> professeurEntities = professeurRepository.findAll();

        return professeurEntities
                .stream()
                .map(ProfesseurMapper::toDto)
                .collect(Collectors.toList());
    }

    public ProfesseurDto findProfesseurDtoById(Integer id) {
        ProfesseurEntity professeurEntity = professeurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Le professeur avec l'id n'est pas trouvé : " + id));
        return ProfesseurMapper.toDto(professeurEntity);
    }

    public ProfesseurEntity findProfesseurEntityById(Integer id) {
        return professeurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Le professeur avec l'id n'est trouvé : " + id));
    }

    public ProfesseurDto createProfesseur(ProfesseurDto professeurDto) {
        ProfesseurEntity professeurEntity = ProfesseurMapper.toEntity(professeurDto);

        ProfesseurEntity savedProfesseur = professeurRepository.save(professeurEntity);

        return ProfesseurMapper.toDto(professeurEntity);
    }

    public ProfesseurDto updateProfesseur(ProfesseurDto professeurDto) {
        ProfesseurEntity professeurEntity = findProfesseurEntityById(professeurDto.getIdProf());

        ProfesseurMapper.toEntity(professeurDto);

        ProfesseurEntity updatedProfesseur = professeurRepository.save(professeurEntity);

        return ProfesseurMapper.toDto(updatedProfesseur);
    }

    public void deleteProfesseurById(Integer id) {
        findProfesseurEntityById(id);
        professeurRepository.deleteById(id);
    }
}
