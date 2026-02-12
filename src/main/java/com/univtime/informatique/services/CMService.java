package com.univtime.informatique.services;

import com.univtime.informatique.dto.cmDto.CMDto;
import com.univtime.informatique.entities.*;
import com.univtime.informatique.entities.ids.CMId;
import com.univtime.informatique.exceptions.ResourceNotFoundException;
import com.univtime.informatique.mappers.CMMapper;
import com.univtime.informatique.repositories.CMRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CMService {
    @Autowired
    private CMRepository cmRepository;

    @Autowired
    private ProfesseurService professeurService;

    @Autowired
    private PromoService promoService;

    @Autowired
    private ComposanteService composanteService;

    @Autowired
    private RepartitionSemaineService repartitionSemaineService;

    public List<CMDto> findAllCMs() {
        List<CMEntity> cmEntities = cmRepository.findAll();

        return cmEntities
                .stream()
                .map(CMMapper::toDto)
                .collect(Collectors.toList());
    }

    public CMDto findCMDtoById(Integer idProfesseur, Integer idPromo, Integer idComposante, Integer idRepartitionSemaine) {
        CMId id = new CMId(
                idProfesseur,
                idPromo,
                idComposante,
                idRepartitionSemaine
        );
        CMEntity cmEntity = cmRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Le CM avec l'id n'est trouvé : " + id));

        return CMMapper.toDto(cmEntity);
    }

    public CMDto findCMDtoById(CMId cmId) {
        CMEntity cmEntity = cmRepository.findById(cmId)
                .orElseThrow(() -> new ResourceNotFoundException("Le CM avec l'id n'existe pas : " + cmId));

        return CMMapper.toDto(cmEntity);
    }

    public CMEntity findCMEntityById(Integer idProfesseur, Integer idPromo, Integer idComposante, Integer idRepartitionSemaine) {
        CMId id = new CMId(
                idProfesseur,
                idPromo,
                idComposante,
                idRepartitionSemaine
        );
        return cmRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Le CM avec l'id n'est trouvé : " + id));
    }

    public CMEntity findCMEntityById(CMId cmId) {
        return cmRepository.findById(cmId)
                .orElseThrow(() -> new ResourceNotFoundException("Le CM avec l'id n'existe pas : " + cmId));
    }

    public CMDto createCM(CMDto cmDto) {
        // Vérifie la clé étrangère de professeur, promo, composante et répartition semaine
        if (cmDto.getProfesseurDto().getIdProf() == null) {
            throw new ResourceNotFoundException("L'id du professeur est obligatoire");
        }

        if (cmDto.getPromoDto().getIdPromo() == null) {
            throw new ResourceNotFoundException("L'id de la promo est obligatoire");
        }

        if (cmDto.getComposanteDto().getIdComposante() == null) {
            throw new ResourceNotFoundException("L'id de la composante est obligatoire");
        }

        if (cmDto.getRepartitionSemaineDto().getIdRepartitionSemaine() == null) {
            throw new ResourceNotFoundException("L'id de la repartition semaine est obligatoire");
        }

        CMEntity cmEntity = CMMapper.toEntity(cmDto);

        // Professeur
        Integer idProfesseurR = cmDto.getProfesseurDto().getIdProf();

        ProfesseurEntity professeurEntity = professeurService.findProfesseurEntityById(idProfesseurR);
        professeurEntity.setIdProf(professeurEntity.getIdProf());

        // Promo
        Integer idPromoR = cmDto.getPromoDto().getIdPromo();

        PromoEntity promoEntity = promoService.findPromoEntityById(idPromoR);
        promoEntity.setIdPromo(promoEntity.getIdPromo());

        // Composante
        Integer idComposanteR = cmDto.getComposanteDto().getIdComposante();

        ComposanteEntity composanteEntity = composanteService.findComposanteEntityById(idComposanteR);
        composanteEntity.setIdComposante(composanteEntity.getIdComposante());

        // Repartition semaine
        Integer idRepartitionSemaineR = cmDto.getRepartitionSemaineDto().getIdRepartitionSemaine();

        RepartitionSemaineEntity repartitionSemaineEntity = repartitionSemaineService.findRepartitionSemaineEntityById(idRepartitionSemaineR);
        repartitionSemaineEntity.setIdRepartitionSemaine(repartitionSemaineEntity.getIdRepartitionSemaine());

        CMEntity savedCM = cmRepository.save(cmEntity);

        return CMMapper.toDto(savedCM);
    }

    public CMDto updateCM(CMDto cmDto) {
        CMEntity cmEntity = findCMEntityById(cmDto.getCMId());

        CMMapper.toEntity(cmDto);

        // Professeur
        if (cmDto.getProfesseurDto().getIdProf() != null) {
            Integer currentIdProfesseur = cmDto.getProfesseurDto().getIdProf();

            if (currentIdProfesseur == null || !currentIdProfesseur.equals(cmDto.getProfesseurDto().getIdProf())) {
                ProfesseurEntity professeurEntity = professeurService.findProfesseurEntityById(cmDto.getProfesseurDto().getIdProf());
                cmEntity.setProfesseur(professeurEntity);
            }
        }

        // Promo
        if (cmDto.getPromoDto().getIdPromo() != null) {
            Integer currentIdPromo = cmDto.getPromoDto().getIdPromo();

            if (currentIdPromo == null || !currentIdPromo.equals(cmDto.getPromoDto().getIdPromo())) {
                PromoEntity promoEntity = promoService.findPromoEntityById(cmDto.getPromoDto().getIdPromo());
                cmEntity.setPromo(promoEntity);
            }
        }

        // Composante
        if (cmDto.getComposanteDto().getIdComposante() != null) {
            Integer currentIdComposante = cmDto.getComposanteDto().getIdComposante();

            if (currentIdComposante == null || !currentIdComposante.equals(cmDto.getComposanteDto().getIdComposante())) {
                ComposanteEntity composanteEntity = composanteService.findComposanteEntityById(cmDto.getComposanteDto().getIdComposante());
                cmEntity.setComposante(composanteEntity);
            }
        }

        // Repartition Semaine
        if (cmDto.getRepartitionSemaineDto().getIdRepartitionSemaine() != null) {
            Integer currentIdRepartitionSemaine = cmDto.getRepartitionSemaineDto().getIdRepartitionSemaine();

            if (currentIdRepartitionSemaine == null || !currentIdRepartitionSemaine.equals(cmDto.getRepartitionSemaineDto().getIdRepartitionSemaine())) {
                RepartitionSemaineEntity repartitionSemaineEntity = repartitionSemaineService.findRepartitionSemaineEntityById(cmDto.getRepartitionSemaineDto().getIdRepartitionSemaine());
                cmEntity.setRepartitionSemaine(repartitionSemaineEntity);
            }
        }

        CMEntity updatedCM = cmRepository.save(cmEntity);

        return CMMapper.toDto(updatedCM);
    }

    public void deleteCMById(CMId cmId) {
        findCMEntityById(cmId);
        cmRepository.deleteById(cmId);
    }
}
