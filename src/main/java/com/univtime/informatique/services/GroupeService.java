package com.univtime.informatique.services;

import com.univtime.informatique.dto.groupeDto.GroupeDto;
import com.univtime.informatique.entities.GroupeEntity;
import com.univtime.informatique.entities.PromoEntity;
import com.univtime.informatique.exceptions.ResourceNotFoundException;
import com.univtime.informatique.mappers.GroupeMapper;
import com.univtime.informatique.repositories.GroupeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GroupeService {
    @Autowired
    private GroupeRepository groupeRepository;

    @Autowired
    private PromoService promoService;

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

    public GroupeDto createGroupe(GroupeDto groupeDto) {
        // Vérifie la clé étrangère de promo
        if (groupeDto.getPromoDto().getIdPromo() == null) {
            throw new ResourceNotFoundException("L'id de la promo est obligatoire");
        }

        GroupeEntity groupeEntity = GroupeMapper.toEntity(groupeDto);

        Integer idPromoR = groupeDto.getPromoDto().getIdPromo();

        PromoEntity promoEntity = promoService.findPromoEntityById(idPromoR);
        promoEntity.setIdPromo(promoEntity.getIdPromo());

        GroupeEntity savedGroupe = groupeRepository.save(groupeEntity);

        return GroupeMapper.toDto(savedGroupe);
    }

    public GroupeDto updateGroupe(GroupeDto groupeDto) {
        GroupeEntity groupeEntity = findGroupeEntityById(groupeDto.getIdGroupe());

        GroupeMapper.toEntity(groupeDto);

        if (groupeDto.getPromoDto().getIdPromo() != null) {
            Integer currentIdPromo = groupeEntity.getPromo().getIdPromo();

            if (currentIdPromo == null || !currentIdPromo.equals(groupeDto.getPromoDto().getIdPromo())) {
                PromoEntity promoEntity = promoService.findPromoEntityById(groupeDto.getPromoDto().getIdPromo());
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
