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

import com.univtime.informatique.dto.disponibiliteDto.DisponibiliteDto;
import com.univtime.informatique.entities.DisponibiliteEntity;
import com.univtime.informatique.entities.JourEntity;
import com.univtime.informatique.exceptions.ResourceNotFoundException;
import com.univtime.informatique.mappers.DisponibiliteMapper;
import com.univtime.informatique.repositories.DisponibiliteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DisponibiliteService {
    private final DisponibiliteRepository disponibiliteRepository;

    private final JourService jourService;

    public DisponibiliteService(DisponibiliteRepository disponibiliteRepository,
                                JourService jourService) {
        this.disponibiliteRepository = disponibiliteRepository;
        this.jourService = jourService;
    }

    public List<DisponibiliteDto> findAllDisponibilites() {
        List<DisponibiliteEntity> disponibiliteEntities = disponibiliteRepository.findAll();

        return disponibiliteEntities
                .stream()
                .map(DisponibiliteMapper::toDto)
                .collect(Collectors.toList());
    }

    public DisponibiliteDto findDisponibiliteDtoById(Integer id) {
        DisponibiliteEntity disponibiliteEntity = disponibiliteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La disponibilite avec l'id n'est trouvé : " + id));

        return DisponibiliteMapper.toDto(disponibiliteEntity);
    }

    public DisponibiliteEntity findDisponibiliteEntityById(Integer id) {
        return disponibiliteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La disponibilite avec l'id n'est trouvé : " + id));
    }

    public DisponibiliteDto createDisponibilite(DisponibiliteDto disponibiliteDto) {
        // Vérifie la clé étrangère de jour
        if (disponibiliteDto.getJourDto() == null || disponibiliteDto.getJourDto().getIdJour() == null) {
            throw new ResourceNotFoundException("L'id du jour est obligatoire");
        }

        DisponibiliteEntity disponibiliteEntity = DisponibiliteMapper.toEntity(disponibiliteDto);

        Integer idJourR = disponibiliteDto.getJourDto().getIdJour();
        JourEntity jourEntity = jourService.findJourEntityById(idJourR);
        disponibiliteEntity.setJour(jourEntity);

        DisponibiliteEntity savedDisponibilite = disponibiliteRepository.save(disponibiliteEntity);

        return DisponibiliteMapper.toDto(savedDisponibilite);
    }

    public DisponibiliteDto updateDisponibilite(DisponibiliteDto disponibiliteDto) {
        DisponibiliteEntity disponibiliteEntity = findDisponibiliteEntityById(disponibiliteDto.getIdDispo());

        // DisponibiliteMapper.toEntity(disponibiliteDto);
        disponibiliteEntity.setHeureDebutDispo(disponibiliteDto.getHeureDebutDispo());
        disponibiliteEntity.setHeureFinDispo(disponibiliteDto.getHeureFinDispo());


        // Jour
        if (disponibiliteDto.getJourDto() != null && disponibiliteDto.getJourDto().getIdJour() != null) {
            Integer newIdJour = disponibiliteDto.getJourDto().getIdJour();
            Integer currentIdJour = (disponibiliteEntity.getJour() != null) ?
                    disponibiliteEntity.getJour().getIdJour() : null;

            if (!newIdJour.equals(currentIdJour)) {
                JourEntity jourEntity = jourService.findJourEntityById(newIdJour);
                disponibiliteEntity.setJour(jourEntity);
            }
        }

        DisponibiliteEntity updatedDisponibilite = disponibiliteRepository.save(disponibiliteEntity);

        return DisponibiliteMapper.toDto(updatedDisponibilite);
    }

    public void deleteDisponibiliteById(Integer id) {
        findDisponibiliteEntityById(id);
        disponibiliteRepository.deleteById(id);
    }
}
