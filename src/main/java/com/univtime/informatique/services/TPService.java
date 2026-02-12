package com.univtime.informatique.services;

import com.univtime.informatique.dto.tpDto.TPDto;
import com.univtime.informatique.entities.*;
import com.univtime.informatique.entities.ids.TPId;
import com.univtime.informatique.exceptions.ResourceNotFoundException;
import com.univtime.informatique.mappers.TPMapper;
import com.univtime.informatique.repositories.TDRepository;
import com.univtime.informatique.repositories.TPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TPService {
    @Autowired
    private TPRepository tpRepository;

    @Autowired
    private TDRepository tdRepository;

    @Autowired
    private ProfesseurService professeurService;

    @Autowired
    private SousGroupeService sousGroupeService;

    @Autowired
    private ComposanteService composanteService;

    @Autowired
    private RepartitionSemaineService repartitionSemaineService;

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
        if (tpDto.getProfesseurDto().getIdProf() == null) {
            throw new ResourceNotFoundException("L'id du professeur est obligatoire");
        }

        if (tpDto.getSousGroupeDto().getIdSousGroupe() == null) {
            throw new ResourceNotFoundException("L'id du sous Groupe est obligatoire");
        }

        if (tpDto.getComposanteDto().getIdComposante() == null) {
            throw new ResourceNotFoundException("L'id de la composante est obligatoire");
        }

        if (tpDto.getRepartitionSemaineDto().getIdRepartitionSemaine() == null) {
            throw new ResourceNotFoundException("L'id de la repartition semaine est obligatoire");
        }
        
        TPEntity tpEntity = TPMapper.toEntity(tpDto);

        // Professeur
        Integer idProfesseurR = tpDto.getProfesseurDto().getIdProf();

        ProfesseurEntity professeurEntity = professeurService.findProfesseurEntityById(idProfesseurR);
        professeurEntity.setIdProf(professeurEntity.getIdProf());

        // Sous Groupe
        Integer idSousGroupeR = tpDto.getSousGroupeDto().getIdSousGroupe();

        SousGroupeEntity sousGroupeEntity = sousGroupeService.findSousGroupeEntityById(idSousGroupeR);
        sousGroupeEntity.setIdSousGroupe(sousGroupeEntity.getIdSousGroupe());

        // Composante
        Integer idComposanteR = tpDto.getComposanteDto().getIdComposante();

        ComposanteEntity composanteEntity = composanteService.findComposanteEntityById(idComposanteR);
        composanteEntity.setIdComposante(composanteEntity.getIdComposante());

        // Repartition semaine
        Integer idRepartitionSemaineR = tpDto.getRepartitionSemaineDto().getIdRepartitionSemaine();

        RepartitionSemaineEntity repartitionSemaineEntity = repartitionSemaineService.findRepartitionSemaineEntityById(idRepartitionSemaineR);
        repartitionSemaineEntity.setIdRepartitionSemaine(repartitionSemaineEntity.getIdRepartitionSemaine());

        TPEntity savedTP = tpRepository.save(tpEntity);

        return TPMapper.toDto(savedTP);
    }

    public TPDto updateTP(TPDto tpDto) {
        TPEntity tpEntity = findTPEntityById(tpDto.getTPId());
        
        TPMapper.toEntity(tpDto);

        // Professeur
        if (tpDto.getProfesseurDto().getIdProf() != null) {
            Integer currentIdProfesseur = tpDto.getProfesseurDto().getIdProf();

            if (currentIdProfesseur == null || !currentIdProfesseur.equals(tpDto.getProfesseurDto().getIdProf())) {
                ProfesseurEntity professeurEntity = professeurService.findProfesseurEntityById(tpDto.getProfesseurDto().getIdProf());
                tpEntity.setProfesseur(professeurEntity);
            }
        }

        // Sous Groupe
        if (tpDto.getSousGroupeDto().getIdSousGroupe() != null) {
            Integer currentIdSousGroupe = tpDto.getSousGroupeDto().getIdSousGroupe();

            if (currentIdSousGroupe == null || !currentIdSousGroupe.equals(tpDto.getSousGroupeDto().getIdSousGroupe())) {
                SousGroupeEntity sousSousGroupeEntity = sousGroupeService.findSousGroupeEntityById(tpDto.getSousGroupeDto().getIdSousGroupe());
                tpEntity.setSousGroupe(sousSousGroupeEntity);
            }
        }

        // Composante
        if (tpDto.getComposanteDto().getIdComposante() != null) {
            Integer currentIdComposante = tpDto.getComposanteDto().getIdComposante();

            if (currentIdComposante == null || !currentIdComposante.equals(tpDto.getComposanteDto().getIdComposante())) {
                ComposanteEntity composanteEntity = composanteService.findComposanteEntityById(tpDto.getComposanteDto().getIdComposante());
                tpEntity.setComposante(composanteEntity);
            }
        }

        // Repartition Semaine
        if (tpDto.getRepartitionSemaineDto().getIdRepartitionSemaine() != null) {
            Integer currentIdRepartitionSemaine = tpDto.getRepartitionSemaineDto().getIdRepartitionSemaine();

            if (currentIdRepartitionSemaine == null || !currentIdRepartitionSemaine.equals(tpDto.getRepartitionSemaineDto().getIdRepartitionSemaine())) {
                RepartitionSemaineEntity repartitionSemaineEntity = repartitionSemaineService.findRepartitionSemaineEntityById(tpDto.getRepartitionSemaineDto().getIdRepartitionSemaine());
                tpEntity.setRepartitionSemaine(repartitionSemaineEntity);
            }
        }
        
        TPEntity savedTP = tpRepository.save(tpEntity);
        
        return TPMapper.toDto(savedTP);    
    }

    public void deleteTPById(TPId tpId) {
        findTPEntityById(tpId);
        tpRepository.deleteById(tpId);
    }
}
