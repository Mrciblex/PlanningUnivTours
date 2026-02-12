package com.univtime.informatique.services;

import com.univtime.informatique.dto.sousGroupeDto.SousGroupeDto;
import com.univtime.informatique.entities.SousGroupeEntity;
import com.univtime.informatique.entities.GroupeEntity;
import com.univtime.informatique.exceptions.ResourceNotFoundException;
import com.univtime.informatique.mappers.SousGroupeMapper;
import com.univtime.informatique.repositories.SousGroupeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SousGroupeService {
    @Autowired
    private SousGroupeRepository sousGroupeRepository;
    
    @Autowired
    private GroupeService groupeService;

    public List<SousGroupeDto> findAllSousGroupes() {
        List<SousGroupeEntity> sousGroupeEntities = sousGroupeRepository.findAll();

        return sousGroupeEntities
                .stream()
                .map(SousGroupeMapper::toDto)
                .collect(Collectors.toList());
    }

    public SousGroupeDto findSousGroupeDtoById(Integer id) {
        SousGroupeEntity sousGroupeEntity = sousGroupeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Le sous groupe avec l'id n'est trouvé : " + id));

        return SousGroupeMapper.toDto(sousGroupeEntity);
    }

    public SousGroupeEntity findSousGroupeEntityById(Integer id) {
        return sousGroupeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Le sous groupe avec l'id n'est trouvé : " + id));
    }

    public SousGroupeDto createSousGroupe(SousGroupeDto sousGroupeDto) {
        // Vérifie la clé étrangère de groupe
        if (sousGroupeDto.getGroupeDto().getIdGroupe() == null) {
            throw new ResourceNotFoundException("L'id du groupe est obligatoire");
        }

        SousGroupeEntity sousGroupeEntity = SousGroupeMapper.toEntity(sousGroupeDto);

        Integer idGroupeR = sousGroupeDto.getGroupeDto().getIdGroupe();

        GroupeEntity groupeEntity = groupeService.findGroupeEntityById(idGroupeR);
        groupeEntity.setIdGroupe(groupeEntity.getIdGroupe());

        SousGroupeEntity savedSousGroupe = sousGroupeRepository.save(sousGroupeEntity);

        return SousGroupeMapper.toDto(savedSousGroupe);
    }

    public SousGroupeDto updateSousGroupe(SousGroupeDto sousGroupeDto) {
        SousGroupeEntity sousGroupeEntity = findSousGroupeEntityById(sousGroupeDto.getIdSousGroupe());

        SousGroupeMapper.toEntity(sousGroupeDto);

        if (sousGroupeDto.getGroupeDto().getIdGroupe() != null) {
            Integer currentIdGroupe = sousGroupeEntity.getGroupe().getIdGroupe();

            if (currentIdGroupe == null || !currentIdGroupe.equals(sousGroupeDto.getGroupeDto().getIdGroupe())) {
                GroupeEntity groupeEntity = groupeService.findGroupeEntityById(sousGroupeDto.getGroupeDto().getIdGroupe());
                sousGroupeEntity.setGroupe(groupeEntity);
            }
        }

        SousGroupeEntity updatedSousGroupe = sousGroupeRepository.save(sousGroupeEntity);

        return SousGroupeMapper.toDto(updatedSousGroupe);
    }

    public void deleteSousGroupeById(Integer id) {
        findSousGroupeEntityById(id);
        sousGroupeRepository.deleteById(id);
    }
}
