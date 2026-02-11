package com.univtime.informatique.services;

import com.univtime.informatique.dto.disponibiliteDto.DisponibiliteDto;
import com.univtime.informatique.entities.DisponibiliteEntity;
import com.univtime.informatique.entities.JourEntity;
import com.univtime.informatique.exceptions.ResourceNotFoundException;
import com.univtime.informatique.mappers.DisponibiliteMapper;
import com.univtime.informatique.repositories.DisponibiliteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DisponibiliteService {
    @Autowired
    private DisponibiliteRepository disponibiliteRepository;

    @Autowired
    private JourService jourService;

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
        if (disponibiliteDto.getJourDto().getIdJour() == null) {
            throw new ResourceNotFoundException("L'id du jour est obligatoire");
        }

        DisponibiliteEntity disponibiliteEntity = DisponibiliteMapper.toEntity(disponibiliteDto);

        Integer idJourR = disponibiliteDto.getJourDto().getIdJour();

        JourEntity jourEntity = jourService.findJourEntityById(idJourR);
        jourEntity.setIdJour(jourEntity.getIdJour());

        DisponibiliteEntity savedDisponibilite = disponibiliteRepository.save(disponibiliteEntity);

        return DisponibiliteMapper.toDto(savedDisponibilite);
    }

    public DisponibiliteDto updateDisponibilite(DisponibiliteDto disponibiliteDto) {
        DisponibiliteEntity disponibiliteEntity = findDisponibiliteEntityById(disponibiliteDto.getIdDispo());
        DisponibiliteMapper.toEntity(disponibiliteDto);

        if (disponibiliteDto.getJourDto().getIdJour() != null) {
            Integer currentIdJour = disponibiliteEntity.getJour().getIdJour();

            if (currentIdJour == null || !currentIdJour.equals(disponibiliteDto.getJourDto().getIdJour())) {
                JourEntity jourEntity = jourService.findJourEntityById(disponibiliteDto.getJourDto().getIdJour());
                disponibiliteEntity.setJour(jourEntity);
            }
        }

        DisponibiliteEntity updatedDisponibilite = disponibiliteRepository.save(disponibiliteEntity);

        return DisponibiliteMapper.toDto(updatedDisponibilite);
    }

    public void deleteDisponibilite(Integer id) {
        findDisponibiliteEntityById(id);
        disponibiliteRepository.deleteById(id);
    }
}
