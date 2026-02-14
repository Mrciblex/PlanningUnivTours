package com.univtime.informatique.services;

import com.univtime.informatique.dto.cmDto.CMDto;
import com.univtime.informatique.entities.*;
import com.univtime.informatique.entities.ids.CMId;
import com.univtime.informatique.exceptions.ResourceNotFoundException;
import com.univtime.informatique.mappers.CMMapper;
import com.univtime.informatique.repositories.CMRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CMService {
    private final CMRepository cmRepository;

    private final ProfesseurService professeurService;

    private final PromoService promoService;

    private final ComposanteService composanteService;

    private final RepartitionSemaineService repartitionSemaineService;

    public CMService(CMRepository cmRepository,
                     ProfesseurService professeurService,
                     PromoService promoService,
                     ComposanteService composanteService,
                     RepartitionSemaineService repartitionSemaineService) {
        this.cmRepository = cmRepository;
        this.professeurService = professeurService;
        this.promoService = promoService;
        this.composanteService = composanteService;
        this.repartitionSemaineService = repartitionSemaineService;
    }

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

    public List<CMDto> findCMDtoByIdPromo(Integer idPromo) {
        List<CMEntity> cmEntity = cmRepository.findByIdPromo(idPromo);

        return cmEntity.stream()
                .map(CMMapper::toDto)
                .collect(Collectors.toList());
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
        if (cmDto.getProfesseurDto() == null || cmDto.getProfesseurDto().getIdProf() == null) {
            throw new ResourceNotFoundException("L'id du professeur est obligatoire");
        }

        if (cmDto.getPromoDto() == null || cmDto.getPromoDto().getIdPromo() == null) {
            throw new ResourceNotFoundException("L'id de la promo est obligatoire");
        }

        if (cmDto.getComposanteDto() == null || cmDto.getComposanteDto().getIdComposante() == null) {
            throw new ResourceNotFoundException("L'id de la composante est obligatoire");
        }

        if (cmDto.getRepartitionSemaineDto() == null || cmDto.getRepartitionSemaineDto().getIdRepartitionSemaine() == null) {
            throw new ResourceNotFoundException("L'id de la repartition semaine est obligatoire");
        }

        CMEntity cmEntity = CMMapper.toEntity(cmDto);

        // Professeur
        Integer idProfesseurR = cmDto.getProfesseurDto().getIdProf();
        ProfesseurEntity professeurEntity = professeurService.findProfesseurEntityById(idProfesseurR);
        cmEntity.setProfesseur(professeurEntity);

        // Promo
        Integer idPromoR = cmDto.getPromoDto().getIdPromo();
        PromoEntity promoEntity = promoService.findPromoEntityById(idPromoR);
        cmEntity.setPromo(promoEntity);

        // Composante
        Integer idComposanteR = cmDto.getComposanteDto().getIdComposante();
        ComposanteEntity composanteEntity = composanteService.findComposanteEntityById(idComposanteR);
        cmEntity.setComposante(composanteEntity);

        // Repartition semaine
        Integer idRepartitionSemaineR = cmDto.getRepartitionSemaineDto().getIdRepartitionSemaine();
        RepartitionSemaineEntity repartitionSemaineEntity = repartitionSemaineService.findRepartitionSemaineEntityById(idRepartitionSemaineR);
        cmEntity.setRepartitionSemaine(repartitionSemaineEntity);

        CMEntity savedCM = cmRepository.save(cmEntity);

        return CMMapper.toDto(savedCM);
    }

    public CMDto updateCM(CMId oldCmId, CMDto newCmDto) {
        if (!oldCmId.equals(newCmDto.getCMId())) {
            deleteCMById(oldCmId);
            return createCM(newCmDto);
        }

        return newCmDto;
    }

    public void deleteCMById(CMId cmId) {
        findCMEntityById(cmId);
        cmRepository.deleteById(cmId);
    }
}
