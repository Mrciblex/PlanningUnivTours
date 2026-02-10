package com.univtime.informatique.services;

import com.univtime.informatique.dto.jourDto.JourDto;
import com.univtime.informatique.entities.JourEntity;
import com.univtime.informatique.entities.ProfesseurEntity;
import com.univtime.informatique.exceptions.ResourceNotFoundException;
import com.univtime.informatique.mappers.JourMapper;
import com.univtime.informatique.repositories.JourRepository;
import com.univtime.informatique.repositories.ProfesseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class JourService {
    @Autowired
    private JourRepository jourRepository;

    @Autowired
    private ProfesseurRepository professeurRepository;

    public List<JourDto> findAllJours() {
        List<JourEntity> jourEntities = jourRepository.findAll();

        return jourEntities
                .stream()
                .map(JourMapper::toDto)
                .collect(Collectors.toList());
    }

    public JourDto findJourById(Integer id) {
        JourEntity jourEntity = jourRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Le jour avec l'id n'est trouvé : " + id));

        return JourMapper.toDto(jourEntity);
    }

    public JourDto createJour(JourDto jourDto) {
        // Vérifie la clé étrangère de professeur
        if (jourDto.getProfesseurDto().getIdProf() == null) {
            throw new ResourceNotFoundException("L'id du professeur est obligatoire");
        }

        JourEntity jourEntity = JourMapper.toEntity(jourDto);

        Integer idProfesseurR = jourDto.getProfesseurDto().getIdProf();

        ProfesseurEntity professeurEntity = professeurRepository.findById(idProfesseurR)
                .orElseThrow(() -> new ResourceNotFoundException("Le professeur avec l'id n'est pas trouvé : " + jourDto.getProfesseurDto().getIdProf()));
        professeurEntity.setIdProf(professeurEntity.getIdProf());

        JourEntity savedJour = jourRepository.save(jourEntity);

        return JourMapper.toDto(savedJour);
    }

    public JourDto updateJour(JourDto jourDto) {
        JourEntity jourEntity = jourRepository.findById(jourDto.getIdJour())
                .orElseThrow(() -> new ResourceNotFoundException("Le jour avec l'id n'est pas trouvé : " + jourDto.getIdJour()));

        JourMapper.toEntity(jourDto);

        if (jourDto.getProfesseurDto().getIdProf() != null) {
            Integer currentIdProfesseur = Optional.ofNullable(jourEntity.getProfesseur().getIdProf())
                    .map(ProfesseurEntity::getIdProf)
                    .orElse(null);

            if (currentIdProfesseur == null || !currentIdProfesseur.equals(jourDto.getProfesseurDto().getIdProf())) {
                ProfesseurEntity professeurEntity = professeurRepository.findById(jourDto.getProfesseurDto().getIdProf())
                        .orElseThrow(() -> new ResourceNotFoundException("Le professeur avec l'id n'est pas trouvé : " + jourDto.getProfesseurDto().getIdProf()));
                jourEntity.setProfesseur(professeurEntity);
            }
        }

        JourEntity updatedJour = jourRepository.save(jourEntity);

        return JourMapper.toDto(updatedJour);
    }

    public void deleteJour(Integer id) {
        jourRepository.deleteById(id);
    }
}
