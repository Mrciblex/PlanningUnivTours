package com.univtime.informatique.services;

import com.univtime.informatique.dto.tdDto.TDDto;
import com.univtime.informatique.entities.*;
import com.univtime.informatique.entities.ids.TDId;
import com.univtime.informatique.exceptions.ResourceNotFoundException;
import com.univtime.informatique.mappers.TDMapper;
import com.univtime.informatique.repositories.TDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TDService {
    @Autowired
    private TDRepository tdRepository;

    @Autowired
    private ProfesseurService professeurService;

    @Autowired
    private GroupeService groupeService;

    @Autowired
    private ComposanteService composanteService;

    @Autowired
    private RepartitionSemaineService repartitionSemaineService;

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
        if (tdDto.getProfesseurDto().getIdProf() == null) {
            throw new ResourceNotFoundException("L'id du professeur est obligatoire");
        }

        if (tdDto.getGroupeDto().getIdGroupe() == null) {
            throw new ResourceNotFoundException("L'id du groupe est obligatoire");
        }

        if (tdDto.getComposanteDto().getIdComposante() == null) {
            throw new ResourceNotFoundException("L'id de la composante est obligatoire");
        }

        if (tdDto.getRepartitionSemaineDto().getIdRepartitionSemaine() == null) {
            throw new ResourceNotFoundException("L'id de la repartition semaine est obligatoire");
        }

        TDEntity tdEntity = TDMapper.toEntity(tdDto);

        // Professeur
        Integer idProfesseurR = tdDto.getProfesseurDto().getIdProf();

        ProfesseurEntity professeurEntity = professeurService.findProfesseurEntityById(idProfesseurR);
        professeurEntity.setIdProf(professeurEntity.getIdProf());

        // Groupe
        Integer idGroupeR = tdDto.getGroupeDto().getIdGroupe();

        GroupeEntity groupeEntity = groupeService.findGroupeEntityById(idGroupeR);
        groupeEntity.setIdGroupe(groupeEntity.getIdGroupe());

        // Composante
        Integer idComposanteR = tdDto.getComposanteDto().getIdComposante();

        ComposanteEntity composanteEntity = composanteService.findComposanteEntityById(idComposanteR);
        composanteEntity.setIdComposante(composanteEntity.getIdComposante());

        // Repartition semaine
        Integer idRepartitionSemaineR = tdDto.getRepartitionSemaineDto().getIdRepartitionSemaine();

        RepartitionSemaineEntity repartitionSemaineEntity = repartitionSemaineService.findRepartitionSemaineEntityById(idRepartitionSemaineR);
        repartitionSemaineEntity.setIdRepartitionSemaine(repartitionSemaineEntity.getIdRepartitionSemaine());

        TDEntity savedTD = tdRepository.save(tdEntity);

        return TDMapper.toDto(savedTD);
    }

    public TDDto updateTD(TDDto tdDto) {
        TDEntity tdEntity = findTDEntityById(tdDto.getTDId());

        TDMapper.toEntity(tdDto);

        // Professeur
        if (tdDto.getProfesseurDto().getIdProf() != null) {
            Integer currentIdProfesseur = tdDto.getProfesseurDto().getIdProf();

            if (currentIdProfesseur == null || !currentIdProfesseur.equals(tdDto.getProfesseurDto().getIdProf())) {
                ProfesseurEntity professeurEntity = professeurService.findProfesseurEntityById(tdDto.getProfesseurDto().getIdProf());
                tdEntity.setProfesseur(professeurEntity);
            }
        }

        // Groupe
        if (tdDto.getGroupeDto().getIdGroupe() != null) {
            Integer currentIdGroupe = tdDto.getGroupeDto().getIdGroupe();

            if (currentIdGroupe == null || !currentIdGroupe.equals(tdDto.getGroupeDto().getIdGroupe())) {
                GroupeEntity groupeEntity = groupeService.findGroupeEntityById(tdDto.getGroupeDto().getIdGroupe());
                tdEntity.setGroupe(groupeEntity);
            }
        }

        // Composante
        if (tdDto.getComposanteDto().getIdComposante() != null) {
            Integer currentIdComposante = tdDto.getComposanteDto().getIdComposante();

            if (currentIdComposante == null || !currentIdComposante.equals(tdDto.getComposanteDto().getIdComposante())) {
                ComposanteEntity composanteEntity = composanteService.findComposanteEntityById(tdDto.getComposanteDto().getIdComposante());
                tdEntity.setComposante(composanteEntity);
            }
        }

        // Repartition Semaine
        if (tdDto.getRepartitionSemaineDto().getIdRepartitionSemaine() != null) {
            Integer currentIdRepartitionSemaine = tdDto.getRepartitionSemaineDto().getIdRepartitionSemaine();

            if (currentIdRepartitionSemaine == null || !currentIdRepartitionSemaine.equals(tdDto.getRepartitionSemaineDto().getIdRepartitionSemaine())) {
                RepartitionSemaineEntity repartitionSemaineEntity = repartitionSemaineService.findRepartitionSemaineEntityById(tdDto.getRepartitionSemaineDto().getIdRepartitionSemaine());
                tdEntity.setRepartitionSemaine(repartitionSemaineEntity);
            }
        }
        
        TDEntity savedTD = tdRepository.save(tdEntity);

        return TDMapper.toDto(savedTD);
    }

    public void deleteTDById(TDId tdId) {
        findTDEntityById(tdId);
        tdRepository.deleteById(tdId);
    }
}
