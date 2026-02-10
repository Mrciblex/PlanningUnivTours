package com.univtime.informatique.services;

import com.univtime.informatique.dto.groupeDto.GroupeDto;
import com.univtime.informatique.entities.GroupeEntity;
import com.univtime.informatique.entities.PromoEntity;
import com.univtime.informatique.exceptions.ResourceNotFoundException;
import com.univtime.informatique.mappers.GroupeMapper;
import com.univtime.informatique.repositories.GroupeRepository;
import com.univtime.informatique.repositories.PromoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class GroupeService {
    @Autowired
    private GroupeRepository groupeRepository;

    @Autowired
    private PromoRepository promoRepository;

    public List<GroupeDto> findAllGroupe() {
        List<GroupeEntity> groupeEntities = groupeRepository.findAll();

        return groupeEntities
                .stream()
                .map(GroupeMapper::toDto)
                .collect(Collectors.toList());
    }

    public GroupeDto findGroupeById(Integer id) {
        GroupeEntity groupeEntity = groupeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Le groupe avec l'id n'est pas trouvé : " + id));

        return GroupeMapper.toDto(groupeEntity);
    }

    public GroupeDto createGroupe(GroupeDto groupeDto) {
        // Vérifie la clé étrangère de promo
        if (groupeDto.getPromoDto().getIdPromo() == null) {
            throw new ResourceNotFoundException("L'id de la promo est obligatoire");
        }

        GroupeEntity groupeEntity = GroupeMapper.toEntity(groupeDto);

        Integer idPromoR = groupeDto.getPromoDto().getIdPromo();

        PromoEntity promoEntity = promoRepository.findById(idPromoR)
                .orElseThrow(() -> new ResourceNotFoundException("La promo avec l'id n'est pas trouvé : " + groupeDto.getPromoDto().getIdPromo()));
        promoEntity.setIdPromo(promoEntity.getIdPromo());

        GroupeEntity savedGroupe = groupeRepository.save(groupeEntity);

        return GroupeMapper.toDto(savedGroupe);
    }

    public GroupeDto updateGroupe(GroupeDto groupeDto) {
        GroupeEntity groupeEntity = groupeRepository.findById(groupeDto.getIdGroupe())
                .orElseThrow(() -> new ResourceNotFoundException("Le groupe avec l'id n'est pas trouvé : " + groupeDto.getIdGroupe()));

        GroupeMapper.toEntity(groupeDto);

        if (groupeDto.getPromoDto().getIdPromo() != null) {
            Integer currentIdPromo = Optional.ofNullable(groupeEntity.getPromo().getIdPromo())
                    .map(PromoEntity::getIdPromo)
                    .orElse(null);

            if (currentIdPromo == null || !currentIdPromo.equals(groupeDto.getPromoDto().getIdPromo())) {
                PromoEntity promoEntity = promoRepository.findById(groupeDto.getPromoDto().getIdPromo())
                        .orElseThrow(() -> new ResourceNotFoundException("La promo avec l'id n'est pas trouvé : " + groupeDto.getPromoDto().getIdPromo()));
                promoEntity.setIdPromo(promoEntity.getIdPromo());
            }
        }

        GroupeEntity updatedGroupe = groupeRepository.save(groupeEntity);

        return GroupeMapper.toDto(updatedGroupe);
    }

    public void deleteGroupe(Integer id) {
        groupeRepository.deleteById(id);
    }
}
