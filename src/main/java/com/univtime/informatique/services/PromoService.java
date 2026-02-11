package com.univtime.informatique.services;

import com.univtime.informatique.dto.promoDto.PromoDto;
import com.univtime.informatique.entities.PromoEntity;
import com.univtime.informatique.exceptions.ResourceNotFoundException;
import com.univtime.informatique.mappers.PromoMapper;
import com.univtime.informatique.repositories.PromoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PromoService {
    @Autowired
    private PromoRepository promoRepository;

    public List<PromoDto> findAllPromos() {
        List<PromoEntity> promoEntities = promoRepository.findAll();

        return promoEntities
                .stream()
                .map(PromoMapper::toDto)
                .collect(Collectors.toList());
    }

    public PromoDto findPromoDtoById(Integer id) {
        PromoEntity promoEntity = promoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La promo avec l'id n'est trouvé : " + id));

        return PromoMapper.toDto(promoEntity);
    }

    public PromoEntity findPromoEntityById(Integer id) {
        return promoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La promo avec l'id n'est trouvé : " + id));
    }

    public PromoDto createPromo(PromoDto promoDto) {
        PromoEntity promoEntity = PromoMapper.toEntity(promoDto);

        PromoEntity savedPromo = promoRepository.save(promoEntity);

        return PromoMapper.toDto(savedPromo);
    }

    public PromoDto updatePromo(PromoDto promoDto) {
        PromoEntity promoEntity = findPromoEntityById(promoDto.getIdPromo());

        PromoMapper.toEntity(promoDto);

        PromoEntity savedPromo = promoRepository.save(promoEntity);

        return PromoMapper.toDto(savedPromo);
    }

    public void deletePromo(Integer id) {
        findPromoEntityById(id);
        promoRepository.deleteById(id);
    }
}
