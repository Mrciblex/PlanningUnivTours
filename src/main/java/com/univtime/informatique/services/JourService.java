/*
 * Copyright (c) 2026 Ademi Musa. Tous droits réservés.
 * Projet : univTime - Logiciel de gestion d'emplois du temps.
 *
 * Ce code source et l'algorithme associé sont la propriété exclusive de l'auteur.
 * Toute reproduction, modification ou distribution non autorisée, par quelque moyen que ce soit, est strictement interdite.
 *
 * Ce fichier fait partie du projet univTime, concédé sous licence d'usage
 * restreinte à l'Université de Tours, 37000, en France.
 */

package com.univtime.informatique.services;

import com.univtime.informatique.dto.jourDto.JourDto;
import com.univtime.informatique.entities.JourEntity;
import com.univtime.informatique.entities.ProfesseurEntity;
import com.univtime.informatique.exceptions.ResourceNotFoundException;
import com.univtime.informatique.mappers.JourMapper;
import com.univtime.informatique.repositories.JourRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class JourService {
    private final JourRepository jourRepository;

    private final ProfesseurService professeurService;

    public JourService(JourRepository jourRepository,
                       ProfesseurService professeurService) {
        this.jourRepository = jourRepository;
        this.professeurService = professeurService;
    }

    public List<JourDto> findAllJours() {
        List<JourEntity> jourEntities = jourRepository.findAll();

        return jourEntities
                .stream()
                .map(JourMapper::toDto)
                .collect(Collectors.toList());
    }

    public JourDto findJourDtoById(Integer id) {
        JourEntity jourEntity = jourRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Le jour avec l'id n'est trouvé : " + id));

        return JourMapper.toDto(jourEntity);
    }

    public List<JourDto> findJoursDtoByIdProf(Integer idProf) {
        List<JourEntity> jourEntities = jourRepository.findByProfesseurIdProf(idProf);

        return jourEntities
                .stream()
                .map(JourMapper::toDto)
                .collect(Collectors.toList());
    }

    public JourEntity findJourEntityById(Integer id) {
        return jourRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Le jour avec l'id n'est trouvé : " + id));
    }

    public JourDto createJour(JourDto jourDto) {
        // Vérifie la clé étrangère de professeur
        if (jourDto.getProfesseurDto() == null || jourDto.getProfesseurDto().getIdProf() == null) {
            throw new ResourceNotFoundException("L'id du professeur est obligatoire");
        }

        JourEntity jourEntity = JourMapper.toEntity(jourDto);

        Integer idProfesseurR = jourDto.getProfesseurDto().getIdProf();
        ProfesseurEntity professeurEntity = professeurService.findProfesseurEntityById(idProfesseurR);
        jourEntity.setProfesseur(professeurEntity);

        JourEntity savedJour = jourRepository.save(jourEntity);

        return JourMapper.toDto(savedJour);
    }

    public JourDto updateJour(JourDto jourDto) {
        JourEntity jourEntity = findJourEntityById(jourDto.getIdJour());

        // JourMapper.toEntity(jourDto);
        jourEntity.setJourSemaine(jourDto.getJourSemaine());

        // Professeur
        if (jourDto.getProfesseurDto() != null && jourDto.getProfesseurDto().getIdProf() != null) {
            Integer newIdProfesseur = jourDto.getProfesseurDto().getIdProf();

            Integer currentIdProfesseur = (jourEntity.getProfesseur() != null) ?
                    jourEntity.getProfesseur().getIdProf() : null;

            if (!newIdProfesseur.equals(currentIdProfesseur)) {
                ProfesseurEntity professeurEntity = professeurService.findProfesseurEntityById(newIdProfesseur);
                jourEntity.setProfesseur(professeurEntity);
            }
        }

        JourEntity updatedJour = jourRepository.save(jourEntity);

        return JourMapper.toDto(updatedJour);
    }

    public void deleteJourById(Integer id) {
        findJourEntityById(id);
        jourRepository.deleteById(id);
    }
}
