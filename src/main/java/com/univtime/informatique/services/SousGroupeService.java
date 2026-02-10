package com.univtime.informatique.services;

import com.univtime.informatique.dto.sousGroupeDto.SousGroupeDto;
import com.univtime.informatique.entities.SousGroupeEntity;
import com.univtime.informatique.entities.GroupeEntity;
import com.univtime.informatique.exceptions.ResourceNotFoundException;
import com.univtime.informatique.mappers.SousGroupeMapper;
import com.univtime.informatique.repositories.GroupeRepository;
import com.univtime.informatique.repositories.SousGroupeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class SousGroupeService {
    @Autowired
    private SousGroupeRepository sousGroupeRepository;
    
    @Autowired
    private GroupeRepository groupeRepository;

    public List<SousGroupeDto> findAllSousGroupes() {
        List<SousGroupeEntity> sousGroupeEntities = sousGroupeRepository.findAll();

        return sousGroupeEntities
                .stream()
                .map(SousGroupeMapper::toDto)
                .collect(Collectors.toList());
    }

    public SousGroupeDto findSousGroupeById(Integer id) {
        SousGroupeEntity sousGroupeEntity = sousGroupeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Le sous groupe avec l'id n'est trouvé : " + id));

        return SousGroupeMapper.toDto(sousGroupeEntity);
    }

    public SousGroupeDto createSousGroupe(SousGroupeDto sousGroupeDto) {
        // Vérifie la clé étrangère de groupe
        if (sousGroupeDto.getGroupeDto().getIdGroupe() == null) {
            throw new ResourceNotFoundException("L'id du groupe est obligatoire");
        }

        SousGroupeEntity sousGroupeEntity = SousGroupeMapper.toEntity(sousGroupeDto);

        Integer idGroupeR = sousGroupeDto.getGroupeDto().getIdGroupe();

        GroupeEntity groupeEntity = groupeRepository.findById(idGroupeR)
                .orElseThrow(() -> new ResourceNotFoundException("Le groupe avec l'id n'est pas trouvé : " + sousGroupeDto.getGroupeDto().getIdGroupe()));
        groupeEntity.setIdGroupe(groupeEntity.getIdGroupe());

        SousGroupeEntity savedSousGroupe = sousGroupeRepository.save(sousGroupeEntity);

        return SousGroupeMapper.toDto(savedSousGroupe);
    }

    public SousGroupeDto updateSousGroupe(SousGroupeDto sousGroupeDto) {
        SousGroupeEntity sousGroupeEntity = sousGroupeRepository.findById(sousGroupeDto.getIdSousGroupe())
                .orElseThrow(() -> new ResourceNotFoundException("Le sous groupe avec l'id n'est pas trouvé : " + sousGroupeDto.getIdSousGroupe()));

        SousGroupeMapper.toEntity(sousGroupeDto);

        if (sousGroupeDto.getGroupeDto().getIdGroupe() != null) {
            Integer currentIdGroupe = Optional.ofNullable(sousGroupeEntity.getGroupe().getIdGroupe())
                    .map(GroupeEntity::getIdGroupe)
                    .orElse(null);

            if (currentIdGroupe == null || !currentIdGroupe.equals(sousGroupeDto.getGroupeDto().getIdGroupe())) {
                GroupeEntity groupeEntity = groupeRepository.findById(sousGroupeDto.getGroupeDto().getIdGroupe())
                        .orElseThrow(() -> new ResourceNotFoundException("Le groupe avec l'id n'est pas trouvé : " + sousGroupeDto.getGroupeDto().getIdGroupe()));
                sousGroupeEntity.setGroupe(groupeEntity);
            }
        }

        SousGroupeEntity updatedSousGroupe = sousGroupeRepository.save(sousGroupeEntity);

        return SousGroupeMapper.toDto(updatedSousGroupe);
    }

    public void deleteSousGroupe(Integer id) {
        sousGroupeRepository.deleteById(id);
    }
}
