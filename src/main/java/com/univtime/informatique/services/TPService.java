package com.univtime.informatique.services;

import com.univtime.informatique.dto.tpDto.TPDto;
import com.univtime.informatique.entities.*;
import com.univtime.informatique.entities.ids.TPId;
import com.univtime.informatique.exceptions.ResourceNotFoundException;
import com.univtime.informatique.mappers.TPMapper;
import com.univtime.informatique.repositories.TPRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TPService {
    private final TPRepository tpRepository;

    private final ProfesseurService professeurService;

    private final SousGroupeService sousGroupeService;

    private final ComposanteService composanteService;

    private final RepartitionSemaineService repartitionSemaineService;

    public TPService(TPRepository tpRepository,
                     ProfesseurService professeurService,
                     SousGroupeService sousGroupeService,
                     ComposanteService composanteService,
                     RepartitionSemaineService repartitionSemaineService) {
        this.tpRepository = tpRepository;
        this.professeurService = professeurService;
        this.sousGroupeService = sousGroupeService;
        this.composanteService = composanteService;
        this.repartitionSemaineService = repartitionSemaineService;
    }

    public List<TPDto> findAllTPs() {
        List<TPEntity> tpEntities = tpRepository.findAll();

        return tpEntities
                .stream()
                .map(TPMapper::toDto)
                .collect(Collectors.toList());
    }

    public TPDto findTPDtoById(Integer idProfesseur, Integer idSousGroupe, Integer idComposante, Integer idRepartitionSemaine) {
        TPId id = new TPId(
                idProfesseur,
                idSousGroupe,
                idComposante,
                idRepartitionSemaine
        );
        TPEntity tpEntity = tpRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Le TP avec l'id n'est trouvé : " + id));

        return TPMapper.toDto(tpEntity);
    }

    public TPDto findTPDtoById(TPId tpId) {
        TPEntity tpEntity = tpRepository.findById(tpId)
                .orElseThrow(() -> new ResourceNotFoundException("Le TP avec l'id n'existe pas : " + tpId));

        return TPMapper.toDto(tpEntity);
    }

    public List<TPDto> findTPDtoByIdPromo(Integer idPromo) {
        List<TPEntity> tpEntity = tpRepository.findByIdPromo(idPromo);

        return tpEntity.stream()
                .map(TPMapper::toDto)
                .collect(Collectors.toList());
    }

    public TPEntity findTPEntityById(Integer idProfesseur, Integer idSousGroupe, Integer idComposante, Integer idRepartitionSemaine) {
        TPId id = new TPId(
                idProfesseur,
                idSousGroupe,
                idComposante,
                idRepartitionSemaine
        );
        return tpRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Le TP avec l'id n'est trouvé : " + id));
    }

    public TPEntity findTPEntityById(TPId tpId) {
        return tpRepository.findById(tpId)
                .orElseThrow(() -> new ResourceNotFoundException("Le TP avec l'id n'existe pas : " + tpId));
    }

    public TPDto createTP(TPDto tpDto) {
        // Vérifie la clé étrangère de professeur, sous Groupe, composante et repartition semaine
        if (tpDto.getProfesseurDto() == null || tpDto.getProfesseurDto().getIdProf() == null) {
            throw new ResourceNotFoundException("L'id du professeur est obligatoire");
        }

        if (tpDto.getSousGroupeDto() == null || tpDto.getSousGroupeDto().getIdSousGroupe() == null) {
            throw new ResourceNotFoundException("L'id du sous Groupe est obligatoire");
        }

        if (tpDto.getComposanteDto() == null || tpDto.getComposanteDto().getIdComposante() == null) {
            throw new ResourceNotFoundException("L'id de la composante est obligatoire");
        }

        if (tpDto.getRepartitionSemaineDto() == null || tpDto.getRepartitionSemaineDto().getIdRepartitionSemaine() == null) {
            throw new ResourceNotFoundException("L'id de la repartition semaine est obligatoire");
        }
        
        TPEntity tpEntity = TPMapper.toEntity(tpDto);

        // Professeur
        Integer idProfesseurR = tpDto.getProfesseurDto().getIdProf();
        ProfesseurEntity professeurEntity = professeurService.findProfesseurEntityById(idProfesseurR);
        tpEntity.setProfesseur(professeurEntity);

        // Sous Groupe
        Integer idSousGroupeR = tpDto.getSousGroupeDto().getIdSousGroupe();
        SousGroupeEntity sousGroupeEntity = sousGroupeService.findSousGroupeEntityById(idSousGroupeR);
        tpEntity.setSousGroupe(sousGroupeEntity);

        // Composante
        Integer idComposanteR = tpDto.getComposanteDto().getIdComposante();
        ComposanteEntity composanteEntity = composanteService.findComposanteEntityById(idComposanteR);
        tpEntity.setComposante(composanteEntity);

        // Repartition semaine
        Integer idRepartitionSemaineR = tpDto.getRepartitionSemaineDto().getIdRepartitionSemaine();
        RepartitionSemaineEntity repartitionSemaineEntity = repartitionSemaineService.findRepartitionSemaineEntityById(idRepartitionSemaineR);
        tpEntity.setRepartitionSemaine(repartitionSemaineEntity);

        TPEntity savedTP = tpRepository.save(tpEntity);

        return TPMapper.toDto(savedTP);
    }

    public TPDto updateTP(TPId oldTpId, TPDto newTpDto) {
        if (!oldTpId.equals(newTpDto.getTPId())){
            deleteTPById(oldTpId);
            return createTP(newTpDto);
        }

        return newTpDto;
    }

    public void deleteTPById(TPId tpId) {
        findTPEntityById(tpId);
        tpRepository.deleteById(tpId);
    }
}
