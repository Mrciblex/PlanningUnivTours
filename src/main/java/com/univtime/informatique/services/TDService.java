package com.univtime.informatique.services;

import com.univtime.informatique.dto.tdDto.TDDto;
import com.univtime.informatique.entities.*;
import com.univtime.informatique.entities.ids.TDId;
import com.univtime.informatique.exceptions.ResourceNotFoundException;
import com.univtime.informatique.mappers.TDMapper;
import com.univtime.informatique.repositories.RepartitionSemaineRepository;
import com.univtime.informatique.repositories.TDRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TDService {
    private final TDRepository tdRepository;

    private final ProfesseurService professeurService;

    private final GroupeService groupeService;

    private final ComposanteService composanteService;

    private final RepartitionSemaineService repartitionSemaineService;

    public TDService(TDRepository tdRepository,
                     ProfesseurService professeurService,
                     GroupeService groupeService,
                     ComposanteService composanteService,
                     RepartitionSemaineService repartitionSemaineService) {
        this.tdRepository = tdRepository;
        this.professeurService = professeurService;
        this.groupeService = groupeService;
        this.composanteService = composanteService;
        this.repartitionSemaineService = repartitionSemaineService;
    }

    public List<TDDto> findAllTDs() {
        List<TDEntity> tdEntities = tdRepository.findAll();

        return tdEntities
                .stream()
                .map(TDMapper::toDto)
                .collect(Collectors.toList());
    }

    public TDDto findTDDtoById(Integer idProfesseur, Integer idGroupe, Integer idComposante, Integer idRepartitionSemaine) {
        TDId id = new TDId(
                idProfesseur,
                idGroupe,
                idComposante,
                idRepartitionSemaine
        );
        TDEntity tdEntity = tdRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Le TD avec l'id n'est trouvé : " + id));

        return TDMapper.toDto(tdEntity);
    }

    public TDDto findTDDtoById(TDId tdId) {
        TDEntity tdEntity = tdRepository.findById(tdId)
                .orElseThrow(() -> new ResourceNotFoundException("Le TD avec l'id n'existe pas : " + tdId));

        return TDMapper.toDto(tdEntity);
    }

    public List<TDDto> findTDDtoByIdPromo(Integer idPromo) {
        List<TDEntity> tdEntity = tdRepository.findByIdPromo(idPromo);

        return tdEntity.stream()
                .map(TDMapper::toDto)
                .collect(Collectors.toList());
    }

    public TDEntity findTDEntityById(Integer idProfesseur, Integer idGroupe, Integer idComposante, Integer idRepartitionSemaine) {
        TDId id = new TDId(
                idProfesseur,
                idGroupe,
                idComposante,
                idRepartitionSemaine
        );
        return tdRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Le TD avec l'id n'est trouvé : " + id));
    }

    public TDEntity findTDEntityById(TDId tdId) {
        return tdRepository.findById(tdId)
                .orElseThrow(() -> new ResourceNotFoundException("Le TD avec l'id n'existe pas : " + tdId));
    }

    public TDDto createTD(TDDto tdDto) {
        // Vérifie la clé étrangère de professeur, groupe, composante et répartition semaine
        if (tdDto.getProfesseurDto() == null || tdDto.getProfesseurDto().getIdProf() == null) {
            throw new ResourceNotFoundException("L'id du professeur est obligatoire");
        }

        if (tdDto.getGroupeDto() == null || tdDto.getGroupeDto().getIdGroupe() == null) {
            throw new ResourceNotFoundException("L'id du groupe est obligatoire");
        }

        if (tdDto.getComposanteDto() == null || tdDto.getComposanteDto().getIdComposante() == null) {
            throw new ResourceNotFoundException("L'id de la composante est obligatoire");
        }

        if (tdDto.getRepartitionSemaineDto() == null || tdDto.getRepartitionSemaineDto().getIdRepartitionSemaine() == null) {
            throw new ResourceNotFoundException("L'id de la repartition semaine est obligatoire");
        }

        TDEntity tdEntity = TDMapper.toEntity(tdDto);

        // Professeur
        Integer idProfesseurR = tdDto.getProfesseurDto().getIdProf();
        ProfesseurEntity professeurEntity = professeurService.findProfesseurEntityById(idProfesseurR);
        tdEntity.setProfesseur(professeurEntity);

        // Groupe
        Integer idGroupeR = tdDto.getGroupeDto().getIdGroupe();
        GroupeEntity groupeEntity = groupeService.findGroupeEntityById(idGroupeR);
        tdEntity.setGroupe(groupeEntity);

        // Composante
        Integer idComposanteR = tdDto.getComposanteDto().getIdComposante();
        ComposanteEntity composanteEntity = composanteService.findComposanteEntityById(idComposanteR);
        tdEntity.setComposante(composanteEntity);

        // Repartition semaine
        Integer idRepartitionSemaineR = tdDto.getRepartitionSemaineDto().getIdRepartitionSemaine();
        RepartitionSemaineEntity repartitionSemaineEntity = repartitionSemaineService.findRepartitionSemaineEntityById(idRepartitionSemaineR);
        tdEntity.setRepartitionSemaine(repartitionSemaineEntity);

        TDEntity savedTD = tdRepository.save(tdEntity);

        return TDMapper.toDto(savedTD);
    }

    public TDDto updateTD(TDId oldTdId, TDDto newTdDto) {
        if (!oldTdId.equals(newTdDto.getTDId())){
            deleteTDById(oldTdId);
            return createTD(newTdDto);
        }

        return newTdDto;
    }

    public void deleteTDById(TDId tdId) {
        findTDEntityById(tdId);

        TDEntity td = findTDEntityById(tdId);
        Integer idRepartition = td.getRepartitionSemaine().getIdRepartitionSemaine();
        tdRepository.deleteById(tdId);
        repartitionSemaineService.deleteRepartitionSemaineById(idRepartition);
    }
}
