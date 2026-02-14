package com.univtime.informatique.services;

import com.univtime.informatique.dto.sousGroupeDto.SousGroupeDto;
import com.univtime.informatique.entities.SousGroupeEntity;
import com.univtime.informatique.entities.GroupeEntity;
import com.univtime.informatique.exceptions.ResourceNotFoundException;
import com.univtime.informatique.mappers.SousGroupeMapper;
import com.univtime.informatique.repositories.SousGroupeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SousGroupeService {
    private final SousGroupeRepository sousGroupeRepository;
    
    private final GroupeService groupeService;

    public SousGroupeService(SousGroupeRepository sousGroupeRepository,
                             GroupeService groupeService) {
        this.sousGroupeRepository = sousGroupeRepository;
        this.groupeService = groupeService;
    }

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

    public List<SousGroupeDto> findSousGroupesDtoByIdPromo(Integer idPromo) {
        List<SousGroupeEntity> sousGroupeEntity = sousGroupeRepository.findByIdPromo(idPromo);

        return sousGroupeEntity.stream()
                .map(SousGroupeMapper::toDto)
                .collect(Collectors.toList());
    }

    public SousGroupeEntity findSousGroupeEntityById(Integer id) {
        return sousGroupeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Le sous groupe avec l'id n'est trouvé : " + id));
    }

    public SousGroupeDto createSousGroupe(SousGroupeDto sousGroupeDto) {
        // Vérifie la clé étrangère de groupe
        if (sousGroupeDto.getGroupeDto() == null ||sousGroupeDto.getGroupeDto().getIdGroupe() == null) {
            throw new ResourceNotFoundException("L'id du groupe est obligatoire");
        }

        SousGroupeEntity sousGroupeEntity = SousGroupeMapper.toEntity(sousGroupeDto);

        Integer idGroupeR = sousGroupeDto.getGroupeDto().getIdGroupe();
        GroupeEntity groupeEntity = groupeService.findGroupeEntityById(idGroupeR);
        sousGroupeEntity.setGroupe(groupeEntity);

        SousGroupeEntity savedSousGroupe = sousGroupeRepository.save(sousGroupeEntity);

        return SousGroupeMapper.toDto(savedSousGroupe);
    }

    public SousGroupeDto updateSousGroupe(SousGroupeDto sousGroupeDto) {
        SousGroupeEntity sousGroupeEntity = findSousGroupeEntityById(sousGroupeDto.getIdSousGroupe());

        // SousGroupeMapper.toEntity(sousGroupeDto);
        sousGroupeEntity.setNomSousGroupe(sousGroupeDto.getNomSousGroupe());
        sousGroupeEntity.setNbEtuSousGroupe(sousGroupeDto.getNbEtuSousGroupe());

        if (sousGroupeDto.getGroupeDto() != null && sousGroupeDto.getGroupeDto().getIdGroupe() != null) {
            Integer newIdGroupe = sousGroupeDto.getGroupeDto().getIdGroupe();

            Integer currentIdGroupe = (sousGroupeEntity.getGroupe() != null) ?
                    sousGroupeEntity.getGroupe().getIdGroupe() : null;

            if (!newIdGroupe.equals(currentIdGroupe)) {
                GroupeEntity groupeEntity = groupeService.findGroupeEntityById(newIdGroupe);
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
