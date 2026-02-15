package com.univtime.informatique.services;

import com.univtime.informatique.dto.professeurDto.ProfesseurDto;
import com.univtime.informatique.entities.ProfesseurEntity;
import com.univtime.informatique.exceptions.ResourceNotFoundException;
import com.univtime.informatique.mappers.ProfesseurMapper;
import com.univtime.informatique.repositories.ProfesseurRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProfesseurService {
    private final ProfesseurRepository professeurRepository;

    public ProfesseurService(ProfesseurRepository professeurRepository) {
        this.professeurRepository = professeurRepository;
    }

    public List<ProfesseurDto> findAllProfesseurs() {
        List<ProfesseurEntity> professeurEntities = professeurRepository.findAll();

        return professeurEntities
                .stream()
                .map(ProfesseurMapper::toDto)
                .collect(Collectors.toList());
    }

    public ProfesseurDto findProfesseursDtoById(Integer id) {
        ProfesseurEntity professeurEntity = professeurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Le professeur avec l'id n'est pas trouvé : " + id));
        return ProfesseurMapper.toDto(professeurEntity);
    }

    public List<ProfesseurDto> findProfesseursDtoByIdPromo(Integer idPromo) {
        List<ProfesseurEntity> professeurEntity = professeurRepository.findByIdPromo(idPromo);
        return professeurEntity.stream()
                .map(ProfesseurMapper::toDto)
                .collect(Collectors.toList());
    }

    public ProfesseurEntity findProfesseurEntityById(Integer id) {
        return professeurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Le professeur avec l'id n'est trouvé : " + id));
    }

    public ProfesseurDto createProfesseur(ProfesseurDto professeurDto) {
        ProfesseurEntity professeurEntity = ProfesseurMapper.toEntity(professeurDto);

        ProfesseurEntity savedProfesseur = professeurRepository.save(professeurEntity);

        return ProfesseurMapper.toDto(savedProfesseur);
    }

    public ProfesseurDto updateProfesseur(ProfesseurDto professeurDto) {
        ProfesseurEntity professeurEntity = findProfesseurEntityById(professeurDto.getIdProf());

        // ProfesseurMapper.toEntity(professeurDto);
        professeurEntity.setNomProf(professeurDto.getNomProf());
        professeurEntity.setPrenomProf(professeurDto.getPrenomProf());
        professeurEntity.setIntervenantExterieur(professeurDto.isIntervenantExterieur());

        ProfesseurEntity updatedProfesseur = professeurRepository.save(professeurEntity);

        return ProfesseurMapper.toDto(updatedProfesseur);
    }

    public void deleteProfesseurById(Integer id) {
        findProfesseurEntityById(id);
        professeurRepository.deleteById(id);
    }
}
