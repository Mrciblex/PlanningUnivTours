package com.univtime.informatique.services;

import com.univtime.informatique.dto.groupeDto.GroupeDto;
import com.univtime.informatique.entities.GroupeEntity;
import com.univtime.informatique.entities.PromoEntity;
import com.univtime.informatique.exceptions.ResourceNotFoundException;
import com.univtime.informatique.mappers.GroupeMapper;
import com.univtime.informatique.repositories.GroupeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GroupeService {
    private final GroupeRepository groupeRepository;

    private final PromoService promoService;

    public GroupeService(GroupeRepository groupeRepository,
                         PromoService promoService) {
        this.groupeRepository = groupeRepository;
        this.promoService = promoService;
    }

    public List<GroupeDto> findAllGroupe() {
        List<GroupeEntity> groupeEntities = groupeRepository.findAll();

        return groupeEntities
                .stream()
                .map(GroupeMapper::toDto)
                .collect(Collectors.toList());
    }

    public GroupeDto findGroupeDtoById(Integer id) {
        GroupeEntity groupeEntity = groupeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Le groupe avec l'id n'est pas trouvé : " + id));

        return GroupeMapper.toDto(groupeEntity);
    }

    public GroupeEntity findGroupeEntityById(Integer id) {
        return groupeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Le groupe avec l'id n'est trouvé : " + id));
    }

    public List<GroupeDto> findGroupeDtoByIdPromo(Integer idPromo) {

        return groupeRepository.findByPromo_IdPromo(idPromo)
                .stream()
                .map(GroupeMapper::toDto)
                .collect(Collectors.toList());
    }

    public GroupeDto createGroupe(GroupeDto groupeDto) {
        // Vérifie la clé étrangère de promo
        if (groupeDto.getPromoDto() == null || groupeDto.getPromoDto().getIdPromo() == null) {
            throw new ResourceNotFoundException("L'id de la promo est obligatoire");
        }

        GroupeEntity groupeEntity = GroupeMapper.toEntity(groupeDto);

        Integer idPromoR = groupeDto.getPromoDto().getIdPromo();
        PromoEntity promoEntity = promoService.findPromoEntityById(idPromoR);
        groupeEntity.setPromo(promoEntity);

        GroupeEntity savedGroupe = groupeRepository.save(groupeEntity);

        return GroupeMapper.toDto(savedGroupe);
    }

    public GroupeDto updateGroupe(GroupeDto groupeDto) {
        GroupeEntity groupeEntity = findGroupeEntityById(groupeDto.getIdGroupe());

        // GroupeMapper.toEntity(groupeDto);
        groupeEntity.setNomGroupe(groupeDto.getNomGroupe());
        groupeEntity.setNbEtuGroupe(groupeDto.getNbEtuGroupe());

        // Promo
        if (groupeDto.getPromoDto() != null && groupeDto.getPromoDto().getIdPromo() != null) {
            Integer newIdPromo = groupeDto.getPromoDto().getIdPromo();

            Integer currentIdPromo = (groupeEntity.getPromo() != null) ?
                    groupeEntity.getPromo().getIdPromo() : null;

            if (!newIdPromo.equals(currentIdPromo)) {
                PromoEntity promoEntity = promoService.findPromoEntityById(newIdPromo);
                promoEntity.setIdPromo(promoEntity.getIdPromo());
            }
        }

        GroupeEntity updatedGroupe = groupeRepository.save(groupeEntity);

        return GroupeMapper.toDto(updatedGroupe);
    }

    public void deleteGroupeById(Integer id) {
        findGroupeEntityById(id);
        groupeRepository.deleteById(id);
    }
}
