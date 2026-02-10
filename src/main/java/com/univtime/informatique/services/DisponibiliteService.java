package com.univtime.informatique.services;

import com.univtime.informatique.dto.disponibiliteDto.DisponibiliteDto;
import com.univtime.informatique.entities.DisponibiliteEntity;
import com.univtime.informatique.entities.JourEntity;
import com.univtime.informatique.exceptions.ResourceNotFoundException;
import com.univtime.informatique.mappers.DisponibiliteMapper;
import com.univtime.informatique.repositories.DisponibiliteRepository;
import com.univtime.informatique.repositories.JourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DisponibiliteService {
    @Autowired
    private DisponibiliteRepository disponibiliteRepository;

    @Autowired
    private JourRepository jourRepository;

    public List<DisponibiliteDto> findAllDisponibilites() {
        List<DisponibiliteEntity> disponibiliteEntities = disponibiliteRepository.findAll();

        return disponibiliteEntities
                .stream()
                .map(DisponibiliteMapper::toDto)
                .collect(Collectors.toList());
    }

    public DisponibiliteDto findDisponibiliteById(Integer id) {
        DisponibiliteEntity disponibiliteEntity = disponibiliteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La disponibilite avec l'id n'est trouvé : " + id));

        return DisponibiliteMapper.toDto(disponibiliteEntity);
    }

    public DisponibiliteDto createDisponibilite(DisponibiliteDto disponibiliteDto) {
        // Vérifie la clé étrangère de jour
        if (disponibiliteDto.getJourDto().getIdJour() == null) {
            throw new ResourceNotFoundException("L'id du jour est obligatoire");
        }

        DisponibiliteEntity disponibiliteEntity = DisponibiliteMapper.toEntity(disponibiliteDto);

        Integer idJourR = disponibiliteDto.getJourDto().getIdJour();

        JourEntity jourEntity = jourRepository.findById(idJourR)
                .orElseThrow(() -> new ResourceNotFoundException("Le jour avec l'id n'est pas trouvé : " + disponibiliteDto.getJourDto().getIdJour()));
        jourEntity.setIdJour(jourEntity.getIdJour());

        DisponibiliteEntity savedDisponibilite = disponibiliteRepository.save(disponibiliteEntity);

        return DisponibiliteMapper.toDto(savedDisponibilite);
    }

    public DisponibiliteDto updateDisponibilite(DisponibiliteDto disponibiliteDto) {
        DisponibiliteEntity disponibiliteEntity = disponibiliteRepository.findById(disponibiliteDto.getIdDispo())
                .orElseThrow(() -> new ResourceNotFoundException("La disponibilite avec l'id n'est pas trouvé : " + disponibiliteDto.getIdDispo()));

        DisponibiliteMapper.toEntity(disponibiliteDto);

        if (disponibiliteDto.getJourDto().getIdJour() != null) {
            Integer currentIdJour = Optional.ofNullable(disponibiliteEntity.getJour().getIdJour())
                    .map(JourEntity::getIdJour)
                    .orElse(null);

            if (currentIdJour == null || !currentIdJour.equals(disponibiliteDto.getJourDto().getIdJour())) {
                JourEntity jourEntity = jourRepository.findById(disponibiliteDto.getJourDto().getIdJour())
                        .orElseThrow(() -> new ResourceNotFoundException("Le jour avec l'id n'est pas trouvé : " + disponibiliteDto.getJourDto().getIdJour()));
                disponibiliteEntity.setJour(jourEntity);
            }
        }

        DisponibiliteEntity updatedDisponibilite = disponibiliteRepository.save(disponibiliteEntity);

        return DisponibiliteMapper.toDto(updatedDisponibilite);
    }
}
